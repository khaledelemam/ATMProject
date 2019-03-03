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

    public void setUserPassword(User user){
        String password = "1";
        user.setPassword(password);
        users.add(user);

    }

    // Bank manger has to initial user, currently new users are initialized in ATM automatically but the bank manager should have the option to accept or reject
    // a new user. Maybe use text file to store user names and accounts of requests? Also i am not sure if that is true. I know this is true regarding creating
    // new account for an existing user.
    public void createUser(){
        for(int i = 0; i< requests.size();i++){
            for(int j = 0; j < requests.get(i).size();j++){
                User user = new User(requests.get(i).get(0));
                if (requests.get(i).get(1).equals("Chequing")){
                    user.addAccount("Chequing");

                }
                else if (requests.get(i).get(1).equals("Savings")){
                    user.addAccount("Savings");

                }
                else if (requests.get(i).get(1).equals("Line of Credit")){
                    user.addAccount("Line of Credit");

                }
                else if (requests.get(i).get(1).equals("Credit Card")){
                    user.addAccount("Credit Card");
                }
                setUserPassword(user);

            }

        }




    }

    //Here by default the bank manager accepts the request but bank manager should have option to reject new accounts.

    public void  userRequestAccount(){
        for(int i = 0; i< users.size();i++){
            if (users.get(i).getRequest() != null){
                users.get(i).addAccount(users.get(i).getRequest());
            }
        }
    }

    public void ReverseLastTransaction(){

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

    public void retrieve(){
        try
        {
            FileInputStream fis = new FileInputStream("file");
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        for(User tmp: users){
            System.out.println(tmp);
        }




    }


}
