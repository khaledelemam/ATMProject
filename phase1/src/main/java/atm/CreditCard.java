package atm;

public class CreditCard extends Account {
    private int balance;

    public CreditCard(User owner) {
        super(owner);
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Credit Card Account";
    }

}
