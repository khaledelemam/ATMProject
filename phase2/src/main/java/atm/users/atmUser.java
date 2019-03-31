package atm.users;

import atm.CashManager;
import atm.Database;
import atm.transactions.UserExecutes;
import atm.accounts.Account;
import atm.accounts.AccountType;
import atm.transactions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Controller class for user data and functionality. */
public class atmUser {

    private User USER;


    public atmUser(String username){

        Database Database = new Database();
        USER = Database.checkExistingUser(username);
    }

    public ObservableList<String> getWithdrawValues() throws IOException {
        CashManager cm = new CashManager();
        return FXCollections.observableArrayList(cm.getWithdrawAmounts());
    }


    public ObservableList<Account> getAccounts() {
        return FXCollections.observableArrayList(USER.getAccounts());
    }


    public ObservableList<User> getUsers() {
        Database Database = new Database();
        List<User> allUsers = Database.getUsers();
        List<User> otherUsers = new ArrayList<>();
        for (User user: allUsers){
            if (!user.getUsername().equals(USER.getUsername())){
                otherUsers.add(user);
            }
        }

        return FXCollections.observableArrayList(otherUsers);
    }

   public String viewAccountInfo(Account account) {
        return USER.viewAccountInfo(account);
    }

    public String getNetBalance() {
        return USER.netUserBalance();
    }


    public void changePassword(String newPassword) {
        Database Database = new Database();
        USER.setPassword(newPassword);
        Database.store();

    }

     public String requestNewAccount(AccountType choice, String partner) {
        Database Database = new Database();
        if (choice == AccountType.JOINT) {
            if (Database.checkExistingUser(partner) != null) {
                USER.requestAccount(partner, choice);
                return "Account requested";
            } else {
                return "User does not exist.";
            }
        }
        USER.requestAccount(choice);
        return "Account requested";
    }


    public String internalTransfer(Account source, Account destination, double amount) {
        try {
            UserExecutes transaction = new UserExecutes(new InternalTransfer(source, destination));
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
            UserExecutes transaction = new UserExecutes(new PayBills(source, USER));
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
