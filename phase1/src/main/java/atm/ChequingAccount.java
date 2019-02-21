package atm;

public class ChequingAccount {

    private User owner;
    private int balance;
    private Transaction lastTransaction;
    private Date dateOpened;

    public ChequingAccount(User owner) {
        this.owner = owner;
        this.balance = 0;
        this.lastTransaction = null;
//        this.dateOpened = getCurrentDate
    }

    public int getBalance() {
        return this.balance;
    }

    public User getOwner() {
        return this.owner;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public Transaction getLastTransaction() {
        return this.lastTransaction;
    }

    public void setLastTransaction(Transaction newTransaction) {
        this.lastTransaction = newTransaction;
    }

}
