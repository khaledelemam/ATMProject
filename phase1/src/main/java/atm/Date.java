package atm;


import java.io.*;
import java.io.Serializable;

/*Keep track of date in ATM.*/
public class Date extends java.util.Date implements Serializable {

    /* Day, month and year of current date. */
    private int day;
    private int month;
    private int year;

    /* Name of byte stream file. */
    private static final String filename = "date";

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
        }
    }

    public static String getFilename() {
        return filename;
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

    public void setDate() {
        /*Set current date to beginning of the year*/
        this.month = 1;
        this.day = 1;
        this.year = 2019;
        store();
    }

    public void update() {
        /*Update the current date
         * Assume: each month has 31 days
         **/
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
        /* Store current date.*/
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
        String yyyy = Integer.toString(this.year);
        String MM = Integer.toString(this.month);
        String dd = Integer.toString(this.day);

        StringBuilder thisDate = new StringBuilder();
        thisDate.append(yyyy + "-" + MM + "-" + dd);

        return String.valueOf(thisDate);
    }
}
