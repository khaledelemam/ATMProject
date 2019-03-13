package atm;

public class InternalTransfer implements UserDo {

    private BankManager bankmanager = new BankManager();
    private String username;
    private int from;
    private User user;
    private int to;

    public InternalTransfer(int from , int to, String username) {
        this.from = from;
        this.to = to;
        this.username = username;
    }


    public void doTransaction(double amount) throws InsufficientFundsException {
        bankmanager.retrieve();
        user = bankmanager.checkExistingUser(username);
        try {
            Account accFrom = user.getAccount(from);
            Account accTo = user.getAccount(to);
            accFrom.setBalance(-amount);
            accTo.setBalance(amount);
            Transaction intTransfer = new Transaction(accFrom, accTo, amount);
            accTo.setLastTransaction(intTransfer);
            accFrom.setLastTransaction(intTransfer);
            bankmanager.store();
        } catch (NullPointerException n) {
            System.out.println("You only have one account. Please request another account.");
        }
    }
}
