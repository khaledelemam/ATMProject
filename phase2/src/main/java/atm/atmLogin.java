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

public class atmLogin {

    public void setTimeInitial() {

        Time date;

        LocalTime time = ZonedDateTime.now().toLocalTime().truncatedTo(MINUTES);
        System.out.println(time.toString());
        LocalTime midnight = LocalTime.MAX;
        long terminate = SECONDS.between(time, midnight);
        date = new Time(1);

        SavingsAccount savings = new SavingsAccount();

        if (date.date.getDay() == 1){
            savings.applyInterest(1);
        }
        Executors.newSingleThreadScheduledExecutor().schedule(Platform::exit, terminate, TimeUnit.SECONDS);
        Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), terminate, TimeUnit.SECONDS);
    }



    // ----- login -----

    public boolean adminCheck(String username, String password) {
        BankManager bankManager = new BankManager();
        return username.equals("admin") && password.equals(bankManager.getPassword());
    }

    public boolean userLogin(String username, String password) {

        Database Database = new Database();
        User USER = Database.login(username, password);
        return USER != null;
    }

    // ----- new user -----

    public String newUserRequest(String username) {
        try {
            BankManager bmu = new BankManager();
            bmu.newUserRequest(username);
            return "Please wait until an admin approves your account.";
        } catch (UsernameTakenException u) {
            return (u.getMessage());
        }
    }


}
