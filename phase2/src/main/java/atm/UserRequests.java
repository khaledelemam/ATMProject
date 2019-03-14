package atm;

import java.io.File;
import java.util.ArrayList;


public class UserRequests {


    void newUser(String username) throws UsernameTakenException{
        BankManager bankManager = new BankManager();

        File f = new File("file");
        if (f.exists()) {
            Database.retrieve();
        }

        File f2 = new File("file2");
        if (f2.exists()) {
            bankManager.retrieveRequests();
        }
        String request = "Chequing";

        if (Database.checkExistingUser(username) != null){
            throw new UsernameTakenException();
        }
        else {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(username);
            arr.add(request);

            bankManager.requests.add(arr);
            bankManager.storeRequests();
            Database.store();
            System.out.println("Please wait till the manager processes your request");
        }
    }



    void requestAccount(int account, User user){

//        User user = Database.checkExistingUser(username);

        if (account == 1){
            user.requestAccount("Chequing");

        }
        else if (account == 2){
            user.requestAccount("Savings");

        }
        else if (account == 3){
            user.requestAccount("Line of Credit");

        }
        else if (account == 4){
            user.requestAccount("Credit Card");
        }
        Database.store();
    }


}
