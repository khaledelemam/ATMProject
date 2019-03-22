package atm;

import java.io.*;
import java.util.ArrayList;


public class Database {

    //TODO: Try passing in the serializable stuff in Database constructor and storing each thing ( pass in transactions, users, accounts..)

    static private ArrayList<User> users = new ArrayList<>();

    ArrayList<User> getUsers() {
        return users;
    }

    void addUser(User user){
        users.add(user);
        store();
    }

    User checkExistingUser(String username){
        for (User user:users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    User login(String username, String password){
       retrieve();
       for (User user : users) {
           if (user.getUsername().equals(username)) {
               if (user.getPassword().equals(password)) {
                   return user;
               }
           }
       }
       store();
       return null;
    }


      void  store(){
        try{
            FileOutputStream fos = new FileOutputStream("Users");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeUnshared(users);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")

    void retrieve() {
        File f = new File("Users");
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream("Users");
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
        }
    }



}
