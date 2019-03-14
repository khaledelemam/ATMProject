package atm;

public class InternalTransfer implements UserDo {

//    private Database Database = new Database();

    private int from;
    private User user;
    private int to;

    public InternalTransfer(int from , int to, User user) {
        this.from = from;
        this.to = to;
        this.user = user;
    }


    public void doTransaction(double amount) throws InsufficientFundsException {

//        user = Database.checkExistingUser(username);
        try {
            Account accFrom = user.getAccount(from);
            Account accTo = user.getAccount(to);
            accFrom.setBalance(-amount);
            accTo.setBalance(amount);
            Transaction intTransfer = new Transaction(accFrom, accTo, amount);
//            accTo.setLastTransaction(intTransfer);
            accFrom.setLastTransaction(intTransfer);
            Database.store();
        } catch (NullPointerException n) {
            System.out.println("You only have one account. Please request another account.");
        }
    }
}
