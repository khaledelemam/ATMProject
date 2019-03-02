package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private int numAccounts;
    private HashMap<Integer,Account> accounts = new HashMap<>();
    private HashMap<Account,Integer> map = new HashMap<>();
    private Account request;


    public User(String username) {
        this.numAccounts = 0;
        this.username = username;
//        this.accounts = new ArrayList<>();

        ChequingAccount firstAccount = new ChequingAccount(this);

        this.accounts.put(1, firstAccount);
        this.map.put(firstAccount, 1);

    }


    public void setPassword (String password){
        this.password = password;
    }

    public String getUsername() {return this.username;}

    public String getPassword() {return this.password;}


    public void addAccount(){
        SavingsAccount s = new SavingsAccount((this));
        this.accounts.put(2, s);
        this.map.put(s, 2);

    }

    public void viewAccounts(){
        for(int i = 1 ; i <=accounts.size(); i++){
            String str = Integer.toString(map.get(accounts.get(i)));
            System.out.println("(" +str + ")" +accounts.get(i));

        }


    }

    public Account getAccount(int acc){

        for(int i = 1 ; i <=accounts.size(); i++){
            if (acc== i) {
                return accounts.get(i);
            }
        }
        return null;

    }

    public void requestAccount(Account account){
        request = account;
    }

}
