package atm;

public class SavingsAccount extends Account {

    public SavingsAccount(User owner) {
        super(owner);
    }

    @Override
    public String toString() {
        return "Savings Account";
    }


}
