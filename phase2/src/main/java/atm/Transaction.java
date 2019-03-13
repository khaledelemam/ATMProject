package atm;

import java.io.Serializable;
import java.time.temporal.Temporal;

public class Transaction implements Serializable {

    private Account source;
    private Account recipient;
    private double amount;
    private Date date;
    private static final long serialVersionUID = 100L;
    private String username = null;

    // for  internal transfers
    public Transaction(Account source, Account recipient, double amount) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
        this.date = new Date();
    }
    //for external transfers
    public Transaction(Account source, Account recipient, double amount, String username) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
        this.date = new Date();
        this.username = username;
    }


    // this is used for withdrawals
    public Transaction(Account source, double amount) {
        this.source = source;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        // external transfer
        if (this.recipient != null && this.username!=null) {
            return ("$"+ this.amount + " transfered " + "from " +this.source+ " to " + this.username +" on "+ this.date);
//            return ("Sender: " + this.source + "\n" +
//                    "Recipient: " + this.recipient + "\n" +
//                    "Amount: " + this.amount + "\n" +
//                    "Date: " + this.date);
        }
        //internal transfer
        else if(this.recipient != null){
            return ("$"+ this.amount + " transfered " + "from " +this.source+ " to " + this.recipient+ " on "+ this.date);

        }
        //withdraw
        else {
            return ("$"+ this.amount + " withdrawn " + "from " +this.source+ " on "+ this.date);
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

    public Date getDate() {
        return this.date;
    }
}

