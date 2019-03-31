package atm.transactions;

import atm.transactions.InsufficientFundsException;
import atm.transactions.WithdrawException;

import java.io.IOException;

public interface UserDo {
    void doTransaction(double amount) throws InsufficientFundsException, IOException, WithdrawException;
}
