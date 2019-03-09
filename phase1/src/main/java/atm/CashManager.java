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


    public boolean changeDenom(int bill, int amount){
        if (bill != 5 | bill != 10 | bill != 20 | bill !=50){
            return false;

        }
        else{
            if (checkDenom(bill, amount)){
                denominations[getIndex(bill)] += amount;
                return true;
            }
            else{
                return false;
            }
        }
    }


    //might remove
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
        }else if (bill == 5){
            return 1;
        }else if (bill == 5){
            return 2;
        }else{
            return 3;
        }
    }

    private String getBill(int bill){
        if (bill == 5){
            return "5 dollar bill(s)";
        }else if(bill == 10){
            return "10 dollar bill(s)";
        }else if(bill == 20){
            return "20 dollar bill(s)";
        }else{
            return "50 dollar bill(s)";
        }
    }

    // not working at the moment.
    public void update(String filePath){
        //FileReader file = new FileReader("alert.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i< 4; i++){
                if(denominations[i] < threshold){
                    writer.write(denominations[i] + " " + getBill(i) + " left, please restock");
                    writer.newLine();
                }
            }
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
