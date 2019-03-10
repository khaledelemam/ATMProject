package atm;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.temporal.Temporal;

public class Transaction implements Serializable {

    private Account source;
    private Account recipient;
    private double amount;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private Date date;
    private static final long serialVersionUID = 100L;

    // for  transfers
    // if its an external transfer, it should pass through <recipient user>.getPrimaryAccount()
    public Transaction(Account source, Account recipient, double amount) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
        this.date = new Date();
    }

    // this is used for withdrawals
    // Username, amount
    public Transaction(Account source, double amount) {
        this.source = source;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        if (this.recipient != null) {
            return ("Sender: " + this.source + "\n" +
                    "Recipient: " + this.recipient + "\n" +
                    "Amount: $" + currencyFormat.format(this.amount) + "\n" +
                    "Date: " + this.date);
        } else {
            return ("Sender: " + this.source + "\n" +
                    "Amount: $" + currencyFormat.format(this.amount) + "\n" +
                    "Date: " + this.date);
        }
    }

    public double getAmount() {
        return this.amount;
    }

    public Date getDate() {
        return this.date;
    }

    // this creates a new object w the recipient and sender reversed
    public Transaction reverse(Transaction transaction) {
        return new Transaction(this.recipient, this.source, this.amount);
    }
}

