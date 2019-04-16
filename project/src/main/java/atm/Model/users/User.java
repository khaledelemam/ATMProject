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
    private Account primaryAccount;

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

    public Account getPrimaryAccount() { return this.primaryAccount; }

    public String getPartner() {return this.partner;}

    public Account getJointAccount() { return this.jointAccount; }

    /** Set the other user for a joint account.*/
    public void setPartner(String username){ this.partner = username;}


    public void createAccount(AccountType accountType){

        AccountFactory accountFactory = new AccountFactory(this);
        Account account = accountFactory.getAccount(accountType);
        addAccount(account);

        if (primaryAccount == null) {
            primaryAccount = account;
        }

        if (accountType == AccountType.JOINT){
            ((ChequingAccount)account).setJoint();
            ((ChequingAccount)account).setNames(partner,username);
            jointAccount = account;
        }

        Database Database = new Database();
        Database.store();
    }

    public void addAccount(Account account){
        accounts.add(account);
        accountRequest = null;
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
