package atm;

public class Transaction {

    private User sender;
    private User recipient;
    private int amount;
    private Date date;

    public Transaction(User sender, User recipient, int amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
//        this.date = ...
    }

    public int getAmount() {
        return amount;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Date getDate() {
        return date;
    }


    // these will be used when the bank manager reverses a transaction

    public void setSender(User sender) {
        this.sender = this.recipient;

    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
