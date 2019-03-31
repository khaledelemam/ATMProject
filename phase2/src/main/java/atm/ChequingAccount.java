package atm;


public class ChequingAccount extends Account implements JointAccount{

    private boolean joint = false;
    private String partner;
    private String user;

    public ChequingAccount() {
    super();
    type = AccountType.CHEQUING;
    }

    @Override
    public String toString() {
        if (joint){
            return "Joint Chequing Account between " + user + " and " + partner;
        }
        else{
            return "Chequing Account " + accountNumber;
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

    public void setJoint(){
        joint = true;
    }

    public void setNames(String partnerName, String username){
        partner= partnerName;
        user = username;
    }

}
