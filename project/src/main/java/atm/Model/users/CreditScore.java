package atm.Model.users;

public class CreditScore {


    /** Used to determine if a new user may be created by BankManager */
    public static double getRandomDoubleBetweenRange(){
        double min = 0.0;
        double max = 500.00;
        return (Math.random()*((max-min)+1))+min;
    }
}
