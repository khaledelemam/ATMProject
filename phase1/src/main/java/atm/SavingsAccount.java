package atm;

import java.text.DecimalFormat;

public class SavingsAccount extends Account {

    private double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");

    public SavingsAccount(User owner) {
        super(owner);
    }

    @Override
    public String getBalance() {
        return currencyFormat.format(this.balance);
    }

    public void setBalance(double amount) {
        if (this.balance >= 0) {
            if (this.balance >= amount || amount >= 0) {
                this.balance += amount;
            }
        }
    }

    public void addInterest() {
        this.balance = this.balance * 0.1;
    }

    @Override
    public String toString() {
        return "Savings Account";
    }


}
