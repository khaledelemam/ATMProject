package atm.admin;

//import com.sun.xml.internal.rngom.digested.DAnnotation;
import atm.*;
import atm.accounts.Account;
import atm.transactions.InsufficientFundsException;
import atm.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

/** Controller class for admin data and functionality. */
public class atmAdmin {

    private User USER;
    private BankManager bankManager;
    atm.Database Database = new Database();

    public atmAdmin(BankManager bankManager){
        this.bankManager = bankManager;

    }

    public void setUser(User user) {
        USER = user;
    }

    public ObservableList<Account> getAccounts() {
        return FXCollections.observableArrayList(USER.getAccounts());
    }


    public ObservableList<String> getBills() throws IOException {
        CashManager cm = new CashManager();
        return FXCollections.observableArrayList(cm.getBillsList());
    }


    public ObservableList<User> getUsers() {
        return FXCollections.observableArrayList(Database.getUsers());
    }

    public void acceptNewUserRequests() { bankManager.createUser(); }

    public void acceptNewAccountRequests() {
        bankManager.newAccountRequest();
    }

    public void advanceDate(int days) {
        bankManager.setDate(days, new Time());
    }

    public String reverseTransaction(Account account) {
        try {
            bankManager.ReverseLastTransfer(account);
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
