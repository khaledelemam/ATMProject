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
    private List<String> bills = new ArrayList<>();

    private String cashFile;
    private String alertFile;

    public CashManager() throws IOException {
        Filename fn = new Filename();
        cashFile = fn.getCashFile();
        alertFile = fn.getAlertFile();
        cashFromFile();
        update();

        if (withdrawAmounts.size() == 0){
            withdrawAmounts.add("5");
            withdrawAmounts.add("10");
            withdrawAmounts.add("20");
            withdrawAmounts.add("50");
            withdrawAmounts.add("100");
            withdrawAmounts.add("200");

        }
    }
    int[] getDenominations(){
        return denominations;
    }
    int[] getBillNumber() {return billNumber;}

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

//        int listLength = Integer.parseInt(cashReader.readLine());

        int[] denominations = new int[stringArray.length];
        int[] billNum = new int[stringArray.length];
        String[] denomString = new String[stringArray.length];

        for (int i = 0; i < denominations.length; i++){
            //splits line into 2
            String s = cashReader.readLine();
            String[] split = s.split(" ");

            denomString[i] = split[0];
            denominations[i] = Integer.parseInt(split[1]);
            billNum[i] = Integer.parseInt(split[2]);

        }
        this.denomString = denomString;
        this.denominations = denominations;
        this.billNumber = billNum;
    }

    //writes current denominations to file
    void writeToFile() throws IOException {
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
        else if (amount - getLargestDenomination(index) >= 0) {
            amount -= getLargestDenomination(index);
            changeDenomination(index, DecreaseBy);
            subtractDenominations(amount, index);
        } else {
            subtractDenominations(amount, index + 1);
        }

    }

    List<String> getDenomList(){

        for (int i = 0 ; i < getDenominations().length; i++) {
            int num = getDenominations()[i];
            bills.add(Integer.toString(num));
        }
        return bills;

    }


//
//    public String toString(){
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0 ; i < getDenominations().length; i++) {
//            int num = getDenominations()[i];
//            sb.append(num);
//            sb.append("\n");
//        }
//        return sb.toString();
//
//    }




}
