package atm;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.time.LocalTime;

public class runner {


    public static void main(String[] args) throws IOException, NegativeDenominationException {

        LocalTime time = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        Scanner in = new Scanner(System.in);
        Date date = new Date();
        ATM boundlessATM = new ATM();
        String username = "";

        try {
            boundlessATM.deposit();
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        System.out.println(time.toString());


        while (!time.toString().equals("00:00:00")) {
            boolean newUser = false;
            time = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);

            File f = new File(Date.getFilename());
            if (f.exists()) {
                // .setToday() opens date file, but date file doesn't exist until BankManager sets the date initially
                date.setToday();
                System.out.println("Today is " + date);
            } else {
                System.out.println("Admin, please set the date.");
            }

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
                        admin = boundlessATM.adminCheck(password);
                    boolean adminLoggedIn = true;
                    while (adminLoggedIn) {
                        if (boundlessATM.adminCheck(password)) {

                            System.out.println("(1) Check/Approve new users requests");
                            System.out.println("(2) Check/Approve existing users requests");
                            System.out.println("(3) Reverse users transactions");
                            System.out.println("(4) SetDate");
                            System.out.println("(5) Change Cash Denominations");
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
                                    String user = in.nextLine();

                                    UserView view = new UserView();
                                    view.viewAccounts(user);

                                    System.out.println("Which account would you like to reverse the transaction from?");
                                    int acc = Integer.parseInt(in.nextLine());
                                    try {
                                        BankManager bm3 = new BankManager();
                                        bm3.ReverseLastTransaction(username,acc);
                                    } catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 4: // set date
                                    BankManager bm4 = new BankManager();
                                    bm4.setDate();
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
                        } else {
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

                            case 1: // previous login
//                                System.out.println("Have you logged in before?\n (1) Yes \n (2) No");
//                                int log = Integer.parseInt(in.nextLine());
//                                boolean loggedIn = false;

//                                switch (log) {
//                                    case 1: // previous login

                                            System.out.println("Enter your username: ");
                                            username = in.nextLine();

                                            System.out.println("Enter your password: ");
                                            String password = in.nextLine();

                                           boolean loggedIn = boundlessATM.login(username, password);

                                            if (!loggedIn) {
                                                System.out.println("Invalid username or password");
                                            }
                                            else{
                                                stayInLoop = false;
                                            }
//                                        break;

//                                case 2: // first login
//                                    System.out.println("Enter your username: ");
//                                    username = in.nextLine();
//                                    loggedIn = boundlessATM.login2(username);
//
//                                    if (!loggedIn) {
//                                        System.out.println("Invalid username ");
//                                    } else {
//                                        System.out.println("Your current password is: " + boundlessATM.getUserPassword());
//                                        stayInLoop = false;
//                                    }
//                                    break;
//                            }
                            break;

                        case 2: // request new user
                            System.out.println("Enter your preferred username:");
                            String name = in.nextLine();
                            System.out.println("Please wait till the manager processes your request");
                            try {
                                UserRequests request = new UserRequests();
                                request.newUser(name);
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
                    while (run && !newUser && !time.toString().equals("12:00:00")) {

                            System.out.println("\nWelcome!\nPlease pick an option:");

                            System.out.println("(1) View accounts info");
                            System.out.println("(2) Make a withdrawal");
                            System.out.println("(3) Transfer funds between accounts");
                            System.out.println("(4) Transfer funds to another user");
                            System.out.println("(5) Pay a bill");
                            System.out.println("(6) Change password");
                            System.out.println("(7) Request new account");
                            System.out.println("(0) Log out");

                            int option3 = -1;
                                try {
                                    option3 = Integer.parseInt(in.nextLine());
                                }catch (NumberFormatException n){
                                    System.out.println("Please enter a valid input");
                                }
                            switch (option3) {
                                case 1: // view accounts info
                                    UserView viewInfo = new UserView();
                                    System.out.println(viewInfo.viewAccountsInfo(username));
                                    break;

                                case 2: // withdrawal
                                    UserView viewAcc= new UserView();
                                    viewAcc.viewAccounts(username);

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
                                        UserExecutes transaction = new UserExecutes(new Withdraw(account, username,cashAmount));
                                        transaction.executeTransaction(WithdrawAmount);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }

                                    break;

                                case 3: // internal transfer
                                    UserView viewAcc2= new UserView();
                                    viewAcc2.viewAccounts(username);

                                    System.out.println("From: ");
                                    int from = Integer.parseInt(in.nextLine());

                                    System.out.println("To: ");
                                    int to = Integer.parseInt(in.nextLine());


                                    System.out.println("How much would you like to transfer?: ");
                                    double amount = Double.parseDouble(in.nextLine());

                                    try {
                                        UserExecutes transaction = new UserExecutes(new InternalTransfer(from, to, username));
                                        transaction.executeTransaction(amount);
                                    } catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }

                                    break;

                                case 4: // external transfer
                                    System.out.println("To whom do you want to transfer to?: ");
                                    String userTo = in.nextLine();

//                                    if (user != null) {
                                        System.out.println("How much money would you like to transfer?: ");
                                        amount = Double.parseDouble(in.nextLine());

                                    UserView viewAcc3= new UserView();
                                    viewAcc3.viewAccounts(username);

                                        System.out.println("From which account would you like to transfer money?: ");
                                        int accFrom = Integer.parseInt(in.nextLine());

                                        try {
                                            UserExecutes transaction = new UserExecutes(new ExternalTransfer(accFrom, userTo, username));
                                            transaction.executeTransaction(amount);
                                        } catch(InsufficientFundsException e){
                                            System.out.println(e.getMessage());
                                        }
//                                    }
                                    break;

                                case 5: // pay bill
                                    UserView viewAcc4= new UserView();
                                    viewAcc4.viewAccounts(username);

                                    System.out.println("From: ");
                                    from = Integer.parseInt(in.nextLine());

                                    System.out.println("How much would you like to pay?: ");
                                    amount = Double.parseDouble(in.nextLine());

                                    try {
                                        UserExecutes transaction = new UserExecutes(new PayBills(from, username));
                                        transaction.executeTransaction(amount);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 6: // change password
                                    System.out.println("Enter new password: ");
                                    String newPassword = in.nextLine();
                                    UserExecutes change = new UserExecutes();
                                    change.changePassword(newPassword, username);
                                    break;
                                case 7:
                                    // new account creation
                                    System.out.println("What type of account would you like to open?:");
                                    System.out.println("(1) CHEQUING\n(2) SAVINGS\n(3) LINE OF CREDIT\n(4) CREDIT CARD");
                                    account = Integer.parseInt(in.nextLine());

                                    UserRequests requests = new UserRequests();
                                    requests.requestAccount(account, username);
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

        date.update();
        System.exit(0);

    }





}
