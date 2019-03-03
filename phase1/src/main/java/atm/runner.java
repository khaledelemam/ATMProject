package atm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class runner {

    // static date object goes here

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        boolean loggedIn = false;
        boolean exit = false;
        ATM boundlessATM = new ATM();



        System.out.println("Please type in a number to pick an option.");
        System.out.println("(1) Admin \n(2) User\n(0) Exit");
        int option = Integer.parseInt(in.nextLine());

        switch(option) {
            case 1:
                System.out.println("Enter admin password:");
                String password = in.nextLine();

                if (boundlessATM.adminCheck(password)) {
                    System.out.println("(1) Check/Approve users requests");
                    System.out.println("(2) Reverse users transactions");
                    System.out.println("(3) SetDate");

                    int option2 = Integer.parseInt(in.nextLine());

                    switch (option2) {
                        case 1:
                            boundlessATM.testAccountCreation();
                            break;
                        case 2:

                        case 3:

                    }
                    break;


                }


            case 2:
                System.out.println("(1) Login");
                System.out.println("(2) Request new bank account");
                int hold = Integer.parseInt(in.nextLine());

                switch (hold) {
                    case 1:
                        System.out.println("Have you logged in before?\n (1) Yes \n (2) No");
                        int log = Integer.parseInt(in.nextLine());

                        switch (log) {
                            case 1:
                                while (!loggedIn) {

                                    System.out.println("Enter your username: ");
                                    String username = in.nextLine();

                                    System.out.println("Enter your password: ");
                                    String password2 = in.nextLine();

                                    loggedIn = boundlessATM.login(username, password2);
                                    if (!loggedIn) {
                                        System.out.println("Invalid username or password");
                                    }
                                }

                            case 2:
                                while (!loggedIn) {

                                    System.out.println("Enter your username: ");
                                    String username = in.nextLine();
                                    loggedIn = boundlessATM.login2(username);

                                    if (!loggedIn) {
                                        System.out.println("Invalid username ");
                                    }

                                    else{
                                        System.out.println("Your current password is: " + boundlessATM.getUserPassword());
                                    }
                                }

                        }
                        break;


                    case 2:
                        System.out.println("What type of account would you like to open?:");
                        System.out.println("(1) CHEQUING\n(2) SAVINGS\n(3) LINE OF CREDIT\n(4) CREDIT CARD");
                        int account = Integer.parseInt(in.nextLine());
                        System.out.println("Enter your preferred username:");
                        String name = in.nextLine();
                        boundlessATM.newUser(name, account);
                        System.out.println("Please wait till tomorrow for processing");
                        exit = true;
                }

                if (!exit) {

                    System.out.println("Welcome!\nPlease pick an option:");

                    System.out.println("(1) View your balance");
                    System.out.println("(2) Make a withdrawal");
                    System.out.println("(3) Transfer funds between accounts");
                    System.out.println("(4) Transfer funds to another user");
                    System.out.println("(5) Pay a bill");
                    System.out.println("(6) Change password");
                    System.out.println("(7) Request new account");
                    System.out.println("(8) Deposit");
                    System.out.println("(0) Exit");

                    option = Integer.parseInt(in.nextLine());

                    switch (option) {
                        case 1:
                            // accounts should be stored in a hashmap where each account is mapped to an id
                            // so ppl can access account info via typing in the ID
//                        HashMap<Integer, Account> accounts = boundlessATM.viewAccounts();
//
//                        // view net total
                            boundlessATM.viewBalance();

                            break;

                        case 2:
                            boundlessATM.viewAccounts();

                            // this brings up their list of accounts

                            System.out.println("Which account would you like to withdraw from?: ");
                            int account = Integer.parseInt(in.nextLine());

                            System.out.println("How much would you like to withdraw?: ");
                            int amount = Integer.parseInt(in.nextLine());

                            boundlessATM.withdrawal(account, amount);
                            break;

                        case 3:
                            boundlessATM.viewAccounts();
                            System.out.println("From: ");
                            int from = Integer.parseInt(in.nextLine());

                            System.out.println("To: ");
                            int to = Integer.parseInt(in.nextLine());


                            System.out.println("How much would you like to transfer?: ");
                            amount = Integer.parseInt(in.nextLine());

                            boundlessATM.internalTransfer(from, to, amount);

                            break;

                        case 4:
//                        boundlessATM.externalTransfer(account, user, account);
                            break;

                        case 5:

                            boundlessATM.viewAccounts();
                            System.out.println("From: ");
                            from = Integer.parseInt(in.nextLine());

                            System.out.println("How much would you like to pay?: ");
                            amount = Integer.parseInt(in.nextLine());

                            boundlessATM.payBill(from, amount);
                            break;

                        case 6:
                            System.out.println("Enter new password: ");
                            String newPassword = in.nextLine();
                            boundlessATM.changePassword(newPassword);
                            break;
                        case 7:
                            // new account creation
                            System.out.println("What type of account would you like to open?:");
                            System.out.println("(1) CHEQUING\n(2) SAVINGS\n(3) LINE OF CREDIT\n (4) CREDIT CARD");
                            account = Integer.parseInt(in.nextLine());
                            boundlessATM.requestAccount(account);
                            break;

                        case 8:
                            // new account creation
                            boundlessATM.viewAccounts();

                            // this brings up their list of accounts

                            System.out.println("Which account would you like to deposit to: ");
                            account = Integer.parseInt(in.nextLine());

                            System.out.println("How much would you like to deposit ?: ");
                            amount = Integer.parseInt(in.nextLine());

                            boundlessATM.deposit(account, amount);

                            break;


                        case 0:
                            System.out.println("Goodbye!\n");
                            loggedIn = false;
                            break;

                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                }
        }




        }





}
