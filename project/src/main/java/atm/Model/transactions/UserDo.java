package atm.Model.transactions;

import java.io.IOException;

public interface UserDo {
    void doTransaction(double amount) throws InsufficientFundsException, IOException, WithdrawException;
}
