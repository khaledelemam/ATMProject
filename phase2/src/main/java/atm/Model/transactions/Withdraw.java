package atm.Model.transactions;

import atm.Model.CashHandler;
import atm.Model.CashManager;
import atm.Model.Database;
import atm.Model.accounts.Account;

import java.io.IOException;


public class Withdraw implements UserDo {


    private Account account;


    public Withdraw(Account account) {
        this.account = account;
    }


    public void doTransaction(double amount) throws InsufficientFundsException, WithdrawException {

        try {
            //CashManager cm = new CashManager();
            CashHandler ch = new CashHandler();
            ch.subtractDenominations(amount, 0);
            ch.cm.update();

            account.setBalance(-amount);
            Transaction withdrawal = new Transaction(account, amount, TransactionType.Withdraw);
            account.getAllTransactions().add(withdrawal);
            account.setLastTransaction(withdrawal);

            Database Database = new Database();
            Database.store();

        }
        catch (IOException e) {
            e.printStackTrace();
        }






    }





}
