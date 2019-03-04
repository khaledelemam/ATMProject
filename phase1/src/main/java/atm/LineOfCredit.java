package atm;

import java.text.DecimalFormat;

public class LineOfCredit extends Account {
    private int balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");


    public LineOfCredit(User owner) {
       super(owner);
    }

    @Override
    public String getBalance() {
        return currencyFormat.format(this.balance);
    }


    public void setBalance(double amount) { this.balance -= amount; }

    @Override
    public String toString() {
        return "Line of Credit Account";
    }

}
