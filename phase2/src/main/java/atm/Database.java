package atm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Database{
    //TODO: Try passing in the serializable stuff in Database constructor and storing each thing ( pass in transactions, users, accounts..)

    private static List<User> users = new ArrayList<>();


    List<User> getUsers() {
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
       for (User user : users) {
           if (user.getUsername().equals(username)) {
               if (user.getPassword().equals(password)) {
                   return user;
               }
           }
       }
       return null;
    }

      void  store(){
        try{
            FileOutputStream fos = new FileOutputStream("Users");
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
        File f = new File("Users");
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream("Users");
                ObjectInputStream ois = new ObjectInputStream(fis);
                users = (List) ois.readObject();
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
