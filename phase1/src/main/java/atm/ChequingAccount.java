package atm;

import java.text.DecimalFormat;

public class ChequingAccount extends Account {
    private Date dateOpened;
    private double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    protected Transaction lastTransaction;

    public ChequingAccount(User owner) {
        super(owner);

    }
    @Override
    public String getBalance() {
        return currencyFormat.format(this.balance);
    }

    @Override
    public String toString() {
        return "Chequing Account";
    }

    @Override
    public void setBalance(double amount) throws InsufficientFundsException {
        if (amount >= 0) {
            this.balance += amount;
        } else {
            if (balance >= 0 && (this.balance - amount) >= -100) {
                this.balance += amount;
            }
            else {
                InsufficientFundsException e = new InsufficientFundsException();
                throw e;
            }
        }
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
