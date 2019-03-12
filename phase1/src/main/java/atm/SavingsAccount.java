package atm;


public class SavingsAccount extends Account {

    public SavingsAccount() {
        super();
    }

    @Override
    public void setBalance(double amount) throws InsufficientFundsException {
        if (this.balance >= 0) {
            if (this.balance >= amount || amount >= 0) {
                this.balance += amount;
            }
            else{
                InsufficientFundsException e = new InsufficientFundsException();
                throw e;
            }
        }
    }

    public void addInterest() {
        double hold  = this.balance * 0.1;
        this.balance = hold + this.balance;

    }

    @Override
    public String toString() {
        return "Savings Account";
    }

    @Override
    public double getNetTotal() {
        return this.balance;
    }

}
