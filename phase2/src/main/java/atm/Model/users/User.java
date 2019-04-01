package atm.Model.users;

import atm.Model.Database;
import atm.Model.People;
import atm.Model.accounts.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends People implements Serializable {

    private static final long serialVersionUID = 42L;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private List<Account> accounts = new ArrayList<>();
    private AccountType accountRequest;
    private String partner;
    private Account jointAccount;
    private ChequingAccount primaryAccount;
    private Map<AccountType, Integer> accountNumber = new HashMap<>();

    public User(String username) {
        this.username = username;
        this.primaryAccount = null;
    }

    // important for bank teller
    public User(){}

    @Override
    public String toString() {
        return this.username;
    }

    public void setPassword (String password){ this.password = password;}

    public List<Account> getAccounts() { return accounts; }

    public AccountType getAccountRequest(){ return accountRequest; }

    public ChequingAccount getPrimaryAccount() { return this.primaryAccount; }

    public String getPartner() {return this.partner;}

    public Account getJointAccount() { return this.jointAccount; }

    /** Set the other user for a joint account.*/
    public void setPartner(String username){
        this.partner = username;
    }

    public void createAccount(AccountType accountType){
        System.out.println(accountType);

        switch (accountType) {
            case CHEQUING:
                ChequingAccount chequing = new ChequingAccount();
                if (primaryAccount == null) {
                    primaryAccount = chequing;
                }
                chequing.setNumber(createAccountHelper(AccountType.CHEQUING));
                addAccount(chequing);
                break;
            case SAVINGS:
                SavingsAccount savingsAccount = new SavingsAccount();
                savingsAccount.setNumber(createAccountHelper(AccountType.SAVINGS));
                addAccount(savingsAccount);

                break;
            case LINEOFCREDIT:
                DebtAccounts lineOfCredit = new LineOfCredit();
                lineOfCredit.setNumber(createAccountHelper(AccountType.LINEOFCREDIT));
                addAccount(lineOfCredit);
                break;
            case CREDIT:
                DebtAccounts creditCard = new CreditCard();
                creditCard.setNumber(createAccountHelper(AccountType.CREDIT));
                addAccount(creditCard);
                break;
            case JOINT:
                ChequingAccount acct = new ChequingAccount();
                acct.setJoint();
                acct.setNames(partner,getUsername());
                jointAccount = acct;
                addAccount(acct);
                break;
            case LOTTERY:
                SavingsAccount lotteryAccount = new LotteryAccount();
                lotteryAccount.setNumber(createAccountHelper(AccountType.LOTTERY));
                addAccount(lotteryAccount);
                break;
        }


        Database Database = new Database();
        Database.store();
    }

    public void addAccount(Account account){
        accounts.add(account);
        accountRequest = null;
    }

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
        for (Account acc : accounts) {
            if (acc.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
    /**View account balance, date opened and the last transaction to/from the given account.*/
    public String viewAccountInfo(Account account) {
        String accountInfo = "--------------------------\n" + account +
                "\n--------------------------\nBalance: $" + account.getBalance() +
                "\nDate opened: " + account.getDateOpened();
        if (account.getLastTransaction() != null) {
            accountInfo += "\n ------ Last Transaction ------ \n" + account.getLastTransaction();
        }
        return accountInfo;
    }

    public String netUserBalance() {
        double netTotal = 0;
        for (Account account : accounts) {
            netTotal += account.getNetTotal();
        }
        return currencyFormat.format(netTotal);
    }


    public void requestAccount(AccountType account){
        accountRequest = account;

        Database Database = new Database();
        Database.store();
    }

    // Overloading for joint accounts
    public void requestAccount(String name, AccountType account){
        accountRequest = account;
        partner = name;

        Database Database = new Database();
        Database.store();
    }
}
