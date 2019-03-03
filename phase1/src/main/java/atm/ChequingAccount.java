package atm;

public class ChequingAccount extends Account {
    public ChequingAccount(User owner) {
        super(owner);

    }


    @Override
    public String toString() {
        return "Chequing Account";
    }

    // this takes in either a negative balance if money was taken from the account
    // or positive balance if money is added
    public void setBalance(double amount) {

        if  (this.balance < 100)

        this.balance += amount;
    }
}
