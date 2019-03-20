package atm;

public class Deposit implements UserDo {

    private Account account;


    public Deposit(Account account) {
        this.account = account;
    }


    public void doTransaction(double amount) throws InsufficientFundsException {
        account.setBalance(amount);
//        try { acc.setBalance(amount);
//        } catch (InsufficientFundsException e) {
//            System.out.println(e.getMessage());
//        }
//            Transaction intTransfer = new Transaction(accFrom, accTo, amount);
        Database.store();

    }
}
