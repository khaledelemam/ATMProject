package atm;

import java.io.File;
import java.util.ArrayList;


public class UserRequests {


    void newUser(String username) throws UsernameTakenException{
        BankManager bankManager = new BankManager();

        File f = new File("file2");
        if (f.exists()) {
            bankManager.retrieveRequests();
        }
        String request = "Chequing";

        if (Database.checkExistingUser(username) != null){
            UsernameTakenException u = new UsernameTakenException();
            throw u;
        }
        else {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(username);
            arr.add(request);

            bankManager.requests.add(arr);
            bankManager.storeRequests();
        }
    }



    void requestAccount(int account, String username){

        User user = Database.checkExistingUser(username);

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
