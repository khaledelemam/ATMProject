package atm;

/** Savings account that applies random interest on balance. */
public class LotteryAccount extends SavingsAccount {

    public LotteryAccount(){
        super();
        type = AccountType.LOTTERY;
    }

    @Override
    public void addInterest(int i) {
        double randomInterest = Math.random();
        double hold  = this.balance * randomInterest * i;
        this.balance = hold + this.balance;
    }

    @Override
    public String toString() {
        return "Lottery Account " + accountNumber;
    }
}
