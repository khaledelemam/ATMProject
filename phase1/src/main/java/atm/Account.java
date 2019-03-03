package atm;

import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class Account extends DecimalFormat  implements Serializable{

    private static final long serialVersionUID = 10L;
    private User owner;
    protected Transaction lastTransaction;
    private Date dateOpened;
    protected double balance;

    public Account(User owner) {
        this.owner = owner;
        this.lastTransaction = null;
        this.balance = 0;
//        this.dateOpened = getCurrentDate
    }

    public abstract double getBalance();

    public abstract void setBalance(double amount);

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


