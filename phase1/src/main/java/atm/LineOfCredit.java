package atm;

import java.text.DecimalFormat;

public class LineOfCredit extends Account {
    private Date dateOpened;
    private double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    protected Transaction lastTransaction;


    public LineOfCredit() {
    }

    @Override
    public String getBalance() {
        return currencyFormat.format(this.balance);
    }


    public void setBalance(double amount) { this.balance -= amount; }

    @Override
    public String toString() {
        return "Line of Credit Account";
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
