package atm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class atmRunner {
    private User USER = null;
    private BankManager bankManager = new BankManager();


    // ----- helper methods -----
    public ArrayList<String> getAccounts() {
        return  USER.getAllAccounts();
        }

    // ----- login -----

    public boolean adminCheck(String username, String password) {
        BankManager bankManager = new BankManager();
        return username.equals("admin") && password.equals(bankManager.getPassword());
    }

    public boolean userLogin(String username, String password) {
        USER = Database.login(username, password);
        return USER != null;
    }

    // ----- new user -----

    public String newUserRequest(String username) {
        try {
            BankManager bankManager = new BankManager();
            bankManager.newUserRequest(username);
            return "Please wait until an admin approves your account.";
        } catch (UsernameTakenException u) {
            return (u.getMessage());
        }
    }

    // ----- admin -----

    public void acceptNewUserRequests() {
        bankManager.createUser();
    }

    public void acceptExistingUserRequests() {
        bankManager.userRequestAccount();
    }

    // ----- main menu -----

    public void changePassword(String newPassword) {
        USER.setPassword(newPassword);
        Database.store();
    }

    public void logout() {
        USER = null;
    }
}
