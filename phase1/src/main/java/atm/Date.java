package atm;


import java.io.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*Keep track of time in ATM.*/
public class Date extends java.util.Date implements Serializable {

    private int day;
    private int month;
    private int year;
    private static final String filename = "date";

    //    private DateFormat sdf;
    //private static int today; //???

    public Date() {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Date today = (Date) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            this.day = 1;
            this.month = 1;
            this.year = 2019;
            System.out.println(this);
            c.printStackTrace();
        }
    }

    public void setDate(int day, int month, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public void update() {
        this.day++;
        if (this.day == 31) {
            this.day = 1;
            this.month++;
        }
        if (this.month == 13) {
            this.month = 1;
            this.year++;
        }
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this);
    }
}
