package atm;


import java.io.*;
import java.util.ArrayList;

public class ATM {

    private User user;
    private BankManager bankManager = new BankManager();
    private CashManager cashManager = new CashManager();
    private Date date = new Date();

    public ATM() throws IOException {
    }

    boolean login(String username, String password) {
        Database.retrieve();

        ArrayList<User> users = Database.users;
        for (int i = 0; i < users.size(); i ++) {

            if (users.get(i).getUsername().equals(username)){
                if (users.get(i).getPassword().equals(password)){
                    user = Database.users.get(i);
                    return true;

                }

            }

        }
       Database.store();
        return false;

    }


    boolean adminCheck(String password){

        if(password.equals(bankManager.getPassword())){
            return true;
        }
        else return false;
    }



    void deposit() throws IOException, InsufficientFundsException {
        File deposits = new File("phase2/src/main/java/atm/deposits.txt");
        BufferedReader depositReader = new BufferedReader(new FileReader(deposits));
        ArrayList<String[]> todaysDeposits = new ArrayList<>();

        String line = depositReader.readLine();

        File f = new File(Date.getFilename());
        if (f.exists()) {
            date.setToday();
        }
        while (line != null) {
            if (line.equals(date.toString())) {
                line = depositReader.readLine();

                while (!(line.equals(""))) {
                    String[] deposit = line.split(" ");
                    todaysDeposits.add(deposit);
                    line = depositReader.readLine();
                    if (line == null) break;
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
            user = Database.checkExistingUserDeposit(username);
            if (user != null) {
                user.getPrimaryAccount().setBalance(amount);
                Database.store();
            }


//                if (type.equalsIgnoreCase("cash")) {
//                    int billType = amount.intValue();
//
//                    try {
//                        cashManager.changeDenom(billType, 1);
//                        cashManager.update();
//                    } catch (NegativeDenominationException e){
//                        e.getMessage();
//                    }
//                }
//            }
        }
    }


    void withdrawal(int account, int[] cashAmounts) throws InsufficientFundsException {

        int amount = (cashAmounts[0] * 5) +
                (cashAmounts[1] * 10) +
                (cashAmounts[2] * 20) +
                (cashAmounts[3] * 50);

        Account acc = user.getAccount(account);
        acc.setBalance(-amount);
        Transaction withdrawal = new Transaction(acc, amount);
        acc.setLastTransaction(withdrawal);
        Database.store();

        try {
            cashManager.changeDenom(5, -cashAmounts[0]);
            cashManager.changeDenom(10, -cashAmounts[1]);
            cashManager.changeDenom(20, -cashAmounts[2]);
            cashManager.changeDenom(50, -cashAmounts[3]);
            getCashManager().update();
        } catch (NegativeDenominationException e){
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }



    CashManager getCashManager() {
        return cashManager;
    }


    //    void internalTransfer(int from, int to , double amount) throws InsufficientFundsException {
//        try {
//            Account accFrom = user.getAccount(from);
//            Account accTo = user.getAccount(to);
//            accFrom.setBalance(-amount);
//            accTo.setBalance(amount);
//            Transaction intTransfer = new Transaction(accFrom, accTo, amount);
//            accTo.setLastTransaction(intTransfer);
//            accFrom.setLastTransaction(intTransfer);
//            bankManager.store();
//        } catch (NullPointerException n) {
//            System.out.println("You only have one account. Please request another account.");
//        }
//    }
//    void externalTransfer(int sender, User recipient,  double amount) throws InsufficientFundsException {
//        if (recipient.getPrimaryAccount() != null) {
//            Account accFrom = user.getAccount(sender);
//            ChequingAccount to = recipient.getPrimaryAccount();
//            to.setBalance(amount);
//            accFrom.setBalance(-amount);
//            Transaction extTransfer = new Transaction(accFrom, to, amount);
//            accFrom.setLastTransaction(extTransfer);
//            to.setLastTransaction(extTransfer);
//        }
//        bankManager.store();
//    }



    //    void payBill(int account, double amount) throws IOException, InsufficientFundsException {
//        Account acc = user.getAccount(account);
//        acc.setBalance(-amount);
//
//        File outgoing = new File("phase2/src/main/java/atm/outgoing.txt");
//        PrintWriter billPayer = new PrintWriter(new FileWriter("phase2/src/main/java/atm/outgoing.txt",
//                true));
//        Transaction billPayment = new Transaction(acc, amount);
//        billPayer.println(user + " payed $" + amount + " on " + date);
//        billPayer.close();
//
//        Transaction bill = new Transaction(acc, amount);
//        acc.setLastTransaction(bill);
//
//        bankManager.store();
//    }




//    void newAccountCreation() {
//        File f = new File("file");
//        if (f.exists()) {
//            Database.retrieve();
//        }
//        bankManager.createUser();
//        System.out.println("New users created.");
//
//
//    }
//
//    void newUsersRequests(){
//        Database.retrieve();
//        bankManager.userRequestAccount();
//        System.out.println("New accounts created");
//        Database.store();
//    }

    //    void reverseTransaction(String username, int account) throws InsufficientFundsException{
//        bankManager.ReverseLastTransaction(username,account);
//        Database.store();
//    }





    //    void requestAccount(int account){
//        if (account == 1){
//            user.requestAccount("Chequing");
//
//        }
//        else if (account == 2){
//            user.requestAccount("Savings");
//
//        }
//        else if (account == 3){
//            user.requestAccount("Line of Credit");
//
//        }
//        else if (account == 4){
//            user.requestAccount("Credit Card");
//        }
//        Database.store();
//    }




    //    void newUser(String username) throws UsernameTakenException{
//        File f = new File("file2");
//        if (f.exists()) {
//            bankManager.retrieveRequests();
//        }
//        String request = "Chequing";
//
//        if (checkExistingUser(username) != null){
//            UsernameTakenException u = new UsernameTakenException();
//            throw u;
//        }
//        else {
//            ArrayList<String> arr = new ArrayList<>();
//            arr.add(username);
//            arr.add(request);
//
//            bankManager.requests.add(arr);
//            bankManager.storeRequests();
//        }
//    }


//    boolean login2(String username){
//        Database.retrieve();
//        ArrayList<User> users = Database.users;
//
//        for (int i = 0; i < users.size(); i ++) {
//            if (users.get(i).getUsername().equals(username)){
//                    user = Database.users.get(i);
//                    return true;
//                }
//        }
//        Database.store();
//        return false;
//    }

//    String getUserPassword(){
//        return user.getPassword();
//    }



    //    void viewAccounts() {
//        user.viewAccounts();
//    }


    //    String viewAccountsInfo() {
////        Database.retrieve();
//        String accInfo = "Net total: " + user.netUserBalance() + "\n";
//        for (String account: user.accountInfo()) {
//            accInfo += account + "\n";
//        }
//        return accInfo;
//    }



//   void changePassword(String newPassword) {
//        user.setPassword(newPassword);
//        Database.store();
//    }



    //    void date(){
//        bankManager.setDate();
//    }

//    void viewAccountsManager(String name){
//        User user  = checkExistingUser(name);
//        user.viewAccounts();
//        Database.store();
//
//
//    }



//
//    User checkExistingUser(String username) {
//        return  Database.checkExistingUser(username);
//    }



//    User checkExistingUserDeposit (String username) {
//        return  Database.checkExistingUserDeposit(username);
//    }



}
