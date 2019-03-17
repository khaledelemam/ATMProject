package atm;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class runner {


    public static void main(String[] args) throws IOException, NegativeDenominationException {


//        Calendar cal=Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis());
//
//        SimpleDateFormat format=new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
//        System.out.println(format.format(cal.getTime()));


        LocalTime time = ZonedDateTime.now().toLocalTime().truncatedTo(MINUTES);
        System.out.println(time.toString());

        LocalTime midnight = LocalTime.MAX;

        long terminate = SECONDS.between(time, midnight);

//        System.out.println(SECONDS.between(time, midnight));
//
//        System.out.println(MINUTES.between(time, midnight));


        Scanner in = new Scanner(System.in);

//        Calendar c = Calendar.getInstance();
//        System.out.println("Today is " +c.getTime());
//        Date date = new Date();

        Time date = new Time(1);

        System.out.println(date);


        String username;
        User USER = null;


        Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), terminate, TimeUnit.SECONDS);

        while (!time.toString().equals("00:00:00")) {
            boolean newUser = false;
            time = ZonedDateTime.now().toLocalTime().truncatedTo(SECONDS);

//            File f = new File(Date.getFilename());
//            if (f.exists()) {
//                // .setToday() opens date file, but date file doesn't exist until BankManager sets the date initially
//                date.setToday();
////                System.out.println("Today is " + date);
//            } else {
//                System.out.println("Admin, please set the date.");
//            }

            System.out.println("Please type in a number to pick an option.");
            System.out.println("(1) Admin \n(2) User\n");

            try {
                int option = Integer.parseInt(in.nextLine());

            switch (option) {

                case 1: // admin login
                    boolean admin = false;
                    while (!admin) {
                        System.out.println("Enter admin password:");
                        String password = in.nextLine();

                        BankManager bankManager = new BankManager();
                        admin = password.equals(bankManager.getPassword());

                    boolean adminLoggedIn = true;

                    while (adminLoggedIn) {

                        if (admin) {

                            CashManager cash = new CashManager();
                            cash.alertManager();

                            System.out.println("\n(1) Check/Approve new users requests");
                            System.out.println("(2) Check/Approve existing users requests");
                            System.out.println("(3) Reverse users transactions");
                            System.out.println("(4) SetDate");
                            System.out.println("(5) Restock machine");
                            System.out.println("(0) Exit");

                            int option2 = Integer.parseInt(in.nextLine());

                            switch (option2) {
                                case 1: // new user requests
                                    BankManager bm = new BankManager();
                                    bm.createUser();
                                    break;

                                case 2: // user requests
                                    BankManager bm2 = new BankManager();
                                    bm2.userRequestAccount();
                                    break;

                                case 3: // reverse transaction
                                    System.out.println("Type the username of the user you want to reverse a transaction for: ");
                                    String user1 = in.nextLine();

                                    User user = Database.checkExistingUser(user1);

                                    if (user!=null) user.viewAccounts();


                                    System.out.println("Which account would you like to reverse the transaction from?");
                                    int acc = Integer.parseInt(in.nextLine());
                                    try {

                                        BankManager bm3 = new BankManager();
                                        bm3.ReverseLastTransaction(user,acc);

                                    } catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 4: // set date
                                    System.out.println("How many days from today do you want to set the date?");
                                    int days = Integer.parseInt(in.nextLine());
                                    BankManager bm4 = new BankManager();
                                    bm4.setDate(days);
                                    break;

                                case 5: // restock machine
                                    CashManager cashmanager = new CashManager();
                                    System.out.println(cashmanager);
                                    int[] bills = {5, 10, 20, 50};
                                    for (int i = 0; i < 4; i++) {
                                        System.out.println("How many $" + bills[i] + " bills would you like to add?");
                                        int amount = Integer.parseInt(in.nextLine());
                                        while (!cashmanager.checkDenom(bills[i], amount)) {
                                            System.out.println("Sorry, that would result in a denomination less than 0");
                                            System.out.println("Please enter a different amount");
                                            amount = Integer.parseInt(in.nextLine());
                                        }
                                        cashmanager.changeDenom(bills[i], amount);
                                    }
                                    cashmanager.update();
                                    break;
                                case 0:
                                    adminLoggedIn = false;
                                    break;

                        }
                        }
                        else {
                            System.out.println("Invalid admin password");
                            adminLoggedIn = false;
                        }
                    }
                    }
                    break;

                case 2: // user login
                    boolean stayInLoop = true;

                    while (stayInLoop) {

                        System.out.println("(1) Login");
                        System.out.println("(2) Request new bank account");
                        int hold = Integer.parseInt(in.nextLine());

                        switch (hold) {

                            case 1:
                                            System.out.println("Enter your username: ");
                                            username = in.nextLine();

                                            System.out.println("Enter your password: ");
                                            String password = in.nextLine();

                                           USER = Database.login(username, password);

                                            if (USER == null) {
                                                System.out.println("Invalid username or password");
                                            }
                                            else{
                                                stayInLoop = false;
                                            }
                            break;

                        case 2: // request new user
                            System.out.println("Enter your preferred username:");
                            String name = in.nextLine();

                            try {
                                BankManager bankManager = new BankManager();
                                bankManager.newUserRequest(name);
                            } catch (UsernameTakenException u) {
                                System.out.println(u.getMessage());
                            }
                            newUser = true;
                            stayInLoop = false;
                            break;

                        default:
                            System.out.println("Invalid option");
                            break;
                        }
                    }

                    boolean run = true;
                    while (run && !newUser) {

                            System.out.println("\nWelcome!\nPlease pick an option:");

                            System.out.println("(1) View accounts info");
                            System.out.println("(2) Make a withdrawal");
                            System.out.println("(3) Make a deposit");
                            System.out.println("(4) Transfer funds between accounts");
                            System.out.println("(5) Transfer funds to another user");
                            System.out.println("(6) Pay a bill");
                            System.out.println("(7) Change password");
                            System.out.println("(8) Request new account");
                            System.out.println("(0) Log out");

                            int option3 = -1;
                                try {
                                    option3 = Integer.parseInt(in.nextLine());
                                }catch (NumberFormatException n){
                                    System.out.println("Please enter a valid input");
                                }
                            switch (option3) {
                                case 1: // view accounts info

                                    System.out.println(USER.viewAccountsInfo());
                                    break;

                                case 2: // withdraw
                                   USER.viewAccounts();

                                    // this brings up their list of accounts

                                    System.out.println("Which account would you like to withdraw from?: ");
                                    int account = Integer.parseInt(in.nextLine());

                                    System.out.println("How much would you like to withdraw?");
                                    int WithdrawAmount = Integer.parseInt(in.nextLine());

                                    System.out.println("Denominations available: $5, $10, $20, $50");
                                    System.out.println("How many $5 bills: ");
                                    int fives = Integer.parseInt(in.nextLine());

                                    System.out.println("How many $10 bills: ");
                                    int tens = Integer.parseInt(in.nextLine());

                                    System.out.println("How many $20 bills: ");
                                    int twenties = Integer.parseInt(in.nextLine());

                                    System.out.println("How many $50 bills: ");
                                    int fifties = Integer.parseInt(in.nextLine());

                                    int[] cashAmount = new int[]{fives, tens, twenties, fifties};

                                    try {
                                        UserExecutes transaction = new UserExecutes(new Withdraw(account, USER ,cashAmount));
                                        transaction.executeTransaction(WithdrawAmount);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }

                                    break;



                                case 3: //deposit

                                    USER.viewAccounts();
                                    // this brings up their list of accounts

                                    System.out.println("Which account would you like to deposit to?: ");
                                    int accountD = Integer.parseInt(in.nextLine());

                                    System.out.println("How much would you like to deposit: ");
                                    double amountD = Double.parseDouble(in.nextLine());
                                    try {
                                        UserExecutes transaction = new UserExecutes(new Deposit(accountD, USER));
                                        transaction.executeTransaction(amountD);
                                    } catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;


                                case 4: // internal transfer
                                    USER.viewAccounts();

                                    System.out.println("From: ");
                                    int from = Integer.parseInt(in.nextLine());

                                    System.out.println("To: ");
                                    int to = Integer.parseInt(in.nextLine());


                                    System.out.println("How much would you like to transfer?: ");
                                    double amount = Double.parseDouble(in.nextLine());

                                    try {
                                        UserExecutes transaction = new UserExecutes(new InternalTransfer(from, to, USER));
                                        transaction.executeTransaction(amount);
                                    } catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }

                                    break;

                                case 5: // external transfer
                                    System.out.println("To whom do you want to transfer to?: ");
                                    String userTo = in.nextLine();

//                                    if (user != null) {
                                    System.out.println("How much money would you like to transfer?: ");
                                    amount = Double.parseDouble(in.nextLine());

                                    USER.viewAccounts();

                                        System.out.println("From which account would you like to transfer money?: ");
                                        int accFrom = Integer.parseInt(in.nextLine());

                                        try {
                                            UserExecutes transaction = new UserExecutes(new ExternalTransfer(accFrom, userTo, USER));
                                            transaction.executeTransaction(amount);
                                        } catch(InsufficientFundsException e){
                                            System.out.println(e.getMessage());
                                        }
//                                    }
                                    break;

                                case 6: // pay bill
                                    USER.viewAccounts();

                                    System.out.println("From: ");
                                    from = Integer.parseInt(in.nextLine());

                                    System.out.println("How much would you like to pay?: ");
                                    amount = Double.parseDouble(in.nextLine());

                                    try {
                                        UserExecutes transaction = new UserExecutes(new PayBills(from, USER));
                                        transaction.executeTransaction(amount);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 7: // change password
                                    System.out.println("Enter new password: ");
                                    String newPassword = in.nextLine();
                                    USER.setPassword(newPassword);

                                    Database.store();
                                    break;

                                case 8:
                                    // new account creation
                                    System.out.println("What type of account would you like to open?:");
                                    System.out.println("(1) Chequing\n(2) Savings\n(3) Line of Credit\n(4) Credit Card\n(5) Joint Account");
                                    account = Integer.parseInt(in.nextLine());

                                    if (account == 5){

                                        System.out.println("Who do you want to open the account with?");
                                        String partner = in.nextLine();
                                        USER.requestJointAccount(partner);
                                    }

                                    USER.requestAccount(account);
                                    break;

                                case 0: // log out
                                    System.out.println("Goodbye!\n");
                                    run = false;
                                    break;

                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                        }


                }
            } catch (NumberFormatException n) {
                System.out.println("Please enter a valid input");
            }
        }

//        date.update();
//        System.exit(0);

    }





}
