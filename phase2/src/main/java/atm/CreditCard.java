package atm;


public class CreditCard extends Account {

    public CreditCard() {
       super();
    }
    public void setBalance(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Credit Card Account";
    }


    @Override
    public double getNetTotal() {
        return -this.balance;
    }


}
