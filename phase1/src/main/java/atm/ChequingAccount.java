package atm;

public class ChequingAccount extends Account {
    private int balance;

    public ChequingAccount(User owner) {
        super(owner);

    }

    @Override
    public String toString() {
        return "Chequing Account";
    }

    public void setBalance(double amount) {
        if (amount >= 0) {
            this.balance += amount;
        } else {
            if (balance >= 0 && (this.balance - amount) >= -100) {
                this.balance += amount;
            }
        }
    }
}
