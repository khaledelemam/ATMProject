package atm.Controllers;

import atm.Model.CashManager;
import atm.Model.Database;
import atm.Model.accounts.Account;
import atm.Model.transactions.*;
import atm.Model.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class UserTransactionsController {

    private User USER;


    public UserTransactionsController(String username){

        Database Database = new Database();
        USER = Database.checkExistingUser(username);
    }


    public ObservableList<String> getWithdrawValues() throws IOException {
        CashManager cm = new CashManager();
        return FXCollections.observableArrayList(cm.getWithdrawAmounts());
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
