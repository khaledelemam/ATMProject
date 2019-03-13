package atm;

import java.io.IOException;

public class UserExecutes {
    private UserDo transaction;

    public UserExecutes(UserDo transaction){
        this.transaction= transaction;
    }

    public void executeTransaction(double amount) throws InsufficientFundsException, IOException {
        transaction.doTransaction(amount);
    }


}
