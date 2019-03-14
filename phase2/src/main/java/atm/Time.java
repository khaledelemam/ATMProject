package atm;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Time implements Serializable{

    Date date;
    private static final long serialVersionUID = 120L;

    private static final String filename = "time";



    public Time(int one) {

        File f = new File(filename);
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);

                date = (Date) ois.readObject();

                long plusOneDay = (1000 * 60 * 60 * 24);
                date.setTime(date.getTime() + plusOneDay);

                ois.close();
                fis.close();

                store();

            } catch (IOException ioe) {
                ioe.printStackTrace();

            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
            }


        } else {
            date = new Date();
            store();
        }
    }


    public Time(){
        retrieve();

    }



    public Time(long FutureTime){

        SetTime(FutureTime);

    }

    private void SetTime( long time){

        retrieve();

        date.setTime(date.getTime() + time);

        store();
    }


    @Override
    public String toString() {

        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        return sm.format(date);

    }


    private void store() {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(date);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void retrieve(){

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            date = (Date) ois.readObject();

            ois.close();
            fis.close();


        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }


    }




}
