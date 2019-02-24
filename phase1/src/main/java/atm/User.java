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

    public User(String username) {
        this.numAccounts = 0;
        this.username = username;
        this.accounts = new HashMap<>();

        ChequingAccount firstAccount = new ChequingAccount(this);
        this.accounts.put(this.numAccounts, firstAccount);
    }


    public void setPassword (String password){
        this.password = password;
        this.username = username;

    }


    public String getUsername() {return this.username;}

    public String getPassword() {return this.password;}



}
