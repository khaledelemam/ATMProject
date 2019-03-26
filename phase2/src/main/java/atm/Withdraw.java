package atm;

import java.io.IOException;

public class Withdraw implements UserDo {


    private Account account;


    public Withdraw(Account account) {
        this.account = account;
    }


    public void doTransaction(double amount) throws InsufficientFundsException, WithdrawException{

        try {
            CashManager cm = new CashManager();
            cm.subtractDenominations(amount, 0);
            cm.update();

            account.setBalance(-amount);
            Transaction withdrawal = new Transaction(account, amount);
            account.setLastTransaction(withdrawal);

            Database Database = new Database();
            Database.store();

        }
        catch (NegativeDenominationException e) {
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }





}
