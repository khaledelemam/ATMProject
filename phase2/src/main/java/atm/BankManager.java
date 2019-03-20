package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class BankManager implements Serializable {

//    private  Database Database = new Database();
    private String password;


//    private ArrayList<ArrayList<String>> requests  = new ArrayList<>();
    private ArrayList<Request> requests = new ArrayList<>();
    // TODO: i realise now this could also be a hash map and we wouldnt need "request" objects


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



//    void createUser() {
//
//        Database.retrieve();
//
//        retrieveRequests();
//
//        for(int i = 0; i < requests.size();i++){
//                if (CreditScore.getRandomDoubleBetweenRange() > 0) {
//                    User user = new User(requests.get(i).get(0));
//                    user.createAccount(requests.get(i).get(1));
//                    setUserPassword(user);
//
//                }
//        }
//        requests.clear();
//        storeRequests();
//
//
//    }
    void createUser() {
        Database.retrieve();
        retrieveRequests();

        for (Request request : requests) {
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                User user = new User(request.getUsername());
                user.createAccount(AccountType.CHEQUING);
                setUserPassword(user);
                user.setPrimaryAccount((ChequingAccount) user.getAccounts().get(0));
            }
        }
        requests.clear();
        storeRequests();
    }


//    void  userRequestAccount(){
//        Database.retrieve();
//        for(int i = 0; i< Database.getUsers().size();i++){
//            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
//
//                // for joint accounts
//                if (Database.getUsers().get(i).getRequest() != null && Database.getUsers().get(i).getJoint() != null ) {
//                    //primary user
//                    User main = Database.getUsers().get(i);
//                    String request = Database.getUsers().get(i).getRequest();
//                    main.createAccount(request);
//
//                    String joint = Database.getUsers().get(i).getJoint();
//
//                    //partner
//                    User partner =  Database.checkExistingUser(joint);
//
//                    if (partner !=null){
//                        partner.setJoint(main.getUsername());
//                        partner.addAccount(main.getJointAccount());
//                    }
//
//                    }
//
//                //for single accounts
//                else if(Database.getUsers().get(i).getRequest() != null ){
//                    Database.getUsers().get(i).createAccount(Database.getUsers().get(i).getRequest());
//                }
//            }
//        Database.store();
//    }
//    }

    void  newAccountRequest(){
        Database.retrieve();

        for(User user : Database.getUsers()){
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                // for joint accounts
                if (user.getRequest() != null && user.getJoint() != null ) {

                    // TODO: i am a bit confused about how joint accts work here
                    //primary user
                    AccountType request = user.getRequest();
                    user.createAccount(request);

                    String joint = user.getJoint();

                    //partner
                    User partner = Database.checkExistingUser(joint);

                    partner.setJoint(user.getUsername());
                    partner.addAccount(user.getJointAccount());

                } //for single accounts
                else if (user.getRequest() != null ){
                    user.createAccount(user.getRequest());
                }
            }
            Database.store();
        }
    }



//    void ReverseLastTransaction(User user, int account) throws InsufficientFundsException{
//        try {
//            Account acc = user.getAccount(account);
//            Transaction transaction = acc.getLastTransaction();
//            ReverseATM rATM = new ReverseATM();
//            if (transaction.getRecipient() != null) {
//                rATM.ReverseTransaction(acc, transaction);
//                System.out.println("Reversed transaction for: " + user.getUsername());
//            } else {
//                System.out.println("The last transaction is not a transfer between accounts.");
//            }
//            Database.store();
//        } catch (NullPointerException e) {
//            NullPointerException n = new NullPointerException("No transaction.");
//            throw n;
//        }
//
//    }

    void ReverseLastTransaction(Account account) throws InsufficientFundsException{
        try {
            Transaction transaction = account.getLastTransaction();
            ReverseATM rATM = new ReverseATM();
            if (transaction.getRecipient() != null) {
                rATM.ReverseTransaction(account, transaction);
//                System.out.println("Reversed transaction for: " + user.getUsername());
//            } else {
//                System.out.println("The last transaction is not a transfer between accounts.");
            }
            Database.store();
        } catch (NullPointerException e) {
            NullPointerException n = new NullPointerException("No transactions available.");
            throw n;
        }

    }



//    void newUserRequest(String username) throws UsernameTakenException{
//
//        Database.retrieve();
//
//        retrieveRequests();
//
//        String request = "Chequing";
//
//        if (Database.checkExistingUser(username) != null){
//            throw new UsernameTakenException();
//        }
//        else {
//            ArrayList<String> arr = new ArrayList<>();
//            arr.add(username);
//            arr.add(request);
//
//            requests.add(arr);
//            storeRequests();
//            Database.store();
//
//            System.out.println("Please wait till the manager processes your request");
//        }
//    }

    void newUserRequest(String username) throws UsernameTakenException {
        Database.retrieve();
        retrieveRequests();

        if (Database.checkExistingUser(username) != null) {
            throw new UsernameTakenException();
        } else {
            Request request = new Request(username, AccountType.CHEQUING);
            requests.add(request);
            storeRequests();
            Database.store();
        }
//
//        storeRequests();
//        Database.store();
    }

    void storeRequests(){
        try{
            Filename file = new Filename();
            FileOutputStream fos= new FileOutputStream(file.getRequestsFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(requests);
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
                requests = (ArrayList<Request>) ois.readObject();
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





// ?? idk what the stuff below is but dont uncomment !



//    void newAccountCreation() {
//        File f = new File("file");
//        if (f.exists()) {
//            Database.retrieve();
//        }
//
//        BankManager bankManager = new BankManager();
//        bankManager.createUser();
//        System.out.println("New users created.");
//
//
//    }
//
//    void newUsersRequests(){
//        Database.retrieve();
//
//        BankManager bankManager = new BankManager();
//        bankManager.userRequestAccount();
//        System.out.println("New accounts created");
//        Database.store();
//    }
//
//
//    void reverseTransaction(String username, int account) throws InsufficientFundsException{
//
//        BankManager bankManager = new BankManager();
//        bankManager.ReverseLastTransaction(username,account);
//        Database.store();
//    }
//
//    void date(){
//        BankManager bankManager = new BankManager();
//        bankManager.setDate();
//    }







}
