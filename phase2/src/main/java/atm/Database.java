package atm;

import java.io.*;
import java.util.ArrayList;


public class Database {

   static ArrayList<User> users = new ArrayList<>();

    static ArrayList<User> getUsers(){
        return users;
    }


    static void addUser(User user){
        users.add(user);
        store();
    }

    static User checkExistingUser(String username){
        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getUsername().equals(username)){
                return users.get(i);
            }
        }

        return null;
    }


   static  User checkExistingUserDeposit(String username){
        File f = new File("file");
        if (f.exists()) {
            retrieve();
        }

        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getUsername().equals(username)){
                return users.get(i);
            }
        }
        store();
        return null;
    }



    static  void  store(){
        try{
            FileOutputStream fos = new FileOutputStream("file");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            users.size();
            oos.writeObject(users);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")

   static  void retrieve() {
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
}
