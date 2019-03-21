package atm;

import java.io.*;
import java.util.ArrayList;


public class Database {

    //TODO: Try passing in the serializable stuff in Database constructor and storing each thing ( pass in transactions, users, accounts..)

    private static ArrayList<User> users = new ArrayList<>();

    static ArrayList<User> getUsers() {
        return users;
    }

    static void addUser(User user){
        users.add(user);
        store();
    }

    static User checkExistingUser(String username){
        for (User user:users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

   static User login(String username, String password){
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


    static  void  store(){
        try{
            FileOutputStream fos = new FileOutputStream("file");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.reset();
//            oos.writeObject(users);
            oos.writeUnshared(users);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")

   static  void retrieve() {
        File f = new File("file");
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream("file");
                ObjectInputStream ois = new ObjectInputStream(fis);
                users = (ArrayList) ois.readObject();
                System.out.println(users.get(0).getPassword());
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
