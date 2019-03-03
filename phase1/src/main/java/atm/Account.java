package atm;

import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class Account implements Serializable {

    private static final long serialVersionUID = 10L;
    private User owner;
    protected Transaction lastTransaction;
    private Date dateOpened;

    public Account(User owner) {
        this.owner = owner;
        this.lastTransaction = null;

//        this.dateOpened = getCurrentDate
    }

    public abstract String  getBalance();

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


