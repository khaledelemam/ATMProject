package atm;

import java.util.Scanner;

public class ATM {

    private static Scanner reader = new Scanner(System.in);


    private static boolean login() {
        System.out.println("Enter username: ");
        String username = reader.nextLine();
        System.out.println("Enter password: ");
        String password = reader.nextLine();

        // if username is valid
            // if password matches username
                // return true
            // else re-prompt for username and password
        return true;
    }
    private static void newUser() {
        System.out.println("Enter username: ");
        String username = reader.nextLine();
        // call to BankManager to create a new user account with default password
        // cannot have two users with the same username
    }

    private static void menu() {

    }

    private static void accounts() {

    }

    public static void main(String[] args) {
        System.out.println("1. Login \n2. Create new user");
        int option = Integer.parseInt(reader.nextLine());
        if (option == 1) {
            login();
        } else if (option == 2) {
            newUser();
        }
    }
}
