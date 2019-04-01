package atm.Controllers;

import atm.Model.admin.BankManager;
import atm.Model.users.UserType;
import atm.Model.users.UsernameTakenException;

public class NewUsersController {

    private BankManager bankManager;

    public NewUsersController(BankManager bankManager){
        this.bankManager = bankManager;
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
