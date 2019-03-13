package atm;

public class ExternalTransfer implements UserDo{

    private BankManager bankmanager = new BankManager();
//    private Database Database = new Database();
    private String username;
    private User user;
    private String recipientS;
    private User recipient;
    private int account;

    public ExternalTransfer(int from , String recipient, String username) {
        this.username= username;
        this.recipientS = recipient;
        this.account = from;
    }

    public void doTransaction(double amount) throws InsufficientFundsException {
//        Database.retrieve();
        user = Database.checkExistingUser(username);
        recipient = Database.checkExistingUser(recipientS);

        if (recipient != null) {
            if (recipient.getPrimaryAccount() != null) {
                Account accFrom = user.getAccount(account);
                Account to = recipient.getPrimaryAccount();
                accFrom.setBalance(-amount);
                to.setBalance(amount);

                Transaction extTransfer = new Transaction(accFrom, to, amount, recipient.getUsername());
                accFrom.setLastTransaction(extTransfer);
//                to.setLastTransaction(extTransfer);
                Database.store();
            }

        }
    }
}
