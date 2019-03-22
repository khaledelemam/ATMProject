package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class BankManager implements Serializable {

    private String password;
    private ArrayList<String> newUsersRequests = new ArrayList<>();

    public BankManager(){
        this.password = "123";
    }

    String getPassword(){
        return this.password;
    }

    void setDate(int days){
        long plusOneDay = (1000 * 60 * 60 * 24);
        long NumberOfDays = plusOneDay * days;
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
        try {
            Transaction transaction = account.getLastTransaction();
            ReverseATM rATM = new ReverseATM();
            if (transaction.getRecipient() != null) {
                rATM.ReverseTransaction(account, transaction);
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

    void storeRequests(){
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

    void retrieveRequests() {
        File f = new File("newUsersRequests");
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream("newUsersRequests");
                ObjectInputStream ois = new ObjectInputStream(fis);
                newUsersRequests = (ArrayList<String>) ois.readObject();
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
