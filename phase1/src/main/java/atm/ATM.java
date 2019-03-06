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



    public void newUser(String username, int account) throws UsernameTakenException{
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
        if (checkExistingUser(username) != null){
            UsernameTakenException u = new UsernameTakenException();
            throw u;
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
    public void externalTransfer(int sender, User recipient,  int amount) throws InsufficientFundsException {
        if (recipient.getPrimaryAccount() != null) {
            Account accFrom = user.getAccount(sender);
            ChequingAccount to = recipient.getPrimaryAccount();
            to.setBalance(amount);
            accFrom.setBalance(-amount);
            Transaction extTransfer = new Transaction(accFrom, to, amount);
            accFrom.setLastTransaction(extTransfer);
            to.setLastTransaction(extTransfer);
        }
        bankManager.store();
    }

    public void deposit() throws IOException, InsufficientFundsException {
        File deposits = new File("phase1/src/main/java/atm/deposits.txt");
        BufferedReader depositReader = new BufferedReader(new FileReader(deposits));
        ArrayList<String[]> todaysDeposits = new ArrayList<>();

        String line = depositReader.readLine();

        Date testDate = new Date();

        while (line != null) {
            if (line.equals(testDate.toString())) {
                line = depositReader.readLine();
                while (!(line.equals(""))) {
                    String[] deposit = line.split(" ");
                    todaysDeposits.add(deposit);
                    line = depositReader.readLine();
                }
                depositReader.close();
                break;
            }
            line = depositReader.readLine();
        }

        for (String[] item: todaysDeposits) {
            String username = item[0];
            String type = item[1];
            Double amount = Double.parseDouble(item[2]);

            // check if user is in system

            user = checkExistingUser(username);
            if (user != null) {
                user.getPrimaryAccount().setBalance(amount);
                bankManager.store();
            }
        }
    }

    public void withdrawal(int account, int amount) throws InsufficientFundsException{
        Account  acc = user.getAccount(account);
        acc.setBalance(-amount);
        Transaction withdrawal = new Transaction(acc, amount);
        acc.setLastTransaction(withdrawal);
        bankManager.store();
    }


    public void payBill(int account, int amount) throws IOException, InsufficientFundsException {
        Account acc = user.getAccount(account);
        acc.setBalance(-amount);


        File outgoing = new File("phase1/src/main/java/atm/outgoing.txt");
        System.out.println(outgoing.canWrite());
        System.out.println(outgoing.getAbsoluteFile());
        System.out.println(outgoing.getCanonicalPath());
        PrintWriter billPayer = new PrintWriter(new FileWriter("phase1/src/main/java/atm/outgoing.txt", true));
        billPayer.println(user + " paid $" + amount + " on " + date);
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


    public User checkExistingUser(String username) {
//        File f = new File("file");
//        if (f.exists()) {
//            bankManager.retrieve();
//        }
//        ArrayList<User> users = bankManager.users;
//
//        for (int i = 0; i < users.size(); i ++) {
//            if (users.get(i).getUsername().equals(username)){
//                return users.get(i);
//            }
//        }
//        bankManager.store();
//        return null;
       return  bankManager.checkExistingUser(username);
    }


    public void date(){
        bankManager.setDate();
    }

    // does not update user balance correctly
    public void reverseTransaction(String username, int account) throws InsufficientFundsException{
        bankManager.ReverseLastTransaction(username,account);
        bankManager.store();
    }

    public void viewAccounts(String name){
        User user  = checkExistingUser(name);
        user.viewAccounts();

    }


}
