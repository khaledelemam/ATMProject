package atm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Database{

    private static List<User> users = new ArrayList<>();

    private Filename file = new Filename();


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
          Serialize ser = new Serialize(file.getUsersFile(), users);
          ser.store();
    }

    @SuppressWarnings("unchecked")

 void retrieve() {
        Serialize ser = new Serialize(file.getUsersFile(), users);
        Object retrieve = ser.retrieve();
        if (retrieve != null) users = (List<User>) retrieve;

    }



}
