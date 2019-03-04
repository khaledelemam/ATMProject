package atm;

import java.io.*;
import java.util.*;

public class CashManager {

    // index 0,1,2,3 represents 5,10,20,50 dollar bills
    private int[] denominations;

    // Sends alert when denomination falls below this amount
    private int threshold;

    public CashManager(int[] denominations, int threshold){
        this.denominations = denominations;
        this.threshold = threshold;
    }

    public int[] getDenom(){
        return this.denominations;
    }

    public void changeDenom(int index, int amount){
        if (index < 0 | index > 3){
            //exception?
        }
        else{
            if (checkDenom(index, amount)){
                denominations[index] += amount;
            }
            else{
                //error message
            }
        }
    }

    //might remove
    private boolean checkDenom(int index, int amount){
        if (denominations[index] + amount < 0){
            return false;
        }
        else{
            return true;
        }
    }

    private String getBill(int index){
        if (index == 0){
            return "5 dollar bill(s)";
        }else if(index == 1){
            return "10 dollar bill(s)";
        }else if(index == 2){
            return "20 dollar bill(s)";
        }else{
            return "50 dollar bill(s)";
        }
    }

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

}
