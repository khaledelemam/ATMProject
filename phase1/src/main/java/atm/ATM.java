package atm;


import java.io.*;
import java.util.ArrayList;

public class ATM {

    private User user;
    private BankManager bankManager = new BankManager();
    private Date date;

    protected boolean login(String username, String password) {
        // if username is valid
        // if password matches username
        // return true
        // else return false
        bankManager.retrieve();

        ArrayList<User> users = bankManager.users;
        for (int i = 0; i < users.size(); i ++){
            System.out.println(users.get(i).getUsername());
        }
        for (int i = 0; i < users.size(); i ++) {

            if (users.get(i).getUsername().equals(username)){
                if (users.get(i).getPassword().equals(password)){
                    System.out.println(5);
                    user = bankManager.users.get(i);
                    return true;

                }

            }

        }
        bankManager.store();
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
        bankManager.store();
        return false;



    }



    public void newUser(String username, int account) {
        // call to BankManager to create a new user account with default password
        // cannot have two users with the same username
        // bank manager responds with a new user object that is printed so user knows their user/pass
        // and then this is returned
        File f = new File("file2");
        if (f.exists()) {
            bankManager.retrieveRequests();
        }
       String request = null;

        if (account == 1){
            request = "Chequing";
        }
        else if (account == 2){
            request ="Savings";
        }
        else if (account == 3){
            request =  "Line of Credit";

        }
        else if (account == 4){
            request =  "Credit Card";

        }

        ArrayList<String> arr = new ArrayList<>();
        arr.add(username);
        arr.add(request);

        bankManager.requests.add(arr);
        bankManager.storeRequests();


    }

    public void changePassword(String newPassword) {
        user.setPassword(newPassword);
        bankManager.store();

    }

    public void viewAccounts() {
        // Accounts stored in HashMap<int, Account>
        // asks you to type in a number thats associated w one of the ints
        // then u can view the account info?
        //test
        user.viewAccounts();
    }

    public void internalTransfer(int from, int to , int amount) throws InsufficientFundsException {
        Account accFrom = user.getAccount(from);
        Account  accTo = user.getAccount(to);
        accFrom.setBalance(-amount);
        accTo.setBalance(amount);
        Transaction intTransfer = new Transaction(accFrom,accTo,amount);
        accTo.setLastTransaction(intTransfer);
        accFrom.setLastTransaction(intTransfer);
        bankManager.store();



    }
    public void externalTransfer(Account sender, User recipient,  int amount) throws InsufficientFundsException {
        if (recipient.getPrimaryAccount() != null) {
            ChequingAccount to = recipient.getPrimaryAccount();
            to.setBalance(amount);
            sender.setBalance(-amount);
            Transaction extTransfer = new Transaction(sender, to, amount);
            sender.setLastTransaction(extTransfer);
            to.setLastTransaction(extTransfer);
        }
        bankManager.store();
    }

    public void deposit() throws IOException {
        File deposits = new File("deposits.txt");
        BufferedReader depositReader = new BufferedReader(new FileReader(deposits));
        ArrayList<String[]> todaysDeposits = new ArrayList<>();

        String line = depositReader.readLine();

        while (line != null) {
            if (line.equals(date.toString())) {
                while (line.equals("\n")) {
                    line = depositReader.readLine();
                    String[] deposit = line.trim().split(" ");
                    todaysDeposits.add(deposit);
                }
            }
            line = depositReader.readLine();
        }

        depositReader.close();

        for (String[] item: todaysDeposits) {
            String username = item[0];
            String type = item[1];
            Double amountToDeposit = Double.parseDouble(item[3]);

            // check if user is in system
            if (checkExistingUser(username) != null) {
                user = checkExistingUser(username);
                user.getPrimaryAccount().setBalance(amountToDeposit);
            }
        }
    }

    public void withdrawal(int account, int amount) throws InsufficientFundsException{
        Account  acc = user.getAccount(account);
        //checkSufficientFunds(acc, amount);
        acc.setBalance(-amount);
        bankManager.store();
    }


    public void payBill(int account, int amount) throws IOException, InsufficientFundsException {
        Account acc = user.getAccount(account);
        acc.setBalance(-amount);


        File outgoing = new File("outgoing.txt");
        System.out.println(outgoing.canWrite());
        System.out.println(outgoing.getAbsoluteFile());
        System.out.println(outgoing.getCanonicalPath());
        PrintWriter billPayer = new PrintWriter(new FileWriter("outgoing", true));
        billPayer.println(user + " payed $" + amount + " on " + date);
        billPayer.close();

        bankManager.store();
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



    public void newAccountCreation() {
        File f = new File("file");
        if (f.exists()) {
            bankManager.retrieve();
        }
        bankManager.createUser();
        bankManager.store();

    }

    public void usersRequests(){
        bankManager.retrieve();
        bankManager.userRequestAccount();
        bankManager.store();
    }

    public String getUserPassword(){
            return user.getPassword();
    }

    public void checkSufficientFunds (Account acc, int amount)throws InsufficientFundsException{
        if (acc.getDoubleBalance() < amount){
            InsufficientFundsException e = new InsufficientFundsException();
            throw e;
        }
    }
    public User checkExistingUser(String username){
        bankManager.retrieve();
        ArrayList<User> users = bankManager.users;

        for (int i = 0; i < users.size(); i ++) {
            if (users.get(i).getUsername().equals(username)){
                users.get(i);
            }
        }
        bankManager.store();
        return null;
    }


}
