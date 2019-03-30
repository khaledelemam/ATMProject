package atm;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class User extends People implements Serializable {

    private static final long serialVersionUID = 42L;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private List<Account> accounts = new ArrayList<>();
    private AccountType accountRequest;
    private String joint;
    private Account jointAccount;
    private ChequingAccount primaryAccount;

    public User(String username) {
        this.username = username;
        this.primaryAccount = null;
    }

    // important for bank teller
    public User(){
    }

    @Override
    public String toString() {
        return this.username;
    }

    void setPassword (String password){ this.password = password;}

    String getJoint() {return this.joint;}

    Account getJointAccount() { return this.jointAccount; }

    void setJoint(String username){
        this.joint = username;
    }

    List<Account> getAccounts() { return accounts; }

    AccountType getAccountRequest(){ return accountRequest; }

    ChequingAccount getPrimaryAccount() { return this.primaryAccount; }

    void createAccount(AccountType accountType){
        System.out.println(accountType);

        switch (accountType) {
            case CHEQUING:
                ChequingAccount chequing = new ChequingAccount();
                addAccount(chequing);
                if (primaryAccount == null) {
                    primaryAccount = chequing;
                }
                break;
            case SAVINGS:
                addAccount(new SavingsAccount());
                break;
            case LINEOFCREDIT:
                addAccount(new LineOfCredit());
                break;
            case CREDIT:
                addAccount(new CreditCard());
                break;
            case JOINT:
                // TODO: ??????
                Account acct = new ChequingAccount();
                ((ChequingAccount) acct).setJoint();
                jointAccount = acct;
                addAccount(acct);
                break;
            case LOTTERY:
                addAccount(new LotteryAccount());
                break;
        }


        Database Database = new Database();
        Database.store();
    }

    void addAccount(Account account){
        accounts.add(account);
        accountRequest = null;
    }

    String viewAccountInfo(Account account) {
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


    // TODO: can this be overloaded???????
    void requestAccount(AccountType account){
        accountRequest = account;

        Database Database = new Database();
        Database.store();
    }

    // Overloading for joint accounts
    void requestAccount(String partner, AccountType account){
        accountRequest = account;
        joint = partner;

        Database Database = new Database();
        Database.store();
    }

    public boolean isEmployee(){
        return false;
    }

//    public Account getSpecificAccount()






}
