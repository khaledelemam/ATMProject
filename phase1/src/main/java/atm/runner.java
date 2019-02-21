package atm;

import java.util.Scanner;

public class runner {

    // static date object goes here

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean loggedIn = false;
        ATM boundlessATM = new ATM();


        while (!loggedIn) {
            System.out.println("Please pick an option.");
            System.out.println("(1) Login \n(2) Create new user\n(0) Exit");
            int option = Integer.parseInt(in.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Enter your username: ");
                    String username = in.nextLine();

                    System.out.println("Enter your password: ");
                    String password = in.nextLine();

                    loggedIn = boundlessATM.login(username, password);
                    break;

                case 2:

                    System.out.println("Enter a username: ");
                    username = in.nextLine();

                    boundlessATM.newUser(username);

                    System.out.println("Your current password is \" password \".");
                    break;

                case 0:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Please pick a valid option.");
                    break;
            }

            while (loggedIn) {
                System.out.println("Welcome!\nPlease pick an option:");

                System.out.println("(1) View your accounts");
                System.out.println("(2) Make a withdrawal");
                System.out.println("(3) Transfer funds between accounts");
                System.out.println("(4) Transfer funds to another user");
                System.out.println("(5) Pay a bill");
                System.out.println("(6) Change password");
                System.out.println("(0) Exit");

                option = Integer.parseInt(in.nextLine());

                switch (option) {
                    case 1:
                        boundlessATM.viewAccounts();
                        break;

                    case 2:
                        boundlessATM.withdrawal(account, amount);
                        break;

                    case 3:
                        boundlessATM.internalTransfer(recipient, amount);
                        break;

                    case 4:
                        boundlessATM.externalTransfer(account, user, account);
                        break;

                    case 5:
                        boundlessATM.payBill(account, amount);
                        break;

                    case 6:
                        System.out.println("Enter new password: ");
                        String newPassword = in.nextLine();
                        boundlessATM.changePassword(newPassword);
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
