package atm;

public class Deposit implements UserDo {

    private Account account;


    public Deposit(Account account) {
        this.account = account;
    }


    public void doTransaction(double amount) throws InsufficientFundsException {
        account.setBalance(amount);
        Database.store();

    }
}
