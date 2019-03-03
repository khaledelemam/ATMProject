package atm;

public class CreditCard extends Account {
    public CreditCard(User owner) {
        super(owner);
    }

    public void setBalance(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Credit Card Account";
    }

}
