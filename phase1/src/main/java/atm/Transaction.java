package atm;

import java.io.Serializable;
import java.time.temporal.Temporal;

public class Transaction implements Serializable {
    //we should refactor the name of this to Transfer??

    private Account source;
    private Account recipient;
    private double amount;
    private Date date;
    private static final long serialVersionUID = 100L;

    // for  transfers
    // if its an external transfer, it should pass through <recipient user>.getPrimaryAccount()
    public Transaction(Account source, Account recipient, double amount) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
//        this.date = ...
    }

    // for paying bills
    // when it prints to outgoing:
    // Username, amount
    public Transaction(Account source, double amount) {
        this.source = source;
        this.amount = amount;
//        this.date = ...
    }


//    public Transaction(Account source, int amount) {
//        this.source = source;
//        this.amount = amount;
////        this.date = ...
//
//    }



    public double getAmount() {
        return this.amount;
    }


    public Date getDate() {
        return this.date;
    }


    // these will be used when the bank manager reverses a transaction

    // this creates a new object w the recipient and sender reversed
    public Transaction reverse(Transaction transaction) {
        return new Transaction(this.recipient, this.source, this.amount);
    }
}

