package atm;

import java.text.DecimalFormat;

public class CreditCard extends Account {
    private Date dateOpened;
    private double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    protected Transaction lastTransaction;

    public CreditCard(User owner) {
        super(owner);

    }
    @Override
    public String getBalance() {
        return currencyFormat.format(this.balance);
    }

    public void setBalance(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Credit Card Account";
    }

    @Override
    public double getDoubleBalance() {return this.balance;}

    public void setLastTransaction(Transaction newTransaction) {
        this.lastTransaction = newTransaction;
    }
    public Transaction getLastTransaction() {
        return this.lastTransaction;
    }
    public Date getDateOpened() {
        return dateOpened;
    }

}
