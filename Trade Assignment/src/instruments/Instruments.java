package instruments;
import java.io.*;
import java.text.*;
import java.util.*;

public class Instruments {

    public static Date startDate = new Date();
    public static Date toDate = new Date();
    public static List<String> instrumentsTraded = new ArrayList<String>();

    public void getDates() {
        // Declare variables
        String sDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Get the date range
            Scanner in = new Scanner(System.in);
            System.out.println("Enter Start date in format dd/mm/yyyy: ");
            sDate = in.nextLine();
            startDate = sdf.parse(sDate);

            System.out.println("Enter To date in format dd/mm/yyyy: ");
            sDate = in.nextLine();
            toDate = sdf.parse(sDate);
        } catch (Exception e) {
            System.out.println("Exception in getDate: " + e.getStackTrace());
        }
    }

    public void getInstrumentWiseTradedVolume() {
        // Declare variables
        List<String[]> instFileData = new ArrayList<String[]>();
        List<String[]> tradesFileData = new ArrayList<String[]>();
        Instruments instruments = new Instruments();
        File instFileLocation = new File("inst.txt");
        File tradeFileLocation = new File("trades.txt");
        String filePath1 = instFileLocation.getAbsolutePath();
        String filePath2 = tradeFileLocation.getAbsolutePath();
        int indexOfInID = 0;
        int indexOfTradeDtTime = 0;
        int indexOfQty = 0;
        String headerOfInID = "inid";
        String headerOfTradeDtTime = "TradeDtTime";
        String headerOfQty = "qty";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<IntrumentTradeDetails> listOfIntrumentTradeDetails = new ArrayList<IntrumentTradeDetails>();

        try {
            // Read data from files
            instFileData = instruments.readFile(filePath1);
            tradesFileData = instruments.readFile(filePath2);

            // PART 1:
            // GET Instrument wise traded volume between the date range
            // Get index of "InID", "TradeDtTime" & "qty" from trades details
            String[] headerRow = tradesFileData.get(0);
            for (int i = 0; i < headerRow.length; i++) {
                if (headerRow[i].equalsIgnoreCase(headerOfInID))
                    indexOfInID = i;
                else if (headerRow[i].equalsIgnoreCase(headerOfTradeDtTime))
                    indexOfTradeDtTime = i;
                else if (headerRow[i].equalsIgnoreCase(headerOfQty))
                    indexOfQty = i;
            }

            for (String[] stringArr : tradesFileData) {
                if (Arrays.asList(stringArr).contains(headerOfTradeDtTime)) {
                    //Read header row. Moving to next.
                } else {
                    String[] date = stringArr[indexOfTradeDtTime].split(" ");
                    Date dt = sdf.parse(date[0]);

                    if (dt.after(startDate) && dt.before(toDate)) {
                        IntrumentTradeDetails intrumentTradeDetailsObj = new IntrumentTradeDetails();
                        intrumentTradeDetailsObj.intrumentId = stringArr[indexOfInID];
                        intrumentTradeDetailsObj.tradingDate = stringArr[indexOfTradeDtTime];
                        intrumentTradeDetailsObj.volume = stringArr[indexOfQty];

                        // Get the name of the instrument from the Inst file
                        for (String[] strInstData : instFileData) {
                            if (strInstData[0].equals(stringArr[indexOfInID])) {
                                instrumentsTraded.add(strInstData[0]);
                                intrumentTradeDetailsObj.intrumentName = strInstData[1];
                                break;
                            }
                        }

                        listOfIntrumentTradeDetails.add(intrumentTradeDetailsObj);
                    }
                }
            }

            System.out.println();
            System.out.println("Instrument wise traded volume between the date range:");
            System.out.println("Instrument ID, \tInstrument Name, \tTrading Date, \t\t\tVolume");

            for (IntrumentTradeDetails tradeDetails : listOfIntrumentTradeDetails) {
                System.out.println(tradeDetails.intrumentId + ", \t\t\t" + tradeDetails.intrumentName + ", \t\t" + tradeDetails.tradingDate + ", \t" + tradeDetails.volume);
            }

            System.out.println();
        } catch (Exception e) {
            System.out.println("Exception in getInstrumentWiseTradedVolume: ");
            e.printStackTrace();
        }
    }

    public void getIntrumentsNotTraded() {
        // Declare variables
        Instruments instruments = new Instruments();
        List<String[]> instFileData = new ArrayList<String[]>();
        File instFileLocation = new File("inst.txt");
        String filePath1 = instFileLocation.getAbsolutePath();
        List<IntrumentsNotTraded> listOfIintrumentsNotTraded = new ArrayList<IntrumentsNotTraded>();
        String headerOfInstName = "instname";

        try {
            // Read data from files
            instFileData = instruments.readFile(filePath1);

            // PART 2:
            // GET List of instrument not traded between the dates
            for (String[] strInstData : instFileData) {

                if (Arrays.asList(strInstData).contains(headerOfInstName)) {
                    //Read header row. Moving to next.
                } else if (!instrumentsTraded.contains(strInstData[0])) {
                    IntrumentsNotTraded intrumentsNotTradedObj = new IntrumentsNotTraded();
                    intrumentsNotTradedObj.intrumentId = strInstData[0];
                    intrumentsNotTradedObj.intrumentName = strInstData[1];
                    listOfIintrumentsNotTraded.add(intrumentsNotTradedObj);
                }
            }
            System.out.println();
            System.out.println("List of instrument not traded between the dates " + startDate + " & " + toDate);
            System.out.println("Instrument ID, \tInstrument Name");

            for (IntrumentsNotTraded instrumentDetails : listOfIintrumentsNotTraded) {
                System.out.println(instrumentDetails.intrumentId + ", \t\t\t" + instrumentDetails.intrumentName);
            }
        } catch (Exception e) {
            System.out.println("Exception in getIntrumentsNotTraded: ");
            e.printStackTrace();
        }
    }


    private List<String[]> readFile(String filePath) throws IOException {

        List<String[]> FileData = new ArrayList<String[]>();
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;


        while ((line = br.readLine()) != null) {
            //line = br.readLine();
            FileData.add(line.split(","));
        }
        return FileData;

    }
} // End of class Instruments

class IntrumentTradeDetails {
    String intrumentId;
    String intrumentName;
    String tradingDate;
    String volume;
}


class IntrumentsNotTraded {
    String intrumentId;
    String intrumentName;
}