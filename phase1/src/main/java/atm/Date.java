package atm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Date extends java.util.Date {

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this);
    }
}