package atm;

import java.text.DecimalFormat;

public class LineOfCredit extends Account {
    private double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    protected Transaction lastTransaction;


    public LineOfCredit() {this.balance = 0;
        this.lastTransaction = null;

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

    @Override
    public double getNetTotal() {
        return -this.balance;
    }

    public Transaction getLastTransaction() {
        return this.lastTransaction;
    }


}
