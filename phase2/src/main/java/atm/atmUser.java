package atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class atmUser {

    private User USER = null;


    atmUser(String username){

        UserSetting(username);
    }


    private void UserSetting(String username){
        Database Database = new Database();
        USER = Database.checkExistingUser(username);
    }



    ObservableList<String> getWithdrawValues() throws IOException {
        CashManager cm = new CashManager();
        return FXCollections.observableArrayList(cm.getWithdrawAmounts());
    }



    public ObservableList<Account> getAccounts() {
        return FXCollections.observableArrayList(USER.getAccounts());
    }


    public ObservableList<User> getUsers() {
        Database Database = new Database();
        return FXCollections.observableArrayList(Database.getUsers());
    }

    String viewAccountInfo(Account account) {
        return USER.viewAccountInto(account);
    }

    String getNetBalance() {
        return USER.netUserBalance();
    }



    void changePassword(String newPassword) {
        Database Database = new Database();
        USER.setPassword(newPassword);
        Database.store();

    }

     String requestNewAccount(AccountType choice, String partner) {
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


    String internalTransfer(Account source, Account destimation, double amount) {
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

    String externalTransfer(User recipient, double amount, Account source) {
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

    String payBill(Account source, double amount) {
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
