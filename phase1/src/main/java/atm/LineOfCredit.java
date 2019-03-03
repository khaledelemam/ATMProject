package atm;

public class LineOfCredit extends Account {
    private int balance;

    public LineOfCredit(User owner) {
       super(owner);
    }

    public void setBalance(double amount) { this.balance -= amount; }

    @Override
    public String toString() {
        return "Line of Credit Account";
    }

}
