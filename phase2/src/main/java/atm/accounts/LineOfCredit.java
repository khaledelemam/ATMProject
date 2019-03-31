package atm.accounts;


import atm.accounts.AccountType;
import atm.accounts.DebtAccounts;

public class LineOfCredit extends DebtAccounts {


    public LineOfCredit() {
        super();
        type = AccountType.LINEOFCREDIT;
    }


    @Override
    public String toString() {
        return "Line of Credit Account " + accountNumber;
    }

}
