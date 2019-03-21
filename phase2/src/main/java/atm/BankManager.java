package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class BankManager implements Serializable {

    private String password;
    private ArrayList<String> newUserRequests = new ArrayList<>();

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
        Database.addUser(user);
    }

    void createUser() {
        Database.retrieve();
        retrieveRequests();

        for (String username : newUserRequests) {
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                User user = new User(username);
                user.createAccount(AccountType.CHEQUING);
                setUserPassword(user);
            }
        }
        newUserRequests.clear();
        storeRequests();
    }

    void  newAccountRequest(){
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
            Database.store();
        } catch (NullPointerException e) {
            NullPointerException n = new NullPointerException("No transactions available.");
            throw n;
        }

    }

    void newUserRequest(String username) throws UsernameTakenException {
        Database.retrieve();
        retrieveRequests();

        if (Database.checkExistingUser(username) != null) {
            throw new UsernameTakenException();
        } else {
            newUserRequests.add(username);
            storeRequests();
            Database.store();
        }
    }

    void storeRequests(){
        try{
            Filename file = new Filename();
            FileOutputStream fos= new FileOutputStream(file.getRequestsFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newUserRequests);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")

    void retrieveRequests() {
        Filename file = new Filename();
        File f = new File(file.getRequestsFile());
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file.getRequestsFile());
                ObjectInputStream ois = new ObjectInputStream(fis);
                newUserRequests = (ArrayList<String>) ois.readObject();
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
