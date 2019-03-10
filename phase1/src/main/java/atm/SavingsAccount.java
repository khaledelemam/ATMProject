package atm;

import java.text.DecimalFormat;

public class SavingsAccount extends Account {
    private double balance;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    protected Transaction lastTransaction;

    public SavingsAccount() {
        this.balance = 0;
        this.lastTransaction = null;
    }

    @Override
    public String getBalance() {
        return currencyFormat.format(this.balance);
    }

    @Override
    public void setBalance(double amount) throws InsufficientFundsException {
        if (this.balance >= 0) {
            if (this.balance >= amount || amount >= 0) {
//           TODO: double check this logic: if ((amount < 0 && this.balance >= Math.abs(amount)) || amount >= 0) {
//                  ...above if condition might not take into account negative <amount> that is greater than <balance>
                this.balance += amount;
            }
            else{
                InsufficientFundsException e = new InsufficientFundsException();
                throw e;
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

    @Override
    public double getDoubleBalance() {return this.balance;}

    public void setLastTransaction(Transaction newTransaction) {
        this.lastTransaction = newTransaction;
    }

    @Override
    public double getNetTotal() {
        return this.balance;
    }

    public Transaction getLastTransaction() {
        return this.lastTransaction;
    }

}
