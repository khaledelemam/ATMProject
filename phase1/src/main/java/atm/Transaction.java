package atm;

public class Transaction {

    private Object source;
    private Object recipient;
    private int amount;
    private Date date;


    // for external transfers
    public Transaction(User source, User recipient, int amount) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
//        this.date = ...we have to figure out this date class thing lmao
    }

    // for internal transfers
    public Transaction(Account source, Account recipient, int amount) {
        this.source = source;
        this.recipient = recipient;
        this.amount = amount;
//        this.date = ...
    }

    // for paying bills
    public Transaction(Account source, int amount) {
        this.source = source;
        this.amount = amount;
//        this.date = ...
    }

    public int getAmount() {
        return amount;
    }

    public Object getRecipient() {
        return recipient;
    }

    public Object getSource() {
        return source;
    }

    public Date getDate() {
        return date;
    }


    // these will be used when the bank manager reverses a transaction

    public void setSender(Object source) {
        this.source = this.source;

    }

    public void setRecipient(Object recipient) {
        this.recipient = recipient;
    }
}
