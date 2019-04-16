package atm.Model.accounts;


import atm.Model.transactions.InsufficientFundsException;

/** Asset account that adds interest to account balance. */
public class SavingsAccount extends Account {

    public SavingsAccount() { super();
    type = AccountType.SAVINGS;
    }

    @Override
    public void setBalance(double amount) throws InsufficientFundsException {
        if (this.balance >= 0) {
            if (this.balance >= -amount || amount >= 0) {
                this.balance += amount;
            }
            else{
                InsufficientFundsException e = new InsufficientFundsException();
                throw e;
            }
        }
    }

    public void addInterest(int i) {
        double hold  = this.balance * 0.001 * i;
        this.balance = hold + this.balance;
    }


    @Override
    public String toString() {
        return "Savings Account " + accountNumber;
    }

}
