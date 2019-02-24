package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class BankManager implements Serializable {

    public static ArrayList<User> users = new ArrayList<>();

    public void setPassword(User user){
        String password = "123abc";
        user.setPassword(password);
        users.add(user);
        System.out.println("Your current password is " + password);

    }

    public void ReverseLastTransaction(){

    }

    public void store(){
        try{
            FileOutputStream fos= new FileOutputStream("myfile");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")

    public void retreive(){
        try
        {
            FileInputStream fis = new FileInputStream("myfile");
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
