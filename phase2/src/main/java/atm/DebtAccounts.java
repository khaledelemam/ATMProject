package atm;

/**
 * Accounts that accumulate debt. Positive net total means user owes money.
 */
public abstract class DebtAccounts extends Account {
    private double maxDebt = 500.;

    public DebtAccounts() {
        super();
    }
    @Override
    public void setBalance(double amount) throws InsufficientFundsException {
        if (this.balance - amount <= maxDebt) {
            this.balance -= amount;
        } else {
            InsufficientFundsException e = new InsufficientFundsException("You have reached your debt limit.");
            throw e;
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
