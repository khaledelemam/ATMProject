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
//    private HashMap<Integer,Account> accounts = new HashMap<>();
//    private HashMap<Account,Integer> map = new HashMap<>();
    // TODO: see if this works lol
    private ArrayList<Account> accounts = new ArrayList<>();

    private AccountType request;
    private String joint;
    private Account JointAccount;
    private ChequingAccount primaryAccount;

    // I used two Hashmaps to track the account with the corresponding number. Two are used instead of one because there is no "getKey()" in Hashmaps.
    // This implementation is working so far in accessing user accounts.
    // I did not use arrayList because I could not track the accounts as users can add different accounts in different orders


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


    void createAccount(AccountType account){

//        Account hold = null;

//        if (account.equals("Chequing")){
//            hold = new ChequingAccount();
////            if (primaryAccount == null)
////            primaryAccount = (ChequingAccount) hold;
//
//        }
//
//        else if (account.equals("Savings")){
//            hold = new SavingsAccount();
//        }
//        else if (account.equals("Line of Credit")){
//            hold = new LineOfCredit();
//        }
//
//        else if (account.equals("Credit Card")){
//            hold = new CreditCard();
//        }
//
//        else if (account.equals("Joint Account")){
//            hold = new ChequingAccount();
//            ((ChequingAccount) hold).setJoint();
//            JointAccount=hold;
//        }
        // TODO: make sure this works correctly!
        switch (account) {
            case CHEQUING:
                addAccount(new ChequingAccount());
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

//        if (hold != null) {
//            addAccount(hold);
//        }
    }

    void addAccount(Account account){

//        int index = accounts.size();
//        this.accounts.put(index + 1, account);
//        this.map.put(account, index + 1);
//        request = null;
        accounts.add(account);
        request = null;

    }

    //    void viewAccounts(){
//        for(int i = 1 ; i <=accounts.size(); i++){
//            String str = Integer.toString(map.get(accounts.get(i)));
//            System.out.println("(" +str + ")" +accounts.get(i));
//
//        }
//    }

//    Account getAccount(int acc){
//        for(int i = 1 ; i <= accounts.size(); i++){
//            if (acc == i) {
//                return accounts.get(i);
//            }
//        }
//        return null;
//    }

//    String viewAccountInfo(int index) {
//        Account account = getAccount(index);
    String viewAccountInto(Account account) {
        String accountInfo = "--------------------------\n" + account +
                "\n--------------------------\nBalance: $" + account.getBalance() +
                "\nDate opened: " + account.getDateOpened();
        if (account.getLastTransaction() != null) {
            accountInfo += "\n ------ Last Transaction ------ \n" + account.getLastTransaction();
        }
        return accountInfo;
    }

//    private ArrayList<String> accountInfo() {
//
//        ArrayList<String> accountsInfo = new ArrayList<>();
//        for(int i = 1 ; i <= accounts.size(); i++) {
//            Account account = accounts.get(i);
//            if (account.getLastTransaction() != null) {
//                accountsInfo.add("\n" + account + ": $" + account.getBalance() +
//                        "\nDate opened: " + account.getDateOpened() +
//                        "\n--Last Transaction--\n" + account.getLastTransaction());
//            } else {
//                accountsInfo.add("\n" + account + ": $" + account.getBalance() +
//                            "\nDate opened: " + account.getDateOpened());
//            }
//        }
//        return accountsInfo;
//    }

//    ArrayList<String> getAccounts() {
//        ArrayList<String> allAccounts = new ArrayList<>();
//        for(int i = 1; i <= accounts.size(); i++) {
//            Account account = accounts.get(i);
//            allAccounts.add(account.toString());
//        }
//        return allAccounts;
//    }
    ArrayList<Account> getAccounts() {
        return accounts;
    }


//    String viewAccountsInfo() {
//
//        String accInfo = "Net total: " + netUserBalance() + "\n";
//        for (String account: accountInfo()) {
//            accInfo += account + "\n";
//        }
//        return accInfo;
//    }


    AccountType getRequest(){
        return request;
    }

//    Account getPrimaryAccount() { return accounts.get(1);}
    Account getPrimaryAccount() {
        return primaryAccount;
    }

    public String netUserBalance() {
        double netTotal = 0;
//        for (int k = 1; k <= accounts.size(); k++){
//            netTotal += accounts.get(k).getNetTotal();
//        }
        for (Account account : accounts) {
            netTotal += account.getNetTotal();
        }
        return currencyFormat.format(netTotal);
    }


    // TODO: can this be overloaded???????

    void requestAccount(AccountType account){
        request = account;

//        if (account == 1){
//            request = "Chequing";
//
//        }
//        else if (account == 2){
//          request =  "Savings";
//
//        }
//        else if (account == 3){
//         request = "Line of Credit";
//
//        }
//        else if (account == 4){
//          request = "Credit Card";
//        }

        Database.store();
    }

    void requestJointAccount(String partner, AccountType account){
        request = account;
        joint = partner;
    }








}
