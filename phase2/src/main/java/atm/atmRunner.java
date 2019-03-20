package atm;

import com.sun.xml.internal.fastinfoset.util.StringArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class atmRunner {
    private User USER = null;
    private BankManager bankManager = new BankManager();


    // ----- helper methods -----
    public ArrayList<String> getAccounts() {
        return USER.getAccounts();
    }

    public void setUser(String username) {
        USER = Database.checkExistingUser(username);
    }

    public void setUser(User user) {
        USER = user;
    }

    public User getUser() {
        return USER;
    }

    public ObservableList<User> getUsers() {
        Database.retrieve();
        return FXCollections.observableArrayList(Database.getUsers());
    }
    // ----- login -----

    public boolean adminCheck(String username, String password) {
        return username.equals("admin") && password.equals(bankManager.getPassword());
    }

    public boolean userLogin(String username, String password) {
        USER = Database.login(username, password);
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

    // ----- admin -----

    public void acceptNewUserRequests() {
        BankManager bmn = new BankManager();
        bankManager.createUser();
    }

    public void acceptNewAccountRequests() {
        BankManager bma = new BankManager();
        bankManager.userRequestAccount();
    }

    public String reverseTransaction(int index) {
        try {
            bankManager.ReverseLastTransaction(USER, index);
            return "Transaction for " + USER + " reversed";
        } catch (InsufficientFundsException e) {
            return "Transaction could not be reversed.";
        } catch (NullPointerException n) {
            return n.getMessage();
        }
    }

    // ----- user menu -----

    public void changePassword(String newPassword) {
        USER.setPassword(newPassword);
        Database.store();
    }

    public void logout() {
        USER = null;
    }

    public void requestNewAccount(int choice, String partner) {
        if (choice == 5) {
            USER.requestJointAccount(partner);
        }
        USER.requestAccount(choice);
    }

    public String viewAccountInfo(int index) {
        return USER.viewAccountInfo(index);
    }

    public String internalTransfer(int from, int to, double amount) {
        try {
            InternalTransfer itransfer = new InternalTransfer(from, to, USER);
            UserExecutes transaction = new UserExecutes(itransfer);
            transaction.executeTransaction(amount);
            return itransfer.toString();
        } catch (InsufficientFundsException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error!";
        }
    }

    public String externalTransfer(User recipient, double amount, int index) {
        try {
            // TODO: change external transfer so it takes a user instead of string when u refactor
            ExternalTransfer etransfer = new ExternalTransfer(index, recipient.getUsername(), USER);
            UserExecutes transaction = new UserExecutes(etransfer);
            transaction.executeTransaction(amount);
            return etransfer.toString();
        } catch (InsufficientFundsException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error!";
        }
    }

    public String payBill(int from, double amount) {
        try {
            PayBills payment = new PayBills(from, USER);
            UserExecutes transaction = new UserExecutes(payment);
            transaction.executeTransaction(amount);
            return payment.toString();
        }catch (InsufficientFundsException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error!";
        }
    }
}
