package atm.Model;

import atm.Model.transactions.WithdrawException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CashManager {

    // index 0,1,2,3 represents 5,10,20,50 dollar bills
    private int[] denominations;
    // the number of each denomination
    private int[] billNumber;
    private String[] denominationString;

    private List<String> withdrawAmounts = new ArrayList<>();


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
    public int[] getDenominations(){
        return denominations;
    }

    public int[] getBillNumber() {return billNumber;}

    public List<String> getWithdrawAmounts(){
        return withdrawAmounts;
    }

    public int getLargestDenomination(int index){
        return denominations[denominations.length-1-index];
    }

    // this method is for the alert part in admin
    private String getBill(int index){
        return denominationString[index] + "-dollar bills";
    }


    //sets denominations as the values from text file
    private void cashFromFile() throws IOException {
        File file = new File(cashFile);
        BufferedReader cashReader = new BufferedReader(new FileReader(file));

        int lines = FileLines();
        int[] denominations = new int[lines];
        int[] billNum = new int[lines];
        String[] denomString = new String[lines];

        for (int i = 0; i < denominations.length; i++){
            //splits line into 3
            String s = cashReader.readLine();
            String[] split = s.split(" ");

            denomString[i] = split[0];
            denominations[i] = Integer.parseInt(split[1]);
            billNum[i] = Integer.parseInt(split[2]);

        }
        this.denominationString = denomString;
        this.denominations = denominations;
        this.billNumber = billNum;
    }



    private int FileLines() throws IOException{
        File file = new File(cashFile);
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[(int)file.length()];
        fis.read(byteArray);
        String data = new String(byteArray);
        String[] stringArray = data.split("\n");
        return stringArray.length;
    }

    //writes current denominations to file
   public void writeToFile() throws IOException {
        File file = new File(cashFile);
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for (int i = 0; i< denominations.length; i++){
            writer.print(denominationString[i] + " ");
            writer.print(denominations[i] + " ");
            writer.println(billNumber[i]);
        }
        writer.close();
    }

    // sends an alert when a denomination falls below the threshold
    public void update() throws IOException {

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
     public String showAlerts() throws IOException {
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
    public void ReStockATM(int amount, int bill) throws IOException{
        for (int i = 0; i < getDenominations().length; i++){
            if (getDenominations()[i] == bill){
                getBillNumber()[i]  += amount;
            }
        }
        writeToFile();
        update();

    }




}
