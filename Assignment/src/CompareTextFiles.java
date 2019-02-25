//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ListIterator;
//
//public class CompareTextFiles
//{
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader reader1 = new BufferedReader(new FileReader("C:/Users/vinay1990/Desktop/trades.txt"));
//
//        BufferedReader reader2 = new BufferedReader(new FileReader("C:/Users/vinay1990/Desktop/inst.txt"));
//
//        String line1 = reader1.readLine();
//        String line2 = reader2.readLine();
//        List<String[]> eachLine = new ArrayList<String[]>();
//
//        boolean areEqual = true;
//        int lineNum = 1;
//
//        while (line1 != null || line2 != null)
//        {
//            line1 = reader1.readLine();
//            String[] splitWords=line1.split(",");
//            String[] dgf=splitWords[1].split("\\s+");
//            String finalDate=dgf[0];         //Final date
//            String valueOfInId=splitWords[2].toString();   //Valueofinid
//            line2=reader2.readLine();
//            String[] splitWordsFile2=line1.split(",");
//            String[] valueOfInstid=splitWordsFile2[0];
//            System.out.println(valueOfInstid);
//
//
//
//
//
//
//            if(line1 == null || line2 == null)
//            {
//                areEqual = false;
//
//                break;
//            }
//            else if(! line1.equalsIgnoreCase(line2))
//            {
//                areEqual = false;
//
//                break;
//            }
//
//            line1 = reader1.readLine();
//
//            line2 = reader2.readLine();
//
//            lineNum++;
//        }
//
//        if(areEqual)
//        {
//            System.out.println("Two files have same content.");
//        }
//        else
//        {
//            System.out.println("Two files have different content. They differ at line "+lineNum);
//
//            System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
//        }
//
//        reader1.close();
//
//        reader2.close();
//    }
//}