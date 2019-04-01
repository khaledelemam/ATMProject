package atm.Controllers;

//import com.sun.xml.internal.rngom.digested.DAnnotation;
import atm.Model.CashHandler;
import atm.Model.CashManager;
import atm.Model.Database;
import atm.Model.accounts.Account;
import atm.Model.admin.ReverseTransactions;
import atm.Model.transactions.InsufficientFundsException;
import atm.Model.admin.BankManager;
import atm.Model.users.User;
import atm.Model.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

/** Controller class for admin data and functionality. */
public class AdminController {

    private User USER;
    private BankManager bankManager;
    private atm.Model.Database Database = new Database();

    public AdminController(BankManager bankManager){
        this.bankManager = bankManager;

    }

    public void setUser(User user) {
        USER = user;
    }

    public ObservableList<Account> getAccounts() {
        return FXCollections.observableArrayList(USER.getAccounts());
    }


    public ObservableList<String> getBills() throws IOException {
        CashHandler ch = new CashHandler();
        return FXCollections.observableArrayList(ch.getBillsList());
    }


    public ObservableList<User> getUsers() {
        return FXCollections.observableArrayList(Database.getUsers());
    }

    public void acceptNewUserRequests() { bankManager.createUser(); }

    public void acceptNewAccountRequests() {
        bankManager.createAccounts();
    }

    public void advanceDate(int days) {
        bankManager.setDate(days, new Time());
    }

    public String reverseTransaction(Account account) {
        try {
            ReverseTransactions reverseTransactions = new ReverseTransactions();
            reverseTransactions.ReverseLastTransfer(account);
            return "Transfer for " + USER + " reversed";
        } catch (InsufficientFundsException e) {
            return "Transfer could not be reversed.";
        } catch (NullPointerException n) {
            return n.getMessage();
        }
    }

    public String addBills(int amount, int bill){
        try {
            bankManager.ReStockATM(amount, bill);
            return "Bill re-stocked";
        }catch (IOException e){
            return "Error!";
        }

    }


}
