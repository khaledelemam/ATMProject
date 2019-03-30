package atm;

import com.sun.xml.internal.rngom.digested.DAnnotation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

/** Controller class for admin data and functionality. */
public class atmAdmin {

    private User USER;
    private BankManager bankManager;

    public atmAdmin(BankManager bankManager){
        this.bankManager = bankManager;

    }


    public void setUser(User user) {
        USER = user;
    }

    public ObservableList<Account> getAccounts() {
        return FXCollections.observableArrayList(USER.getAccounts());
    }


    ObservableList<String> getBills() throws IOException {
        CashManager cm = new CashManager();
        return FXCollections.observableArrayList(cm.getDenomList());
    }


    ObservableList<User> getUsers() {
        Database Database = new Database();
        Database.retrieve();
        return FXCollections.observableArrayList(Database.getUsers());
    }


    void acceptNewUserRequests() { bankManager.createUser(); }

    void acceptNewAccountRequests() {
        bankManager.newAccountRequest();
    }

    void advanceDate(int days) {
        bankManager.setDate(days, new Time());
    }

    String reverseTransaction(Account account) {
        try {
            bankManager.ReverseLastTransfer(account);
            return "Transaction for " + USER + " reversed";
        } catch (InsufficientFundsException e) {
            return "Transaction could not be reversed.";
        } catch (NullPointerException n) {
            return n.getMessage();
        }
    }

    String addBills(int amount, int bill){
        try {
            bankManager.ReStockATM(amount, bill);
            return "Bill re-stocked";
        }catch (IOException e){
            return "Error!";
        }

    }


}
