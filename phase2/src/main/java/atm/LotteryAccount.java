package atm;

public class LotteryAccount extends SavingsAccount {

    //apply random interest on balance
    public LotteryAccount(){
        super();
    }

    @Override
    public void addInterest(int i) {
        double randomInterest = Math.random();
        double hold  = this.balance * randomInterest * i;
        this.balance = hold + this.balance;
    }

    @Override
    public String toString() {
        return "Lottery Account";
    }
}
