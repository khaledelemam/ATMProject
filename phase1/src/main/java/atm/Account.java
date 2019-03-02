package atm;

import java.io.Serializable;

public abstract class Account implements Serializable {

    private User owner;
//    private int balance;
    private Transaction lastTransaction;
    private Date dateOpened;

    public Account(User owner) {
        this.owner = owner;
        this.lastTransaction = null;
//        this.dateOpened = getCurrentDate
    }

    public abstract int getBalance();

    // this takes in either a negative balance if money was taken from the account
    // or positive balance if money is added
    public abstract void setBalance(int amount);

    public User getOwner() { return this.owner; }

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


