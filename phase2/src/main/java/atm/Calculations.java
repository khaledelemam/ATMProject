package atm;

import java.util.Calendar;

public class Calculations {


    void numInterestApplications(int days, Time oldDate){
        // computes the number of times you need to apply interest
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate.date);
        int oldYear = cal.get(Calendar.YEAR);
        cal.add(Calendar.DAY_OF_MONTH, days);
        int monthsPassed = Math.abs(cal.get(Calendar.MONTH) - oldDate.date.getMonth());
        int yearsPassed = Math.abs(cal.get(Calendar.YEAR) - oldYear);
        int i = monthsPassed + 12*yearsPassed;
        applyInterest(i);
    }

    void applyInterest(int numApplications){
        Database database = new Database();
        for (int i=0; i < database.getUsers().size();i++) {
            User user1 = database.getUsers().get(i);
            for (int k = 0; k < user1.getAccounts().size(); k++) {
                Account acc = user1.getAccounts().get(k);
                if (acc instanceof SavingsAccount) {
                    ((SavingsAccount) acc).addInterest(numApplications);
                    database.store();
                }
            }

        }
    }
}
