package atm;

import java.io.*;

public class CashManager {

    // index 0,1,2,3 represents 5,10,20,50 dollar bills
    private int[] denominations;

    private final int threshold = 20;

    private String cashFile = "phase2/src/main/java/atm/cash.txt";
    private String alertFile = "phase2/src/main/java/atm/alerts.txt";

    public CashManager() throws IOException {
        cashFromFile();
    }

    public int[] getDenominations(){
        return this.denominations;
    }

    //returns a single denomination
    int getDenom(int bill){
        return denominations[getIndex(bill)];
    }

    //bill must be 5, 10, 20, or 50
    void changeDenom(int bill, int amount) throws NegativeDenominationException, IOException {
        if (checkDenom(bill, amount)){
            denominations[getIndex(bill)] += amount;
            writeToFile();
        }
        else {
            NegativeDenominationException e = new NegativeDenominationException();
            throw e;
        }
    }

    boolean checkDenom(int bill, int amount){
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

    //sets denominations as the values from text file
    private void cashFromFile() throws IOException {
        File file = new File(cashFile);
        BufferedReader cashReader = new BufferedReader(new FileReader(file));

        int[] denominations = new int[4];

        for (int i = 0; i <4; i++){
            denominations[i] = Integer.parseInt(cashReader.readLine());
        }
        this.denominations = denominations;
    }

    //writes current denominations to file
    private void writeToFile() throws IOException {
        File file = new File(cashFile);
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for (int i = 0; i< 4; i++){
            writer.println(denominations[i]);
        }
        writer.close();
    }


    // sends an alert when a denomination falls below the threshold
    void update() throws IOException {
        File file = new File(alertFile);
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for (int i = 0; i< 4; i++){
            if(denominations[i] < threshold){
                writer.println(denominations[i] + " " + getBill(i) + " left, please restock");
            }
        }
        writer.close();
    }

    public String toString(){
        String s = "$5 dollar bills: " + getDenom(5) + "\n" +
                "$10 dollar bills: " + getDenom(10) + "\n" +
                "$20 dollar bills: " + getDenom(20) + "\n" +
                "$50 dollar bills: " + getDenom(50) + "\n";

        return s;
    }

}
