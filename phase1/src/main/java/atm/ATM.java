package atm;

import java.util.ArrayList;

public class ATM {
    private User user;
    private BankManager bankManager = new BankManager();

    protected boolean login(String username, String password) {
        // if username is valid
        // if password matches username
        // return true
        // else return false
        bankManager.retrieve();
        ArrayList<User> users = bankManager.users;

        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getUsername().equals(username)){
                if (users.get(i).getPassword().equals(password)){
                    System.out.println(5);
                    user = bankManager.users.get(i);
                    return true;

                }

            }

        }
        return false;


    }



    public boolean login2(String username){
        bankManager.retrieve();
        ArrayList<User> users = bankManager.users;

        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getUsername().equals(username)){
                    user = bankManager.users.get(i);
                    return true;
                }



        }
        return false;



    }



    public void newUser(String username, int account) {
        // call to BankManager to create a new user account with default password
        // cannot have two users with the same username
        // bank manager responds with a new user object that is printed so user knows their user/pass
        // and then this is returned
        user = new User(username);
        bankManager.setPassword(user);
        requestAccount(account);

        bankManager.store();


    }

    public void changePassword(String newPassword) {
        user.setPassword(newPassword);
    }

    public void viewAccounts() {
        // Accounts stored in HashMap<int, Account>
        // asks you to type in a number thats associated w one of the ints
        // then u can view the account info?
        //test
        user.viewAccounts();
    }

    public void internalTransfer(int from, int to , int amount) {
        Account accFrom = user.getAccount(from);
        Account  accTo = user.getAccount(to);
        accFrom.setBalance(-amount);
        accTo.setBalance(amount);
        bankManager.store();



    }

    public void externalTransfer(Account sender, User recipient,  int amount) {
        sender.setBalance((-amount));
        if (recipient.getPrimaryAccount() == null) {
            ChequingAccount to = recipient.getPrimaryAccount();
        }
        else {

        }
    }

    public void deposit(int account , int amount) {
        // is it cash or cheque


    }

    public void withdrawal(int account, int amount) {
        Account  acc = user.getAccount(account);
        acc.setBalance(-amount);
        bankManager.store();

    }


    public void payBill(){

    }


    public void viewBalance() {
        user.viewBalance();
    }

    public void requestAccount(int account){
        if (account == 1){
            user.requestAccount("Chequing");

        }
        else if (account == 2){
            user.requestAccount("Savings");

        }
        else if (account == 3){
            user.requestAccount("Line of Credit");

        }
        else if (account == 4){
            user.requestAccount("Credit Card");

        }

        bankManager.store();



    }



    public boolean adminCheck(String password){

        if(password.equals(bankManager.getPassword())){
            return true;
        }
        else return false;
    }


   // temporary method to test account creation by bank manager
    public void testAccountCreation() {
        bankManager.userRequestAccount();
        bankManager.store();

    }

    public String getUserPassword(){
            return user.getPassword();
    }


}
