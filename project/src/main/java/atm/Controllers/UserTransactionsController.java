package atm.Controllers;

import atm.Model.CashManager;
import atm.Model.Database;
import atm.Model.accounts.Account;
import atm.Model.accounts.ChequingAccount;
import atm.Model.transactions.*;
import atm.Model.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public ObservableList<Account> getAccounts(){
        List<Account> userAccounts = new ArrayList<>();
        for(Account acc : USER.getAccounts()){
            if (acc instanceof ChequingAccount){
                userAccounts.add(acc);
            }
        }
        return FXCollections.observableArrayList(userAccounts);
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


    public String deposit(double amount, Account account) {
        UserExecutes transaction = new UserExecutes(new Deposit(account));
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

    public String withdraw(double amount, Account account) {
        UserExecutes transaction = new UserExecutes(new Withdraw(account));
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
