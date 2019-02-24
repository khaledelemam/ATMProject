package atm;

import java.util.ArrayList;
import java.util.Arrays;

public class BankManager {

    public static  ArrayList<User> users = new ArrayList<>(10);

    public void setPassowrd(User user){
        user.setPassword("123abc");
        users.add(user);
    }
    public void ReverseLastTransaction(){

    }


}
