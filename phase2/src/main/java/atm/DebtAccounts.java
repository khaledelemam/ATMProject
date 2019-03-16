package atm;

public class DebtAccounts extends Account {
    private double maxDebt = 500.;

    public DebtAccounts() {
        super();
    }

    @Override
    public void setBalance(double amount) {
        if (this.balance - amount >= -maxDebt) {
            this.balance -= amount;
        } else {
            System.out.println("You have reached the max debt.");
        }

    }

    @Override
    public double getNetTotal() {
        return -this.balance;
    }

    // For now, this is for testing. Can remove if we don't want this later
    public void setMaxDebt(double newDebtMax) {
        maxDebt = newDebtMax;
    }

}
