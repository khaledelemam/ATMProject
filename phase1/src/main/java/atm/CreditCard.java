package atm;

public class CreditCard extends Account {

    public CreditCard(User owner) {
        super(owner);
    }

    @Override
    public String toString() {
        return "Credit Card Account";
    }

}
