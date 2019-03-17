package atm;

import java.io.IOException;

public class atmRunner {
    User USER = null;

    // ----- login -----

    public boolean adminCheck(String password) {
        BankManager bankManager = new BankManager();
        return password.equals(bankManager.getPassword());
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
            return "Please wait until an admin approves your account. \nPassword is: 1";
        } catch (UsernameTakenException u) {
            return (u.getMessage());
        }
    }

    // ----- admin -----

    public void acceptNewUserRequests() {
        BankManager bm = new BankManager();
        bm.createUser();
    }

    public void acceptExistingUserRequests() {
        BankManager bm2 = new BankManager();
        bm2.userRequestAccount();
    }

    // ----- main menu -----

    public void changePassword(String newPassword) {
        USER.setPassword(newPassword);
        Database.store();
    }
}
