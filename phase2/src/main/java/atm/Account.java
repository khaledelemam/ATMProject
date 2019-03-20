package atm;

import java.io.Serializable;
import java.text.DecimalFormat;


// Make sure to keep everything abstract because instance variables are not overridden!


public abstract class Account implements Serializable {

    double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private Transaction lastTransaction;
    private Time dateOpened;
    private static final long serialVersionUID = 10L;

    public Account() {
        this.lastTransaction = null;
        this.balance = 0;
        this.dateOpened = new Time();
    }

    public String getBalance(){
        return currencyFormat.format(balance);
    }

    public abstract void setBalance(double amount) throws InsufficientFundsException;

    public Time getDateOpened() {
        return dateOpened;
    }

    public Transaction getLastTransaction(){
        return lastTransaction;
    }

    public void setLastTransaction(Transaction newTransaction){
        lastTransaction = newTransaction;
    }

    public abstract  double getNetTotal();



}
