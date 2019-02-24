package atm;

import java.util.HashMap;

public class User {

    private String username;
    private String password;
    private int numAccounts;
    private HashMap<Integer, Account> accounts;
    private ChequingAccount primaryAccount;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.numAccounts = 0;

        this.accounts = new HashMap<>();

        ChequingAccount firstAccount = new ChequingAccount(this);
        this.accounts.put(this.numAccounts, firstAccount);
    }

    public void changePassword (){

    }

}
