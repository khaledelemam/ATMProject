package atm;

public class Deposit implements UserDo {

    private User user;
    private int account;


    public Deposit(int account, User user) {
        this.account = account;
        this.user = user;
    }


    public void doTransaction(double amount) throws InsufficientFundsException {
//        User  user = Database.checkExistingUser(username);
        Account acc = user.getAccount(account);
        try { acc.setBalance(amount);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
//            Transaction intTransfer = new Transaction(accFrom, accTo, amount);
        Database.store();

    }
}
