package atm;

import java.io.IOException;

public class UserExecutes {
    private UserDo transaction;

    UserExecutes(UserDo transaction){
        this.transaction= transaction;
    }

    UserExecutes(){}

    void executeTransaction(double amount) throws InsufficientFundsException, IOException {
        transaction.doTransaction(amount);
    }

    void changePassword(String newPassword, String username) {
        User user = Database.checkExistingUser(username);
        user.setPassword(newPassword);
        Database.store();
    }



}
