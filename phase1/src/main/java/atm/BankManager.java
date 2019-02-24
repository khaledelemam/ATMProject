package atm;

import java.util.ArrayList;
import java.util.Arrays;

public class BankManager {

    public static  ArrayList<User> users = new ArrayList<>();

    public void setPassword(User user){
        String password = "123abc";
        user.setPassword(password);
        users.add(user);
        System.out.println("Your current password is " + password);

    }

    public void ReverseLastTransaction(){

    }


}
