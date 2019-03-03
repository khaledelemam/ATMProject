package atm;

public class LineOfCredit extends Account {
    public LineOfCredit(User owner) {
       super(owner);
    }

    // this takes in either a negative balance if money was taken from the account
    // or positive balance if money is added
    public void setBalance(double amount) { this.balance -= amount; }

    @Override
    public String toString() {
        return "Line of Credit Account";
    }

}
