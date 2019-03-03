package atm;

public class CreditCard extends Account {
    private int balance;

    public CreditCard(User owner) {
        super(owner);
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Credit Card Account";
    }

}
