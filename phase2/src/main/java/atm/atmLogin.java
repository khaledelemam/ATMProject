package atm;

import com.sun.xml.internal.fastinfoset.util.StringArray;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

/** Controller class for login data for users and creating new login for new users. */
public class atmLogin {


    private BankManager bankManager;

    // ----- login -----

    public atmLogin(BankManager bankManager){
         this.bankManager = bankManager;

         Database Database = new Database();
         Database.retrieve();


    }

    boolean adminCheck(String username, String password) {
        return username.equals("admin") && password.equals(bankManager.getPassword());

    }

    boolean bankTellerCheck(String username, String password){
        Database database = new Database();
        User User = database.login(username,password);
        return  User instanceof BankIntern;



    }
    boolean userLogin(String username, String password) {

        Database Database = new Database();
        User USER = Database.login(username, password);
        return USER != null;
    }

    // ----- new user -----

    String newUserRequest(String username, UserType type) {
        try {
            bankManager.newUserRequest(username, type);
            return "Please wait until an admin approves your account.";
        } catch (UsernameTakenException u) {
            return (u.getMessage());
        }
    }


}
