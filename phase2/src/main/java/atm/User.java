package atm;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private DecimalFormat currencyFormat = new DecimalFormat("0.00");
    private ArrayList<Account> accounts = new ArrayList<>();

    private AccountType request;
    private String joint;
    private Account JointAccount;
    private ChequingAccount primaryAccount;

    public User(String username) {
        this.username = username;
        this.primaryAccount = null;
    }

    @Override
    public String toString() {
        return this.username;
    }

    void setPassword (String password){
        this.password = password;
    }

    String getUsername() {return this.username;}

    String getPassword() {return this.password;}

    String getJoint() {return this.joint;}

    Account getJointAccount() { return this.JointAccount; }

    void setJoint(String username){
        this.joint = username;
    }

    void setPrimaryAccount(ChequingAccount account) {
        this.primaryAccount = account;
    }

    ArrayList<Account> getAccounts() { return accounts; }

    AccountType getRequest(){ return request; }

    ChequingAccount getPrimaryAccount() { return this.primaryAccount; }

    void createAccount(AccountType account){
        switch (account) {
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
                JointAccount = acct;
                addAccount(acct);
                break;
        }
    }

    void addAccount(Account account){
        accounts.add(account);
        request = null;
    }

    String viewAccountInto(Account account) {
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
        request = account;
        Database.store();
    }

    void requestJointAccount(String partner, AccountType account){
        request = account;
        joint = partner;
    }








}
