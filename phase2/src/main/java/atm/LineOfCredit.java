package atm;


public class LineOfCredit extends Account {


    public LineOfCredit() {
        super();
    }

    @Override
    public void setBalance(double amount) { this.balance -= amount; }

    @Override
    public String toString() {
        return "Line of Credit Account";
    }


    @Override
    public double getNetTotal() {
        return -this.balance;
    }



}
