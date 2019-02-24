package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private int numAccounts;
    private ArrayList<Account> accounts = new ArrayList<>();
    private HashMap<Integer, Account> acc = new HashMap<>();
    private ChequingAccount primaryAccount;


    public User(String username) {
        this.numAccounts = 0;
        this.username = username;
//        this.accounts = new HashMap<>();

        ChequingAccount firstAccount = new ChequingAccount(this);
        accounts.add(firstAccount);
        this.acc.put(0, firstAccount);
    }


    public void setPassword (String password){
        this.password = password;
    }


    public String getUsername() {return this.username;}

    public String getPassword() {return this.password;}


    public void viewAccounts(){
        for (int i = 0; i < accounts.size(); i++){
            if (accounts.get(i) instanceof ChequingAccount)
            System.out.println("("+(i)+")" + " Chequing Account");
            else if (accounts.get(i) instanceof SavingsAccount)
                System.out.println("("+(i)+")"  + "Savings Account");
            else if (accounts.get(i) instanceof CreditCard)
                System.out.println("("+(i)+")"  + "Credit Card Account");
            else if (accounts.get(i) instanceof LineOfCredit)
                System.out.println("("+(i)+")"  + "Line of Credit Account");
        }


    }






}
