package atm;

public class CreditScore {



    public static double getRandomDoubleBetweenRange(){
        double min = 0.0;
        double max = 500.00;
        return (Math.random()*((max-min)+1))+min;
    }
}
