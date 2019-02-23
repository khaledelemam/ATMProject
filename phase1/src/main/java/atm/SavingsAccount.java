package atm;

public class SavingsAccount {

    private User owner;
    private int balance;
    private Transaction lastTransaction;
    private Date dateOpened;

    public SavingsAccount(User owner) {
        this.owner = owner;
        this.balance = 0;
        this.lastTransaction = null;
//        this.dateOpened = getCurrentDate
    }

    public int getBalance() {
        return this.balance;
    }

    // this takes in either a negative balance if money was taken from the account
    // or positive balance if money is added
    public void setBalance(int amount) {
        this.balance += balance;
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
