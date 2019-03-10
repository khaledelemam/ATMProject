package atm;

import java.io.*;

public class CashManager {

    // index 0,1,2,3 represents 5,10,20,50 dollar bills
    private int[] denominations;

    // Sends alert when denomination falls below this amount
    private int threshold;

    public CashManager(int[] denominations, int threshold){
        this.denominations = denominations;
        this.threshold = threshold;
    }

    //returns the array of denominations
    public int[] getDenominations(){
        return this.denominations;
    }

    //returns a single denomination
    public int getDenom(int bill){
        return denominations[getIndex(bill)];
    }

    //bill must be 5, 10, 20, or 50
    public void changeDenom(int bill, int amount) throws NegativeDenominationException{
        if (checkDenom(bill, amount)){
            denominations[getIndex(bill)] += amount;
        }
        else{
            NegativeDenominationException e = new NegativeDenominationException();
            throw e;
        }
    }

    public boolean checkDenom(int bill, int amount){
        if (denominations[getIndex(bill)] + amount < 0){
            return false;
        }
        else{
            return true;
        }
    }

    private int getIndex(int bill){
        if (bill == 5){
            return 0;
        }else if (bill == 10){
            return 1;
        }else if (bill == 20){
            return 2;
        }else if (bill == 50){
            return 3;
        }else
            //returns an impossible index
            return -99;
    }

    private String getBill(int index){
        if (index == 0){
            return "five-dollar bill(s)";
        }else if(index == 1){
            return "ten-dollar bill(s)";
        }else if(index == 2){
            return "twenty-dollar bill(s)";
        }else{
            return "fifty-dollar bill(s)";
        }
    }

    // sends an alert when a denomination falls below the threshold
    public void update(String filePath){
        File file = new File(filePath);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            for (int i = 0; i< 4; i++){
                if(denominations[i] < threshold){
                    writer.println(denominations[i] + " " + getBill(i) + " left, please restock");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        String s = "$5 dollar bills: " + getDenom(5) + "\n" +
                "$10 dollar bills: " + getDenom(10) + "\n" +
                "$20 dollar bills: " + getDenom(20) + "\n" +
                "$50 dollar bills: " + getDenom(50) + "\n";

        return s;
    }

}
