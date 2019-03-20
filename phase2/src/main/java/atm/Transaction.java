package atm;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.temporal.Temporal;

public class Transaction implements Serializable {

    private Account source;
    private Account recipient;
    private double amount;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private Time date;
    private static final long serialVersionUID = 100L;
    private String username = null;

    /** Construct transaction information for internal transfers */
    public Transaction(Account source, Account recipient, double amount) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
        this.date = new Time();
    }

    /** Construct transaction information for external transfers */
    public Transaction(Account source, Account recipient, double amount, String username) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
        this.date = new Time();
        this.username = username;
    }


    /** Construct transaction information for external transfers */
    public Transaction(Account source, double amount) {
        this.source = source;
        this.amount = amount;
        this.date = new Time();
    }



    @Override
    public String toString() {
        String strAmount = currencyFormat.format(this.amount);
        // external transfer
        if (this.recipient != null && this.username!=null) {
            return ("$"+ strAmount + " transferred from " +
                    this.source + " to " + this.username +" on "+ this.date);
        }
        //internal transfer
        else if(this.recipient != null){
            return ("$"+ strAmount + " transferred from " + this.source+ " to " + this.recipient+ " on "+ this.date);

        }
        //withdraw
        else {
            return ("$"+ strAmount + " withdrawn  from " +this.source+ " on "+ this.date);
        }
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
}

