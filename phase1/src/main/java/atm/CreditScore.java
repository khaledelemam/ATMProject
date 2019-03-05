package atm;

public class CreditScore {
    double min = 0.0;
    double max = 500.00;

    public static double getRandomDoubleBetweenRange(double min, double max){
        return  (Math.random()*((max-min)+1))+min;
    }
}
