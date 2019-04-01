package atm.Controllers;

import atm.Model.CashManager;
import atm.Model.Database;
import atm.Model.accounts.Account;
import atm.Model.accounts.AccountType;
import atm.Model.transactions.*;
import atm.Model.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Controller class for user data and functionality. */
public class UserController {

    private User USER;


    public UserController(String username){

        Database Database = new Database();
        USER = Database.checkExistingUser(username);
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

}
