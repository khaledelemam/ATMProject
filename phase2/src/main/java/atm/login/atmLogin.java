package atm.login;

import atm.Database;
import atm.admin.BankManager;
import atm.users.BankIntern;
import atm.users.User;
import atm.users.UserType;
import atm.users.UsernameTakenException;

/** Controller class for login data for users and creating new login for new users. */
public class atmLogin {


    private BankManager bankManager;

    // ----- login -----

    public atmLogin(BankManager bankManager){
         this.bankManager = bankManager;

         Database Database = new Database();
         Database.retrieve();


    }

    public boolean adminCheck(String username, String password) {
        return username.equals(bankManager.getUsername()) && password.equals(bankManager.getPassword());

    }

    public boolean bankTellerCheck(String username, String password){
        Database database = new Database();
        User User = database.login(username,password);
        return  User instanceof BankIntern;



    }
    public boolean userLogin(String username, String password) {

        Database Database = new Database();
        User USER = Database.login(username, password);
        return USER != null;
    }

    // ----- new user -----

    public String newUserRequest(String username, UserType type) {
        try {
            bankManager.newUserRequest(username, type);
            return "Please wait until an admin approves your account.";
        } catch (UsernameTakenException u) {
            return (u.getMessage());
        }
    }


}
