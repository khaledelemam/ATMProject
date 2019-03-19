package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class BankManager implements Serializable {

//    private  Database Database = new Database();
    private String password;


    private ArrayList<ArrayList<String>> requests  = new ArrayList<>();


    public BankManager(){
        this.password = "123";
    }

    String getPassword(){
        return this.password;
    }



    void createUser(){

        Database.retrieve();

        retrieveRequests();

        for(int i = 0; i< requests.size();i++){
                if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                    User user = new User(requests.get(i).get(0));
                    user.createAccount(requests.get(i).get(1));
                    setUserPassword(user);

                }
        }
        System.out.println("New users created.");
        requests.clear();
        storeRequests();


    }

    private void setUserPassword(User user) {
        String password = "1";
        user.setPassword(password);
        Database.addUser(user);
    }

    void  userRequestAccount(){
        Database.retrieve();
        for(int i = 0; i< Database.getUsers().size();i++){
            if (CreditScore.getRandomDoubleBetweenRange() > 0) {

                // for joint accounts
                if (Database.getUsers().get(i).getRequest() != null && Database.getUsers().get(i).getJoint() != null ) {
                    //primary user
                    User main = Database.getUsers().get(i);
                    String request = Database.getUsers().get(i).getRequest();
                    main.createAccount(request);

                    String joint = Database.getUsers().get(i).getJoint();

                    //partner
                    User partner =  Database.checkExistingUser(joint);

                    if (partner !=null){
                        partner.setJoint(main.getUsername());
                        partner.addAccount(main.getJointAccount());
                    }

                    }

                //for single accounts
                else if(Database.getUsers().get(i).getRequest() != null ){
                    Database.getUsers().get(i).createAccount(Database.getUsers().get(i).getRequest());
                }
            }
        System.out.println("New accounts created");
        Database.store();
    }
    }


    void ReverseLastTransaction(User user, int account) throws InsufficientFundsException{
            Account acc = user.getAccount(account);
            Transaction transaction = acc.getLastTransaction();
            ReverseATM rATM = new ReverseATM();
            if (transaction.getRecipient() != null) {
                rATM.ReverseTransaction(acc, transaction);
                System.out.println("Reversed transaction for: " + user.getUsername());
            }
            else{
                System.out.println("The last transaction is not a transfer between accounts.");
            }
            Database.store();

    }



    void newUserRequest(String username) throws UsernameTakenException{

        Database.retrieve();

        retrieveRequests();

        String request = "Chequing";

        if (Database.checkExistingUser(username) != null){
            throw new UsernameTakenException();
        }
        else {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(username);
            arr.add(request);

            requests.add(arr);
            storeRequests();
            Database.store();

            System.out.println("Please wait till the manager processes your request");
        }
    }



    void setDate(int days){

        long plusOneDay = (1000 * 60 * 60 * 24);
        long NumberOfDays = plusOneDay * days;

        new Time(NumberOfDays);

    }


    void storeRequests(){
        try{
            FileOutputStream fos= new FileOutputStream("file2");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(requests);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")

    void retrieveRequests() {
        File f = new File("file2");
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream("file2");
                ObjectInputStream ois = new ObjectInputStream(fis);
                requests = (ArrayList) ois.readObject();
                ois.close();
                fis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
            }
        }
//        for (User tmp : users) {
//            System.out.println(tmp);
//        }


    }






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
