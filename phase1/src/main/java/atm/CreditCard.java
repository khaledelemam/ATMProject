package atm;

public class CreditCard extends Account {
    private int balance;

    public CreditCard(User owner) {
        super(owner);
    }

    public int getBalance() {
        return this.balance;
    }

    // this takes in either a negative balance if money was taken from the account
    // or positive balance if money is added
    public void setBalance(int amount) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "Credit Card Account";
    }

}
