package atm;

import java.io.File;

public class BankManagerExecute {

    void newAccountCreation() {
        File f = new File("file");
        if (f.exists()) {
            Database.retrieve();
        }

        BankManager bankManager = new BankManager();
        bankManager.createUser();
        System.out.println("New users created.");


    }

    void newUsersRequests(){
        Database.retrieve();

        BankManager bankManager = new BankManager();
        bankManager.userRequestAccount();
        System.out.println("New accounts created");
        Database.store();
    }


    void reverseTransaction(String username, int account) throws InsufficientFundsException{

        BankManager bankManager = new BankManager();
        bankManager.ReverseLastTransaction(username,account);
        Database.store();
    }

    void date(){
        BankManager bankManager = new BankManager();
        bankManager.setDate();
    }

}
