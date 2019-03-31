package atm.accounts;


public class CreditCard extends DebtAccounts {

    public CreditCard() { super();
    type = AccountType.CREDIT;}

    @Override
    public String toString() {
        return "Credit Card Account " + accountNumber;
    }


}
