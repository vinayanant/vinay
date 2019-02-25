import javax.swing.text.DateFormatter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.*;
import java.util.*;
import java.io.*;

public class Trades {
public static String valueOfInID;
public static String ValueOfInst;
    public static void main(String [] args) throws IOException {
        // getData();
        readFileTrade();
        readFileInstrument();
    }
    public static void getData()
    {
        Scanner in=new Scanner(System.in);
        String s=in.nextLine();
        System.out.println("Enter the date in format ddmmyyyy"+s);

    }

    public static void readFileTrade () throws IOException {
        String filename = "C:/Users/vinay1990/Desktop/trades.txt";
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;

        List<String[]> eachLine = new ArrayList<String[]>();

        while ((line = br.readLine()) != null) {

            line = br.readLine();
            String[] lineDate = line.split(",");
            eachLine.add(lineDate);
            for (String str[] : eachLine) {
//                String b = str.split("\\s+");
                String splittedDate[]=str[1].split("\\s+");
                System.out.println(splittedDate[0]);      //Final date
               // System.out.println(str[1].split("\\s+"));

                valueOfInID=str[2];


            }
        }
    }
        public static void readFileInstrument() throws IOException {
            String filename1 = "C:/Users/vinay1990/Desktop/inst.txt";
            File file1 = new File(filename1);
            FileInputStream fis1 = new FileInputStream(file1);
            InputStreamReader isr1 = new InputStreamReader(fis1);
            BufferedReader br1 = new BufferedReader(isr1);
            String line1;
            List<String[]> eachLine1 = new ArrayList<String[]>();

            while ((line1 = br1.readLine()) != null) {

                line1 = br1.readLine();
                String[] lineDate1 = line1.split(",");
                eachLine1.add(lineDate1);
                ValueOfInst= String.valueOf(eachLine1);
                if(valueOfInID.equals(ValueOfInst)) //Compare institutions
                {
                    System.out.println("true");
                }

//                for (String str[] : eachLine1) {
////                String b = str.split("\\s+");
//
//                }
            }
            // br.close();
            //  }

//    public void test()
//    {
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////        Date date1 = sdf.parse("2009-12-31");
////        Date date2 = sdf.parse("2010-01-31");
//
//
//    }

        }


}
