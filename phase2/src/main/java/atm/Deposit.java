package atm;

public class Deposit implements UserDo {

    private String username;
    private int account;


    public Deposit(int account,  String username) {
        this.account = account;
        this.username = username;
    }


    public void doTransaction(double amount) throws InsufficientFundsException {
//        Database.retrieve();
        User  user = Database.checkExistingUser(username);
        Account acc = user.getAccount(account);
        acc.setBalance(amount);
//            Transaction intTransfer = new Transaction(accFrom, accTo, amount);
        Database.store();

    }
}
