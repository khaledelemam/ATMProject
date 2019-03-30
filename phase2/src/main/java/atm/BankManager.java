package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class BankManager implements Serializable, BankWorker {

    private String password;
    private List<String> newUsersRequests = new ArrayList<>();

    public BankManager() {
        this.password = "123";
    }

    public String getPassword() {
        return this.password;
    }


    void setDate(int days, Time oldDate) {
        long plusOneDay = (1000 * 60 * 60 * 24);
        long NumberOfDays = plusOneDay * days;

        SavingsAccount savings = new SavingsAccount();
        savings.numInterestApplications(days, oldDate);

        new Time(NumberOfDays);
    }

    private void setUserPassword(User user) {
        String password = "1";
        user.setPassword(password);

        Database Database = new Database();
        Database.addUser(user);

    }

    void createUser() {
        Database Database = new Database();

        Database.retrieve();
        retrieveRequests();

        for (String username : newUsersRequests) {
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                User user = new User(username);
                user.createAccount(AccountType.CHEQUING);
                setUserPassword(user);
            }
        }
        newUsersRequests.clear();
        storeRequests();
    }

    void newAccountRequest() {
        Database Database = new Database();

        Database.retrieve();

        for (User user : Database.getUsers()) {
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                // for joint accounts

                if (user.getAccountRequest() != null && user.getJoint() != null) {

                    //primary user
                    AccountType request = user.getAccountRequest();
                    user.createAccount(request);

                    String joint = user.getJoint();

                    //partner
                    User partner = Database.checkExistingUser(joint);
                    partner.setJoint(user.getUsername());
                    partner.addAccount(user.getJointAccount());

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
                                Transaction transaction2 = account.getAllTransactions().get(account.getAllTransactions().size() - 1);
                                ReverseTransferHelper(transaction2);
                                break;
                            }
                        }
                    }
                    break;
            }
            Database Database = new Database();
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


    void newUserRequest(String username) throws UsernameTakenException {

        Database Database = new Database();
        Database.retrieve();
        retrieveRequests();

        if ((Database.checkExistingUser(username) != null) || (checkUserRequests(username))) {
            throw new UsernameTakenException();
        } else {
            newUsersRequests.add(username);
            storeRequests();
            Database.store();
        }
    }


    private boolean checkUserRequests(String username){
        for (String user: newUsersRequests) {
            if (user.equals(username)) {
                return true;
            }
        }
        return false;
    }


    void ReStockATM(int amount, int bill) throws IOException{
        CashManager cashManager = new CashManager();
        for (int i = 0; i < cashManager.getDenominations().length; i++){
            if (cashManager.getDenominations()[i] == bill){
                cashManager.getBillNumber()[i]  += amount;
            }
        }
        cashManager.writeToFile();
        cashManager.update();

    }

    private void storeRequests(){
        try{
            FileOutputStream fos= new FileOutputStream("newUsersRequests");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newUsersRequests);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")

    private void retrieveRequests() {
        File f = new File("newUsersRequests");
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream("newUsersRequests");
                ObjectInputStream ois = new ObjectInputStream(fis);
                newUsersRequests = (List<String>) ois.readObject();
                ois.close();
                fis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
            }
        }
    }

}
