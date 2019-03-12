package atm;


public class ChequingAccount extends Account {


    public ChequingAccount() {
    super();
    }

    @Override
    public String toString() {
        return "Chequing Account";
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

}
