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
    private Account request = null;

    // I used two Hashmaps to track the account with the corresponding number. Two are used instead of one because there is no "getKey()" in Hashmaps.
    // This implementation is working so far in accessing user accounts.
    // I did not use arrayList because I could not track the accounts as users can add different accounts in different orders


    public User(String username) {
        this.numAccounts = 0;
        this.username = username;




        //There should not be a "default" account, the user requests the specific account
        // these accounts are just for testing purposes.

        SavingsAccount s = new SavingsAccount((this));
        this.accounts.put(1, s);
        this.map.put(s, 1);


        ChequingAccount c = new ChequingAccount(this);
        this.accounts.put(2, c);
        this.map.put(c, 2);




    }


    public void setPassword (String password){
        this.password = password;
    }

    public String getUsername() {return this.username;}

    public String getPassword() {return this.password;}


    public void addAccount(Account account){
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


    public void viewBalance (){
        for(int i = 1 ; i <=accounts.size(); i++){
            String str = Integer.toString(map.get(accounts.get(i)));
            System.out.println(accounts.get(i) +": " + accounts.get(i).getBalance()) ;

        }


    }


    public Account getRequest(){
        return request ;
    }

    public void requestAccount(Account account){
        request = account;
    }

}
