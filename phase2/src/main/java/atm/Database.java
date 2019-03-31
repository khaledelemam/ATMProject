package atm;

import atm.users.User;

import java.util.ArrayList;
import java.util.List;


public class Database{

    private static List<User> users = new ArrayList<>();

    private Filename file = new Filename();


   public  List<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        users.add(user);
        store();
    }

    public User checkExistingUser(String username){
        for (User user:users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User login(String username, String password){
       for (User user : users) {
           if (user.getUsername().equals(username)) {
               if (user.getPassword().equals(password)) {
                   return user;
               }
           }

       }
       return null;
    }

    public void  store(){
          Serialize ser = new Serialize(file.getUsersFile(), users);
          ser.store();
    }

    @SuppressWarnings("unchecked")

   public  void retrieve() {
        Serialize ser = new Serialize(file.getUsersFile(), users);
        Object retrieve = ser.retrieve();
        if (retrieve != null) users = (List<User>) retrieve;

    }



}
