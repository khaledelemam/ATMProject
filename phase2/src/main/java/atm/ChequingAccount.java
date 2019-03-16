package atm;


public class ChequingAccount extends Account {

    private boolean joint = false;

    public ChequingAccount() {
    super();
    }

    @Override
    public String toString() {
        if (joint){
            return " Joint Chequing Account";
        }
        else{
            return "Chequing Account";
        }
    }

    @Override
    public void setBalance(double amount) throws InsufficientFundsException {
        if (amount >= 0) {
            this.balance += amount;
        } else {
            if (balance >= 0 && (this.balance - amount) >= -100) {
                this.balance += amount;
            }
            else {
                InsufficientFundsException e = new InsufficientFundsException();
                throw e;
            }
        }
    }

    public double getNetTotal(){
        return balance;
    }

    public void setJoint(){
        joint = true;
    }

}
