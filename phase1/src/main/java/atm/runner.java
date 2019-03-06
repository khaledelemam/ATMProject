package atm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class runner {

    // static date object goes here

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        boolean main = true;
        ATM boundlessATM = new ATM();


        while (main) {
            boolean newUser  = false;

            System.out.println("Please type in a number to pick an option.");
            System.out.println("(1) Admin \n(2) User\n");
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

                            int option2 = Integer.parseInt(in.nextLine());

                            switch (option2) {
                                case 1:
                                    boundlessATM.newAccountCreation();
                                    break;
                                case 2:
                                    boundlessATM.usersRequests();
                                    break;

                                case 3:

                                case 4:

                            }
                            break;


                        } else {
                            System.out.println("Invalid admin password");

                        }

                    }
                    break;


                case 2:
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
                                        } else {
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
                            System.out.println("Please wait till the manager processes your request");
                            newUser = true;

                    }



                    boolean run = true;
                    while (run && !newUser) {



                            System.out.println("\nWelcome!\nPlease pick an option:");

                            System.out.println("(1) View your balance");
                            System.out.println("(2) Make a withdrawal");
                            System.out.println("(3) Transfer funds between accounts");
                            System.out.println("(4) Transfer funds to another user");
                            System.out.println("(5) Pay a bill");
                            System.out.println("(6) Change password");
                            System.out.println("(7) Request new account");
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
                                    try {
                                        boundlessATM.withdrawal(account, amount);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }

                                    break;

                                case 3:
                                    boundlessATM.viewAccounts();
                                    System.out.println("From: ");
                                    int from = Integer.parseInt(in.nextLine());

                                    System.out.println("To: ");
                                    int to = Integer.parseInt(in.nextLine());


                                    System.out.println("How much would you like to transfer?: ");
                                    amount = Integer.parseInt(in.nextLine());

                                    try {
                                        boundlessATM.internalTransfer(from, to, amount);
                                    } catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }

                                    break;

                                case 4:
                                    System.out.println("To whom do you want to transfer to?: ");
                                    String userTo = in.nextLine();
                                    User user = boundlessATM.checkExistingUser(userTo);
                                    if (user != null) {
                                        System.out.println("How much money would you like to transfer?: ");
                                        amount = Integer.parseInt(in.nextLine());
                                        boundlessATM.viewAccounts();
                                        System.out.println("From which account would you like to transfer money?: ");
                                        int accFrom = Integer.parseInt(in.nextLine());
                                        try {
                                        boundlessATM.externalTransfer(accFrom, user, amount);
                                        }catch(InsufficientFundsException e){
                                        System.out.println(e.getMessage());
                                        }
                                    }
                                    break;

                                case 5:

                                    boundlessATM.viewAccounts();
                                    System.out.println("From: ");
                                    from = Integer.parseInt(in.nextLine());

                                    System.out.println("How much would you like to pay?: ");
                                    amount = Integer.parseInt(in.nextLine());
                                    try {
                                        boundlessATM.payBill(from, amount);
                                    }catch (InsufficientFundsException e) {
                                        System.out.println(e.getMessage());
                                    }
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

                                case 0:
                                    System.out.println("Goodbye!\n");
                                    run = false;
                                    break;

                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                        }
                    }




        }


    }


}
