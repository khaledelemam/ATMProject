package atm.Model;

import atm.Model.accounts.Calculations;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class   Time implements Serializable{

    public Date date;
    private static final long serialVersionUID = 120L;

    private Filename filename = new Filename();



    public Time(int one) {

        Serialize ser = new Serialize(filename.getTimeFile(), date);
        Object retrieve = ser.retrieve();
        if (retrieve !=null){
            date = (Date) retrieve;
            long plusOneDay = (1000 * 60 * 60 * 24);
            date.setTime(date.getTime() + plusOneDay);
            store();

        }
        else{
            date = new Date();
            store();

        }
    }


    public Time(){ retrieve(); }


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

    public void setDate(int days, Time oldDate) {
        long plusOneDay = (1000 * 60 * 60 * 24);
        long NumberOfDays = plusOneDay * days;

        Calculations calc = new Calculations();
        calc.numInterestApplications(days, oldDate);

        SetTime(NumberOfDays);

    }



    private void store() {
        Serialize ser = new Serialize(filename.getTimeFile(), date);
        ser.store();
    }

    private void retrieve(){
        Serialize ser = new Serialize(filename.getTimeFile(), date);
        Object retrieve = ser.retrieve();
        if (retrieve !=null) date = (Date) retrieve;


    }




}
