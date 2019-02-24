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
        bankManager.retreive();
        ArrayList<User> users = BankManager.users;

        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getUsername().equals(username)){
                if (users.get(i).getPassword().equals(password)){
                    user = BankManager.users.get(i);
                    return true;

                }

            }

        }
        return false;


    }

    public void newUser(String username) {
        // call to BankManager to create a new user account with default password
        // cannot have two users with the same username
        // bank manager responds with a new user object that is printed so user knows their user/pass
        // and then this is returned
        user = new User(username);
        bankManager.setPassword(user);
        bankManager.store();


    }

    public void changePassword(String newPassword) {
        user.setPassword(newPassword);
    }

    public void viewAccounts() {
        // Accounts stored in HashMap<int, Account>
        // asks you to type in a number thats associated w one of the ints
        // then u can view the account info?
    }

    public void internalTransfer(Account sender, Account recipient, int amount) {

    }

    public void externalTransfer(Account sender, User recipient,  int amount) {

    }

    public void deposit(String type, int amount) {
        // is it cash or cheque
    }

    public void withdrawal(Account account, int amount) {


    }

    public void payBill(Account account, int amount) {

    }

}
