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

public class atmController {
    private User USER = null;
    private Time date;


    // ----- helper methods -----
    public ObservableList<Account> getAccounts() {

        return FXCollections.observableArrayList(USER.getAccounts());
    }

    public ObservableList<String> getWithdrawValues() throws IOException {
        CashManager cm = new CashManager();

        return FXCollections.observableArrayList(cm.getWithdrawAmounts());
    }


    public void setUser(String username) {

        Database Database = new Database();
        USER = Database.checkExistingUser(username);
    }
    public void setUser(User user) {
        USER = user;
    }

    public User getUser() {
        return USER;
    }

    public ObservableList<User> getUsers() {
        Database Database = new Database();
        return FXCollections.observableArrayList(Database.getUsers());
    }


    public ObservableList<User> getUsersReverse() {
        Database Database = new Database();
        return FXCollections.observableArrayList(Database.getUsersReverse());
    }




    public void setTimeInitial() {
        LocalTime time = ZonedDateTime.now().toLocalTime().truncatedTo(MINUTES);
        System.out.println(time.toString());
        LocalTime midnight = LocalTime.MAX;
        long terminate = SECONDS.between(time, midnight);
        date = new Time(1);

        SavingsAccount savings = new SavingsAccount();

        if (date.date.getDay() == 1){
//            bankManager.applyInterest(1);
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
        bmn.createUser();
    }

    public void acceptNewAccountRequests() {
        BankManager bma = new BankManager();
        System.out.println(1);
        bma.newAccountRequest();
    }

    public void advanceDate(int days) {
        BankManager bankManager = new BankManager();
        bankManager.setDate(days, date);
    }

    public String reverseTransaction(Account account) {
        try {
            BankManager bankManager = new BankManager();
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
        Database Database = new Database();
        USER.setPassword(newPassword);
        Database.store();

    }

    public void logout() {
        USER = null;
    }

    public String requestNewAccount(AccountType choice, String partner) {
        Database Database = new Database();
        if (choice == AccountType.JOINT) {



            if (Database.checkExistingUser(partner) != null) {
                // TODO: ??
                USER.requestJointAccount(partner, choice);
                Database.store();
                return "Account requested";
            } else {
                return "User does not exist.";
            }
        }
        USER.requestAccount(choice);
        Database.store();
        return "Account requested";
    }

    public String viewAccountInfo(Account account) {
        return USER.viewAccountInto(account);
    }

    public String getNetBalance() {
        return USER.netUserBalance();
    }

    public String internalTransfer(Account source, Account destimation, double amount) {
        try {
            UserExecutes transaction = new UserExecutes(new InternalTransfer(source, destimation));
            transaction.executeTransaction(amount);
            return "Transaction completed.";
        } catch (InsufficientFundsException | WithdrawException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error!";
        } catch (NullPointerException e) {
            return "Please open a second account.";
        }
    }

    public String externalTransfer(User recipient, double amount, Account source) {
        try {
            UserExecutes transaction = new UserExecutes(new ExternalTransfer(source, recipient));
            transaction.executeTransaction(amount);
            return "Transaction completed.";
        } catch (InsufficientFundsException | WithdrawException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error!";
        } catch (NullPointerException e) {
            return "Please open a second account.";
        }
    }

    public String payBill(Account source, double amount) {
        try {
            UserExecutes transaction = new UserExecutes(new PayBills(source, USER, date));
            transaction.executeTransaction(amount);
            return "Bill payment completed.";
        } catch (InsufficientFundsException | WithdrawException e) {
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
        } catch (InsufficientFundsException | WithdrawException e) {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        }
    }

    public String withdraw(double amount) {
        UserExecutes transaction = new UserExecutes(new Withdraw(USER.getPrimaryAccount()));
        System.out.println(USER.getPrimaryAccount());
        try {
            transaction.executeTransaction(amount);
            return "Withdraw completed.";
        } catch (InsufficientFundsException | WithdrawException e) {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        }
    }






}
