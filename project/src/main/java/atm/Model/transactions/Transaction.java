package atm.Model.transactions;

import atm.Model.Time;
import atm.Model.accounts.Account;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Transaction implements Serializable {

    private Account source;
    private Account recipient;
    private double amount;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private Time date;
    private static final long serialVersionUID = 100L;
    private String username = null;
    private TransactionType transactionType;


    /** Construct transaction information for internal transfers */
    public Transaction(Account source, Account recipient, double amount) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
        this.date = new Time();
        this.transactionType = TransactionType.InternalTransfer;
    }

    /** Construct transaction information for external transfers */
    public Transaction(Account source, Account recipient, double amount, String username) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
        this.date = new Time();
        this.username = username;
        this.transactionType = TransactionType.ExternalTransfer;
    }


    /** Construct transaction information for withdraw , deposit, pay bills*/
    public Transaction(Account source, double amount, TransactionType type) {
        this.source = source;
        this.amount = amount;
        this.date = new Time();
        this.transactionType = type;
    }


    @Override
    public String toString() {
        String strAmount = currencyFormat.format(this.amount);
        switch(transactionType){

            case ExternalTransfer:
                return ("$"+ strAmount + " transferred from " +
                    this.source + " to " + this.username +" on "+ this.date);
            case InternalTransfer:
                return ("$"+ strAmount + " transferred from " + this.source+ " to " + this.recipient+ " on "+ this.date);
            case Withdraw:
                return ("$"+ strAmount + " withdrawn  from " +this.source+ " on "+ this.date);
            case Deposit:
                return ("$"+ strAmount + " deposited to " +this.source+ " on "+ this.date);
            case PayBill:
                return ("$"+ strAmount + " paid by " +this.source+ " on "+ this.date);

        }
        return null;
    }

    public double getAmount() {
        return this.amount;
    }

    public Account getRecipient() {
        return this.recipient;
    }

    public Account getSource() {
        return this.source;
    }

    public Time getDate() {
        return this.date;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }
}

