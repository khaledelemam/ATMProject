package atm;

/*Keep track of time in ATM.*/
public class Date {
    private int day;
    private int month;
    private int year;
    //private static int today; //???
    
	public Date() {

    }

    public void setDate(int day, int month, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }


}
