package atm;

public class SavingsAccount extends Account {

    public SavingsAccount(User owner) {
        super(owner);
    }

    public void setBalance(double amount) {
        if (this.balance >= 0) {
            if (this.balance >= amount || amount >= 0) {
                this.balance += amount;
            }
        }
    }

    public void addInterest() {
        this.balance = this.balance * 0.1;
    }

    @Override
    public String toString() {
        return "Savings Account";
    }


}
