package atm;

import java.io.IOException;

public class UserExecutes {
    private UserDo transaction;

    UserExecutes(UserDo transaction){
        this.transaction = transaction;
    }

    void executeTransaction(double amount) throws InsufficientFundsException, IOException , WithdrawException{
        transaction.doTransaction(amount);
    }



}
