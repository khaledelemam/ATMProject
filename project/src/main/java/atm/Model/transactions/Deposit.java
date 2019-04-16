package atm.Model.transactions;

import atm.Model.Database;
import atm.Model.accounts.Account;

public class Deposit implements UserDo {

    private Account account;


    public Deposit(Account account) {
        this.account = account;
    }


    public void doTransaction(double amount) throws InsufficientFundsException {
        account.setBalance(amount);
        Transaction deposit = new Transaction(account, amount, TransactionType.Deposit);
        account.getAllTransactions().add(deposit);
        account.setLastTransaction(deposit);

        Database Database = new Database();
        Database.store();

    }
}
