package atm;

import java.io.IOException;

interface BankWorker {

    void ReverseLastTransfer(Account account) throws  InsufficientFundsException;



}
