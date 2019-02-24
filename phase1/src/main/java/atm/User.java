package atm;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {

    private String username;
    private String password;
    private int numAccounts;
    private HashMap<Integer, Account> accounts;
    private ChequingAccount primaryAccount;

    public User(String username) {
        this.numAccounts = 0;
        this.username = username;
        this.accounts = new HashMap<>();

        ChequingAccount firstAccount = new ChequingAccount(this);
        this.accounts.put(this.numAccounts, firstAccount);
    }


    public void setPassword (String password){
        this.password = password;
    }



    public String getUsername() {return this.username;}

    public String getPassword() {return this.password;}



}
