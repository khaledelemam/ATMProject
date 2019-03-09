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

    public static void main(String[] args) {
        Date today = new Date();

        System.out.println(today);
        today.update();
        System.out.println(today);

        Date other = new Date();
        System.out.println(other);
        System.out.println(today);
        System.out.println(other);
        System.out.println(today);
    }


    public Date() {
        File f = new File(filename);
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Date today = (Date) ois.readObject();
                ois.close();
                fis.close();

                this.day = today.getDay();
                this.month = today.getMonth();
                this.year = today.getYear();
            } catch (IOException ioe) {
                ioe.printStackTrace();

            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
            }

        } else {
            this.day = 1;
            this.month = 1;
            this.year = 2019;
            store();
        }

    }

    @Override
    public int getDay() {
        return this.day;
    }

    @Override
    public int getMonth() {
        return this.month;
    }

    @Override
    public int getYear() {
        return this.year;
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
        store();
    }

    private void store() {
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
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yyyy = Integer.toString(this.year);
        String MM = Integer.toString(this.month);
        String dd = Integer.toString(this.day);
        StringBuilder thisDate = new StringBuilder();
        thisDate.append(yyyy + "-" + MM + "-" + dd);
        return String.valueOf(thisDate);
    }
}
