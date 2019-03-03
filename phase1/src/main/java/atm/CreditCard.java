package atm;

import java.text.DecimalFormat;

public class CreditCard extends Account {
    private int balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");

    public CreditCard(User owner) {
        super(owner);

    }
    @Override
    public String getBalance() {
        return currencyFormat.format(this.balance);
    }

    public void setBalance(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Credit Card Account";
    }

}
