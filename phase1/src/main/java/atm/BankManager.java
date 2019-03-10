package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class BankManager implements Serializable {

    private String password;
    public  ArrayList<User> users = new ArrayList<>();
    public  ArrayList<ArrayList<String>> requests  = new ArrayList<>();


    public BankManager(){
        this.password = "123";
    }

    public String getPassword(){
        return this.password;
    }




    public void createUser(){
        retrieveRequests();
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

    public void setUserPassword(User user) {
        String password = "1";
        user.setPassword(password);
        users.add(user);
    }

    public void  userRequestAccount(){
        for(int i = 0; i< users.size();i++){
            if (users.get(i).getRequest() != null){
                if (CreditScore.getRandomDoubleBetweenRange() > 0) {
                    users.get(i).addAccount(users.get(i).getRequest());
                    users.get(i).requestAccount(null);
                }
            }
        }
    }

    public void ReverseLastTransaction(String username, int account)throws InsufficientFundsException{
        // only withdraw in mind right now.
        User user = checkExistingUser(username);
        if (user!= null){
           Account acc = user.getAccount(account);
           Transaction transaction = acc.getLastTransaction();
           double money = transaction.getAmount();
           ReverseATM rATM = new ReverseATM();
            System.out.println(user.getUsername());
           rATM.ReverseWithdrawal(user, acc, money);
//            System.out.println(username);
//            System.out.println(user.getAccount(1).getBalance());
        }

    }

    public User checkExistingUser(String username){
        File f = new File("file");
        if (f.exists()) {
            retrieve();
        }

        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getUsername().equals(username)){
                return users.get(i);
            }
        }
//       store();
        System.out.println(8);
        return null;
    }



    public void setDate(){
        Date date = new Date();
        date.setDate();
        System.out.println(date);

    }

    public void store(){
        try{
            FileOutputStream fos= new FileOutputStream("file");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")

    public void retrieve() {
        try {
            FileInputStream fis = new FileInputStream("file");
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
//            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
//            return;
        }
//        for (User tmp : users) {
//            System.out.println(tmp);
//        }

    }



    public void storeRequests(){
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

    public void retrieveRequests() {
        try {
            FileInputStream fis = new FileInputStream("file2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            requests = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
//            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
//            return;
        }
//        for (User tmp : users) {
//            System.out.println(tmp);
//        }

    }










}
