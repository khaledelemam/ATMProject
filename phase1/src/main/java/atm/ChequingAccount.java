package atm;

import java.text.DecimalFormat;

public class ChequingAccount extends Account {
    private int balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");

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

    public void setBalance(double amount) {
        if (amount >= 0) {
            this.balance += amount;
        } else {
            if (balance >= 0 && (this.balance - amount) >= -100) {
                this.balance += amount;
            }
        }
    }
    @Override
    public double getDoubleBalance() {return this.balance;}
}
