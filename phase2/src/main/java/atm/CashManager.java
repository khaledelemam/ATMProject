package atm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CashManager {

    // index 0,1,2,3 represents 5,10,20,50 dollar bills
    private int[] denominations;
    // the number of each denomination
    private int[] billNumber;

    private String[] denomString;

    private List<String> withdrawAmounts = new ArrayList<>();

    private String cashFile;
    private String alertFile;

    public CashManager() throws IOException {
        Filename fn = new Filename();
        cashFile = fn.getCashFile();
        alertFile = fn.getAlertFile();
        cashFromFile();

        if (withdrawAmounts.size() == 0){
            withdrawAmounts.add("5");
            withdrawAmounts.add("10");
            withdrawAmounts.add("20");
            withdrawAmounts.add("50");
            withdrawAmounts.add("100");
            withdrawAmounts.add("200");

        }
    }
    private int[] getDenominations(){
        return denominations;
    }

    List<String> getWithdrawAmounts(){
        return withdrawAmounts;
    }

    private int getLargestDenomination(int index){
        return denominations[denominations.length-1-index];
    }


    private void changeDenomination(int index, int amount) throws WithdrawException, IOException{
        if (checkDenominationAmount(index)){
            billNumber[billNumber.length-1 - index] -= amount;
            writeToFile();
        }
        else {
             throw new WithdrawException();

        }
    }

    private boolean checkDenominationAmount(int index){

        return (billNumber[billNumber.length-1-index] > 0);
    }

// this method is for the alert part in admin
    private String getBill(int index){
        return denomString[index] + "-dollar bills";
    }


    //sets denominations as the values from text file
    private void cashFromFile() throws IOException {
        File file = new File(cashFile);
        BufferedReader cashReader = new BufferedReader(new FileReader(file));

        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[(int)file.length()];
        fis.read(byteArray);
        String data = new String(byteArray);
        String[] stringArray = data.split("\n");
        System.out.println("Number of lines in the file are :"+stringArray.length);

        int listLength = Integer.parseInt(cashReader.readLine());

        int[] denominations = new int[listLength];
        int[] billNum = new int[listLength];
        String[] denomString = new String[listLength];

        for (int i = 0; i < denominations.length; i++){
            //splits line into 2
            String s = cashReader.readLine();
            String[] splitted = s.split(" ");

            denomString[i] = splitted[0];
            denominations[i] = Integer.parseInt(splitted[1]);
            billNum[i] = Integer.parseInt(splitted[2]);

        }
        this.denomString = denomString;
        this.denominations = denominations;
        this.billNumber = billNum;
    }

    //writes current denominations to file
    private void writeToFile() throws IOException {
        File file = new File(cashFile);
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for (int i = 0; i< denominations.length; i++){
            writer.print(denomString[i] + " ");
            writer.print(denominations[i] + " ");
            writer.println(billNumber[i]);
        }
        writer.close();
    }

    // sends an alert when a denomination falls below the threshold
    void update() throws IOException {

        final int threshold = 20;

        File file = new File(alertFile);
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for (int i = 0; i< billNumber.length; i++){
            if(billNumber[i] < threshold){
                writer.println(billNumber[i] + " " + getBill(i) + " left, please restock");
            }
        }
        writer.close();
    }

    // Returns the alerts or a string that says "No Alerts"
     String showAlerts() throws IOException {
        File file = new File(alertFile);
        Scanner input = new Scanner(file);

        StringBuilder sb = new StringBuilder();

        if (!input.hasNext()){
            return "No Alerts";
        }

        while (input.hasNext()){
            sb.append(input.nextLine());
            sb.append("\n");
        }

        return sb.toString();

    }


    void subtractDenominations(double amount, int index) throws WithdrawException, IOException {

        int DecreaseBy = 1;

        if (amount == 0 || getDenominations().length == index) {
            assert true;
        }
        // if the greatestBill is not greater than the amount you're withdrawing
        // and if there is at least one denomination of the greatestBill
        else if (amount - getLargestDenomination(index) >= 0 && checkDenominationAmount(index)) {
            amount -= getLargestDenomination(index);
            changeDenomination(index, DecreaseBy);
            subtractDenominations(amount, index);
        } else {
            subtractDenominations(amount, index + 1);
        }

    }

//    public String toString(){
//        String s = "$5 dollar bills: " + getDenom(5) + "\n" +
//                "$10 dollar bills: " + getDenom(10) + "\n" +
//                "$20 dollar bills: " + getDenom(20) + "\n" +
//                "$50 dollar bills: " + getDenom(50) + "\n";
//
//        return s;
//    }



//    public int[] getDenominations(){
//        return this.denominations;
//    }

    //returns a single denomination
//    int getDenom(int bill){
//        return denominations[getIndex(bill)];
//    }


//    //bill must be 5, 10, 20, or 50
//    void changeDenomOld(int bill, int amount) throws NegativeDenominationException, IOException {
//        if (checkDenom(bill, amount)){
//            denominations[getIndex(bill)] += amount;
//            writeToFile();
//        }
//        else {
//            NegativeDenominationException e = new NegativeDenominationException();
//            throw e;
//        }
//    }


//
//    boolean checkDenomOld(int bill, int amount){
//        if (denominations[getIndex(bill)] + amount < 0){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }

//    private int getIndex(int bill){
//        if (bill == 5){
//            return 0;
//        }else if (bill == 10){
//            return 1;
//        }else if (bill == 20){
//            return 2;
//        }else if (bill == 50){
//            return 3;
//        }else
//            //returns an impossible index
//            return -99;
//    }
//
//    private String getBill(int index){
//        if (index == 0){
//            return "five-dollar bill(s)";
//        }else if(index == 1){
//            return "ten-dollar bill(s)";
//        }else if(index == 2){
//            return "twenty-dollar bill(s)";
//        }else{
//            return "fifty-dollar bill(s)";
//        }
//    }








}
