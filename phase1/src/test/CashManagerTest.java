//import atm.CashManager;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Scanner;
//
//import static org.junit.Assert.assertTrue;
//
//public class CashManagerTest {
//
//    private String filePath;
//    private CashManager cm;
//
//    @Before
//    public void setUp(){
//        int threshold = 20;
//        int[] denominations = new int[]{30,30,30,30};
//        filePath = "CashManagerTest.txt";
//
//        cm = new CashManager(denominations, threshold);
//    }
//
//
//    @Test
//    public void alertTest(){
//        cm.changeDenom(0,-15);
//        cm.update(filePath);
//        String[] alertMsgs = new String[4];
//
//        Scanner input = new Scanner(filePath);
//        for(int i = 0; i < 4; i++){
//            alertMsgs[i] = input.nextLine();
//        }
//
//        String msg = "15 5 dollar bills left, please restock";
//        assertTrue(alertMsgs[0].equalsIgnoreCase(""));
//    }
//
//}
