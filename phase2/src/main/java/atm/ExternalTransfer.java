package atm;

public class ExternalTransfer implements UserDo{

    private BankManager bankmanager = new BankManager();

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
        bankmanager.retrieve();
        user = bankmanager.checkExistingUser(username);
        recipient = bankmanager.checkExistingUser(recipientS);

        if (recipient != null) {
            if (recipient.getPrimaryAccount() != null) {
                Account accFrom = user.getAccount(account);
                Account to = recipient.getPrimaryAccount();
                accFrom.setBalance(-amount);
                to.setBalance(amount);

                Transaction extTransfer = new Transaction(accFrom, to, amount);
                accFrom.setLastTransaction(extTransfer);
                to.setLastTransaction(extTransfer);
                bankmanager.store();
            }

        }
    }
}
