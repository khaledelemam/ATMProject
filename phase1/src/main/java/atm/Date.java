package atm;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*Keep track of time in ATM.*/
public class Date extends java.util.Date {

    private int day;
    private int month;
    private int year;
    //private static int today; //???

    public void setDate(int day, int month, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this);
    }
}
