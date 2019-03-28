package atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class atmAdmin {

    private User USER = null;
    private Time date;


    // ----- helper methods -----
    public ObservableList<Account> getAccounts() {

        return FXCollections.observableArrayList(USER.getAccounts());
    }


    ObservableList<String> getBills() throws IOException {
        CashManager cm = new CashManager();

        return FXCollections.observableArrayList(cm.getDenomList());
    }


    public void setUser(User user) {
        USER = user;
    }


    ObservableList<User> getUsersReverse() {
        Database Database = new Database();
        return FXCollections.observableArrayList(Database.getUsersReverse());
    }



    void acceptNewUserRequests() {
        BankManager bmn = new BankManager();
        bmn.createUser();
    }

    void acceptNewAccountRequests() {
        BankManager bma = new BankManager();
        bma.newAccountRequest();
    }

    void advanceDate(int days) {
        BankManager bankManager = new BankManager();
        bankManager.setDate(days, date);
    }

    String reverseTransaction(Account account) {
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

    String addBills(int amount, int bill){
        try {
            BankManager bankManager = new BankManager();
            bankManager.ReStockATM(amount, bill);
            return "Bill re-stocked";
        }catch (IOException e){
            return "Error!";
        }

    }




}
