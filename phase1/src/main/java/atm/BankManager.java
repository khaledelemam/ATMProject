package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class BankManager implements Serializable {

    private String password;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<ArrayList<String>> requests  = new ArrayList<>();


    public BankManager(){
        this.password = "123";
    }

    String getPassword(){
        return this.password;
    }




    void createUser(){
        File f = new File("file2");
        if (f.exists()) {
            retrieveRequests();
        }
        for(int i = 0; i< requests.size();i++){
                if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                    User user = new User(requests.get(i).get(0));
                    user.addAccount(requests.get(i).get(1));
                    setUserPassword(user);

                }
        }
        requests.clear();
        storeRequests();


    }

    void setUserPassword(User user) {
        String password = "1";
        user.setPassword(password);
        users.add(user);
    }

    void  userRequestAccount(){
        for(int i = 0; i< users.size();i++){
            if (users.get(i).getRequest() != null){
                if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                    users.get(i).addAccount(users.get(i).getRequest());
                    users.get(i).requestAccount(null);
                }
            }
        }
    }

    void ReverseLastTransaction(String username, int account)throws InsufficientFundsException{
        User user = checkExistingUser(username);
        if (user!= null){
            Account acc = user.getAccount(account);
            Transaction transaction = acc.getLastTransaction();
            ReverseATM rATM = new ReverseATM();
            if (transaction.getRecipient() != null) {
                rATM.ReverseTransaction(acc, transaction);
                System.out.println("Reversed transaction for: " + username);
            }
            else{
                System.out.println("The last transaction is not a transfer between accounts.");
            }
        }
    }

    User checkExistingUser(String username){
        File f = new File("file");
        if (f.exists()) {
            retrieve();
        }

        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getUsername().equals(username)){
                return users.get(i);
            }
        }
        return null;
    }



    void setDate(){
        Date date = new Date();
        date.setDate();
        System.out.println(date);

    }

    void store(){
        try{
            FileOutputStream fos = new FileOutputStream("file");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")

    void retrieve() {
        try {
            FileInputStream fis = new FileInputStream("file");
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
//        for (User tmp : users) {
//            System.out.println(tmp);
//        }

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
//        for (User tmp : users) {
//            System.out.println(tmp);
//        }

    }










}
