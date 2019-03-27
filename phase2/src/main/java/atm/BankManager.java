package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class BankManager implements Serializable {

    private String password;
    private List<String> newUsersRequests = new ArrayList<>();

    public BankManager(){
        this.password = "123";
    }



    String getPassword(){
        return this.password;
    }



    void setDate(int days, Time oldDate){
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

    void  newAccountRequest(){
        Database Database = new Database();

        Database.retrieve();

        for(User user : Database.getUsers()){
            System.out.println(user.getClass());
            System.out.println(user);

            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                // for joint accounts
                System.out.println(234);
                System.out.println(user.getAccountRequest());

                if (user.getAccountRequest() != null && user.getJoint() != null ) {

                    // TODO: i am a bit confused about how joint accts work here
                    //primary user
                    AccountType request = user.getAccountRequest();
                    user.createAccount(request);

                    String joint = user.getJoint();

                    //partner
                    User partner = Database.checkExistingUser(joint);
                    partner.setJoint(user.getUsername());
                    partner.addAccount(user.getJointAccount());

                } //for single accounts
                else if (user.getAccountRequest() != null ){
                    System.out.println(user.getAccountRequest());
                    user.createAccount(user.getAccountRequest());
                }
            }
            Database.store();
        }
    }

    void ReverseLastTransaction(Account account) throws InsufficientFundsException{
        ReverseATM rATM = new ReverseATM();
        try {
            switch(account.getLastTransaction().getTransactionType()) {
                case InternalTransfer:
                case ExternalTransfer:
                    Transaction transaction = account.getLastTransaction();
                    rATM.ReverseTransaction(transaction);
                    break;
                case Deposit:
                case PayBill:
                case Withdraw:
                    if (account.getTransfers().size()> 1) {
                        Transaction transaction2 = account.getTransfers().get(account.getTransfers().size() - 1);
                        rATM.ReverseTransaction(transaction2);
                        break;
                    }
            }
            Database Database = new Database();
            Database.store();

        } catch (NullPointerException e) {
            NullPointerException n = new NullPointerException("No transactions available.");
            throw n;
        }

    }

    void newUserRequest(String username) throws UsernameTakenException {

        Database Database = new Database();
        Database.retrieve();

        retrieveRequests();

        if (Database.checkExistingUser(username) != null) {
            throw new UsernameTakenException();
        } else {
            newUsersRequests.add(username);
            storeRequests();
            Database.store();
        }
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
