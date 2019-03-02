package atm;

public class LineOfCredit extends Account {
    private int balance;

    public LineOfCredit(User owner) {
       super(owner);
    }


    public int getBalance() {
        return this.balance;
    }

    // this takes in either a negative balance if money was taken from the account
    // or positive balance if money is added
    public void setBalance(int amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "Line of Credit Account";
    }

}
