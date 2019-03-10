package atm;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class runner {



    public static void main(String[] args) throws IOException, NegativeDenominationException {

        Scanner in = new Scanner(System.in);
        Date date = new Date();
        ATM boundlessATM = new ATM();

        try {
            boundlessATM.deposit();
        } catch (InsufficientFundsException e) {
            //ignore
        }

        boolean main = true;

        while (main) {
            boolean newUser = false;

            File f = new File(Date.getFilename());
            if (f.exists()) {
                // .setToday() opens date file, but date file doesn't exist until BankManager sets the date initially
                date.setToday();
                System.out.println("Today is " + date);
            } else {
                System.out.println("Admin, please set the date.");
            }

            System.out.println("Please type in a number to pick an option.");
            System.out.println("(1) Admin \n(2) User\n(3) Close for the day");

            try {
                int option = Integer.parseInt(in.nextLine());

            switch (option) {

                case 1:
                    boolean admin = false;
                    while (!admin) {
                        System.out.println("Enter admin password:");
                        String password = in.nextLine();
                        admin = boundlessATM.adminCheck(password);

                        if (boundlessATM.adminCheck(password)) {

                            System.out.println("(1) Check/Approve new users requests");
                            System.out.println("(2) Check/Approve existing users requests");
                            System.out.println("(3) Reverse users transactions");
                            System.out.println("(4) SetDate");
                            System.out.println("(5) Change Cash Denominations");

                            int option2 = Integer.parseInt(in.nextLine());

                            switch (option2) {
                                case 1:
                                    boundlessATM.newAccountCreation();
                                    break;

                                case 2:
                                    boundlessATM.usersRequests();
                                    break;

                                case 3:
                                    System.out.println("Type the username of the user you want to reverse a transaction for: ");
                                    String user = in.nextLine();
                                    boundlessATM.viewAccountsManager(user);
                                    System.out.println("Which account would you like to reverse the transaction from?");
                                    int acc = Integer.parseInt(in.nextLine());
                                    try {
                                        boundlessATM.reverseTransaction(user, acc);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 4:
                                    boundlessATM.date();
                                    break;

                                case 5:
                                    String filepath = "phase1/src/main/java/atm/alerts.txt";
                                    System.out.println(boundlessATM.getCashManager());
                                    int[] bills = {5,10,20,50};
                                    for (int i = 0; i< 4; i++){
                                        System.out.println("How many $" + bills[i] + " bills would you like to add?");
                                        int amount = Integer.parseInt(in.nextLine());
                                        while (!boundlessATM.getCashManager().checkDenom(bills[i],amount)){
                                            System.out.println("Sorry, that would result in a denomination less than 0");
                                            System.out.println("Please enter a different amount");
                                            amount = Integer.parseInt(in.nextLine());
                                        }
                                        boundlessATM.getCashManager().changeDenom(bills[i],amount);
                                    }
                                    boundlessATM.getCashManager().update(filepath);
                                    break;

                            }
                            break;


                        } else {
                            System.out.println("Invalid admin password");

                        }

                    }
                    break;


                case 2:
                    boolean stayInLoop = true;
                    while (stayInLoop) {

                    System.out.println("(1) Login");
                    System.out.println("(2) Request new bank account");
                    int hold = Integer.parseInt(in.nextLine());

                    switch (hold) {

                        case 1:
                            System.out.println("Have you logged in before?\n (1) Yes \n (2) No");
                            int log = Integer.parseInt(in.nextLine());
                            boolean loggedIn = false;

                            switch (log) {
                                case 1:
                                    //while (!loggedIn) {

                                        System.out.println("Enter your username: ");
                                        String username = in.nextLine();

                                        System.out.println("Enter your password: ");
                                        String password2 = in.nextLine();

                                        loggedIn = boundlessATM.login(username, password2);
                                        if (!loggedIn) {
                                            System.out.println("Invalid username or password");
                                        }
                                    //}
                                    break;

                                case 2:
                                    //while (!loggedIn) {

                                        System.out.println("Enter your username: ");
                                        String username2 = in.nextLine();
                                        loggedIn = boundlessATM.login2(username2);

                                        if (!loggedIn) {
                                            System.out.println("Invalid username ");
                                        } else {
                                            System.out.println("Your current password is: " + boundlessATM.getUserPassword());
                                        }
                                    //}
                                    break;

                            }
                            break;


                        case 2:
                            System.out.println("Enter your preferred username:");
                            String name = in.nextLine();
                            System.out.println("Please wait till the manager processes your request");
                            try {
                                boundlessATM.newUser(name);
                            } catch (UsernameTakenException u) {
                                System.out.println(u.getMessage());
                            }
                            newUser = true;
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
                            System.out.println("(3) Transfer funds between accounts");
                            System.out.println("(4) Transfer funds to another user");
                            System.out.println("(5) Pay a bill");
                            System.out.println("(6) Change password");
                            System.out.println("(7) Request new account");
                            System.out.println("(0) Log out");

                            option = Integer.parseInt(in.nextLine());

                            switch (option) {
                                case 1:
                                    System.out.println(boundlessATM.viewAccountsInfo());
                                    break;

                                case 2: // withdrawal
                                    boundlessATM.viewAccounts();

                                    // this brings up their list of accounts

                                    System.out.println("Which account would you like to withdraw from?: ");
                                    int account = Integer.parseInt(in.nextLine());

                                    System.out.println("How much would you like to withdraw?:\n" +
                                            "Denominations available: $5, $10, $15, $20");
                                    int cashAmount = Integer.parseInt(in.nextLine());
                                    try {
                                        boundlessATM.withdrawal(account, cashAmount);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }

                                    break;

                                case 3: // internal transfer
                                    boundlessATM.viewAccounts();
                                    System.out.println("From: ");
                                    int from = Integer.parseInt(in.nextLine());

                                    System.out.println("To: ");
                                    int to = Integer.parseInt(in.nextLine());


                                    System.out.println("How much would you like to transfer?: ");
                                    double amount = Double.parseDouble(in.nextLine());

                                    try {
                                        boundlessATM.internalTransfer(from, to, amount);
                                    } catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }

                                    break;

                                case 4: // external transfer
                                    System.out.println("To whom do you want to transfer to?: ");
                                    String userTo = in.nextLine();
                                    User user = boundlessATM.checkExistingUser(userTo);
                                    if (user != null) {
                                        System.out.println("How much money would you like to transfer?: ");
                                        amount = Double.parseDouble(in.nextLine());

                                        boundlessATM.viewAccounts();
                                        System.out.println("From which account would you like to transfer money?: ");
                                        int accFrom = Integer.parseInt(in.nextLine());

                                        try {
                                            boundlessATM.externalTransfer(accFrom, user, amount);
                                        } catch(InsufficientFundsException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;

                                case 5: // pay bill
                                    boundlessATM.viewAccounts();
                                    System.out.println("From: ");
                                    from = Integer.parseInt(in.nextLine());

                                    System.out.println("How much would you like to pay?: ");
                                    amount = Double.parseDouble(in.nextLine());

                                    try {
                                        boundlessATM.payBill(from, amount);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 6: // change password
                                    System.out.println("Enter new password: ");
                                    String newPassword = in.nextLine();
                                    boundlessATM.changePassword(newPassword);
                                    break;
                                case 7:
                                    // new account creation
                                    System.out.println("What type of account would you like to open?:");
                                    System.out.println("(1) CHEQUING\n(2) SAVINGS\n(3) LINE OF CREDIT\n(4) CREDIT CARD");
                                    account = Integer.parseInt(in.nextLine());
                                    boundlessATM.requestAccount(account);
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
                    break;


                case 3:
                    date.update();
                    System.exit(0);
                }


            } catch (NumberFormatException n) {
                System.out.println("Please enter a valid input");
            }



        }


    }


}
