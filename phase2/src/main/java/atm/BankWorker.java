package atm;

import java.io.IOException;

interface BankWorker {

    String getPassword();

    void ReverseLastTransaction(Account account) throws  InsufficientFundsException;


}
