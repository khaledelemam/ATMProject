package atm;

import java.io.Serializable;
import java.text.DecimalFormat;


// Make sure to keep everything abstract because instance variables are not overridden!


public abstract class Account implements Serializable {
    private Date dateOpened;
    private static final long serialVersionUID = 10L;

    public Account() {
        this.dateOpened = new Date();
    }
    public abstract String getBalance();

    public abstract void setBalance(double amount) throws InsufficientFundsException;

    public Date getDateOpened() {
        return dateOpened;
    }

    public  abstract Transaction getLastTransaction();

    public abstract void setLastTransaction(Transaction newTransaction);

    public abstract double getNetTotal();

    public abstract double getDoubleBalance();

}
