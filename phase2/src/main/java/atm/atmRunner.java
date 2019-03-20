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
    public ObservableList<Account> getAccounts() {
        return FXCollections.observableArrayList(USER.getAccounts());
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
        bankManager.newAccountRequest();
    }

    public String reverseTransaction(Account account) {
        try {
            bankManager.ReverseLastTransaction(account);
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

    public String requestNewAccount(AccountType choice, String partner) {
        if (choice == AccountType.JOINT) {
            if (Database.checkExistingUser(partner) != null) {
                // TODO: ??
                USER.requestJointAccount(partner, choice);
                return "Account requested";
            } else {
                return "User does not exist.";
            }
        }
        USER.requestAccount(choice);
        return "Account requested";
    }

    public String viewAccountInfo(Account account) {
        return USER.viewAccountInto(account);
//        return USER.viewAccountInfo(index);
    }

    public String getNetBalance() {
        return USER.netUserBalance();
    }

    public String internalTransfer(Account source, Account destimation, double amount) {
        try {
            UserExecutes transaction = new UserExecutes(new InternalTransfer(source, destimation));
            transaction.executeTransaction(amount);
            return "Transaction completed.";
        } catch (InsufficientFundsException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error!";
        } catch (NullPointerException e) {
            return "Please open a second account.";
        }
    }

    public String externalTransfer(User recipient, double amount, Account source) {
        try {
            // TODO: change external transfer so it takes a user instead of string when u refactor
            UserExecutes transaction = new UserExecutes(new ExternalTransfer(source, recipient));
            transaction.executeTransaction(amount);
            return "Transaction completed.";
        } catch (InsufficientFundsException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error!";
        } catch (NullPointerException e) {
            return "Please open a second account.";
        }
    }

    public String payBill(Account source, double amount) {
        try {
            UserExecutes transaction = new UserExecutes(new PayBills(source, USER));
            transaction.executeTransaction(amount);
            return "Bill payment completed.";
        } catch (InsufficientFundsException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error!";
        }
    }

    public String deposit(double amount) {
        UserExecutes transaction = new UserExecutes(new Deposit(USER.getPrimaryAccount()));
        System.out.println(USER.getPrimaryAccount());
        try {
            transaction.executeTransaction(amount);
            return "Deposit completed.";
        } catch (InsufficientFundsException e) {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        }
    }

}
