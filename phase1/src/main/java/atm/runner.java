package atm;

import java.util.HashMap;
import java.util.Scanner;

public class runner {

    // static date object goes here

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean loggedIn = false;
        ATM boundlessATM = new ATM();


//        while (!loggedIn) {
        System.out.println("Please pick an option.");
        System.out.println("(1) Login \n(2) Create new user\n(0) Exit");
        int option = Integer.parseInt(in.nextLine());

           while (!loggedIn) {
               switch (option) {
                   case 1:
                       System.out.println("Enter your username: ");
                       String username = in.nextLine();

                       System.out.println("Enter your password: ");
                       String password = in.nextLine();

                       loggedIn = boundlessATM.login(username, password);
                       if (!loggedIn) {
                           System.out.println("Invalid username or password");
                       }
                       break;

                   case 2:

                       System.out.println("Enter a username: ");
                       username = in.nextLine();
                       boundlessATM.newUser(username);
                       loggedIn = true;

                       break;

                   case 0:
                       System.exit(0);
                       break;

                   default:
                       System.out.println("Please pick a valid option.");
                       break;
               }
           }

//            while (loggedIn) {

                System.out.println("Welcome!\nPlease pick an option:");

                System.out.println("(1) View your balance");
                System.out.println("(2) Make a withdrawal");
                System.out.println("(3) Transfer funds between accounts");
                System.out.println("(4) Transfer funds to another user");
                System.out.println("(5) Pay a bill");
                System.out.println("(6) Change password");
                System.out.println("(7) Create new account");
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
                        System.out.println("From: ");
                        int recipient = Integer.parseInt(in.nextLine());

                        System.out.println("To: ");
                        int sender = Integer.parseInt(in.nextLine());


                        System.out.println("How much would you like to transfer?: ");
                        amount = Integer.parseInt(in.nextLine());

//                        boundlessATM.internalTransfer(sender, recipient, amount);

                        break;

                    case 4:
//                        boundlessATM.externalTransfer(account, user, account);
                        break;

                    case 5:
//                        boundlessATM.payBill(account, amount);
                        break;

                    case 6:
                        System.out.println("Enter new password: ");
                        String newPassword = in.nextLine();
                        boundlessATM.changePassword(newPassword);
                        break;
                    case 7:
                        // new account creation
                        System.out.println("What type of account would you like to open?:");
                        System.out.println("CHEQUING\nSAVINGS\nLINE OF CREDIT\nCREDIT CARD");

                    case 8:
                        // new account creation
                        boundlessATM.viewAccounts();

                        // this brings up their list of accounts

                        System.out.println("Which account would you like to deposit to: ");
                        account = Integer.parseInt(in.nextLine());

                        System.out.println("How much would you like to deposit ?: ");
                        amount = Integer.parseInt(in.nextLine());

                        boundlessATM.deposit(account,amount);

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


//        }
//    }
}
