package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class BankManager extends People implements Serializable, BankWork {

    private List<List<Object>> newUsersRequests = new ArrayList<>();
    private Database Database = new Database();
    private Filename file = new Filename();


    public BankManager() {
        this.username = "admin";
        this.password = "123";
    }


    void setDate(int days, Time oldDate) {
        long plusOneDay = (1000 * 60 * 60 * 24);
        long NumberOfDays = plusOneDay * days;

        Calculations calc = new Calculations();
        calc.numInterestApplications(days, oldDate);

        new Time(NumberOfDays);
    }

    private void setUserPassword(User user) {
        String password = "1";
        user.setPassword(password);
        Database.addUser(user);

    }

    void createUser() {
        retrieveRequests();

        for (List<Object> users: newUsersRequests) {
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                String username = (String) users.get(0);
                UserType userType = (UserType) users.get(1);
                switch (userType){
                    case NormalUser:
                        User user = new User(username);
                        user.createAccount(AccountType.CHEQUING);
                        setUserPassword(user);
                        break;
                    case BankIntern:
                        BankIntern bankIntern = new BankIntern(username);
                        bankIntern.createAccount(AccountType.CHEQUING);
                        setUserPassword(bankIntern);
                        break;
                }

            }
        }
        newUsersRequests.clear();
        storeRequests();
    }

    void newAccountRequest() {
        for (User user : Database.getUsers()) {
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                // for joint accounts

                if (user.getAccountRequest() != null && user.getPartner() != null) {
                    //primary user
                    AccountType request = user.getAccountRequest();
                    user.createAccount(request);
                    String partner = user.getPartner();

                    //partner
                    User OtherUser = Database.checkExistingUser(partner);
                    OtherUser.setPartner(user.getUsername());
                    OtherUser.addAccount(user.getJointAccount());

                } //for single accounts
                else if (user.getAccountRequest() != null) {
                    System.out.println(user.getAccountRequest());
                    user.createAccount(user.getAccountRequest());
                }
            }
            Database.store();
        }
    }

    public void ReverseLastTransfer(Account account) throws InsufficientFundsException {
        try {
            switch (account.getLastTransaction().getTransactionType()) {
                case InternalTransfer:
                case ExternalTransfer:
                    Transaction transaction = account.getLastTransaction();
                    ReverseTransferHelper(transaction);
                    break;
                case Deposit:
                case PayBill:
                case Withdraw:
                    if (account.getAllTransactions().size() > 0) {
                        for (int i = account.getAllTransactions().size() - 1; i > 0; i--) {
                            Transaction t = account.getAllTransactions().get(i);
                            if (t.getTransactionType() == TransactionType.InternalTransfer || t.getTransactionType() == TransactionType.ExternalTransfer) {
                                //TODO: I think something is wrong here
                                Transaction transaction2 = account.getAllTransactions().get(account.getAllTransactions().size() - 1);
                                ReverseTransferHelper(transaction2);
                                break;
                            }
                        }
                    }
                    break;
            }
            Database.store();

        } catch (NullPointerException e) {
            throw new NullPointerException("No transactions available.");
        }

    }


    private void ReverseTransferHelper(Transaction trans) throws InsufficientFundsException {
        double amount = trans.getAmount();
        Account from = trans.getRecipient();
        Account to = trans.getSource();
        from.setBalance(-amount);
        to.setBalance(amount);

        List<Transaction> fromTrans = from.getAllTransactions();
        List<Transaction> toTrans = to.getAllTransactions();

        int indexFromTrans = fromTrans.lastIndexOf(trans);
        int indexToTrans = toTrans.lastIndexOf(trans);

        if (fromTrans.size() > 0) {
            fromTrans.remove(indexFromTrans);
        }
        if (toTrans.size() > 0) {
            toTrans.remove(indexToTrans);
        }
        if (toTrans.size() > 0) {
            to.setLastTransaction(toTrans.get(to.getAllTransactions().size() - 1));
        } else {
            to.setLastTransaction(null);
        }
        if (fromTrans.size() > 0) {
            from.setLastTransaction(fromTrans.get(from.getAllTransactions().size() - 1));
        } else {
            from.setLastTransaction(null);
        }
    }


    void newUserRequest(String username, UserType type) throws UsernameTakenException {
        retrieveRequests();

        if ((Database.checkExistingUser(username) != null) || (checkUserRequests(username))) {
            throw new UsernameTakenException();
        } else {
            List<Object> userTypes = new ArrayList<>();
            userTypes.add(username);
            userTypes.add(type);

            newUsersRequests.add(userTypes);
            storeRequests();
            Database.store();
        }
    }


    private boolean checkUserRequests(String username){
        for(List users: newUsersRequests){
            String user = (String)users.get(0);
            System.out.println(user);
            if (user.equals(username)) {
                    return true;
            }
        }
        return false;

    }
    public void ReStockATM(int amount, int bill) throws IOException{
        CashManager cashManager = new CashManager();
        cashManager.ReStockATM(amount, bill);
    }

    private void storeRequests(){
        Serialize ser = new Serialize(file.getRequestsFile(), newUsersRequests);
        ser.store();
    }

    @SuppressWarnings("unchecked")

    private void retrieveRequests() {
        Serialize ser = new Serialize(file.getRequestsFile(), newUsersRequests);
        Object retrieve = ser.retrieve();
        if (retrieve != null) newUsersRequests = (List<List<Object>>) retrieve;
    }

}
