package atm.Model.accounts;

import atm.Model.users.User;

import java.util.HashMap;
import java.util.Map;

public class AccountFactory {

    private Map<AccountType, Integer> accountNumber = new HashMap<>();
    private User user;


    public AccountFactory(User user){
        this.user = user;
    }

    /** Creates a new account.*/
    public Account getAccount(AccountType accountType){

        switch (accountType) {
            case CHEQUING:
                Account chequing = new ChequingAccount();
                chequing.setNumber(createAccountHelper(AccountType.CHEQUING));
                return chequing;
            case SAVINGS:
                Account savingsAccount = new SavingsAccount();
                savingsAccount.setNumber(createAccountHelper(AccountType.SAVINGS));
                return savingsAccount;

            case LINEOFCREDIT:
                Account lineOfCredit = new LineOfCredit();
                lineOfCredit.setNumber(createAccountHelper(AccountType.LINEOFCREDIT));
                return  lineOfCredit;

            case CREDIT:
                Account creditCard = new CreditCard();
                creditCard.setNumber(createAccountHelper(AccountType.CREDIT));
                return creditCard;

            case JOINT:
                return new ChequingAccount();
            case LOTTERY:
                Account lotteryAccount = new LotteryAccount();
                lotteryAccount.setNumber(createAccountHelper(AccountType.LOTTERY));
                return  lotteryAccount;
        }

        return null;

    }

    /** Returns the number of the new account*/
    private Integer createAccountHelper(AccountType type) {
        int newNumber = 1;

        if (accountNumber.size() >= 1 && findType(type)){
            Integer number = accountNumber.get(type);
            newNumber = number+=1;
            accountNumber.replace(type, number, newNumber);
        }
        else{
            accountNumber.put(type, newNumber);
        }

        return newNumber;
    }

    /**Check if there is an instance of an account of AccountType type in the accounts list.*/
    private boolean findType(AccountType type) {

        for (Account acc : user.getAccounts()) {
            if (acc.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
