package instruments;

import java.io.*;
import java.text.*;
import java.util.*;

public class Instruments {

	// public static void main(String[] args) {

	public void readTextFiles() {
		Instruments instruments = new Instruments();
		String filePath1 = "C:/Users/vinay.anant/Desktop/inst.txt";
		String filePath2 = "C:/Users/vinay.anant/Desktop/trades.txt";
		List<String[]> instFileData = new ArrayList<String[]>();
		List<String[]> tradesFileData = new ArrayList<String[]>();
		List<IntrumentTradeDetails> listOfIntrumentTradeDetails = new ArrayList<IntrumentTradeDetails>();
		List<IntrumentsNotTraded> listOfIintrumentsNotTraded = new ArrayList<IntrumentsNotTraded>();
		List<String> instrumentsTraded = new ArrayList<String>();
		int indexOfInID = 0;
		int indexOfTradeDtTime = 0;
		int indexOfQty = 0;
		String sDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Get the date range
			Scanner in = new Scanner(System.in);
			System.out.println("Enter Start date in format dd/mm/yyyy: ");
			sDate = in.nextLine();
			Date startDate = sdf.parse(sDate);

			System.out.println("Enter To date in format dd/mm/yyyy: ");
			sDate = in.nextLine();
			Date toDate = sdf.parse(sDate);

			// Read date from files
			instFileData = instruments.readFile(filePath1);
			tradesFileData = instruments.readFile(filePath2);

			// PART 1:
			// GET Instrument wise traded volume between the date range
			// Get index of "InID", "TradeDtTime" & "qty" from trades details
			String[] headerRow = tradesFileData.get(0);
			for (int i = 0; i < headerRow.length; i++) {
				if (headerRow[i].equalsIgnoreCase("inid"))
					indexOfInID = i;
				else if (headerRow[i].equalsIgnoreCase("tradedttime"))
					indexOfTradeDtTime = i;
				else if (headerRow[i].equalsIgnoreCase("qty"))
					indexOfQty = i;
			}

			for (String[] stringArr : tradesFileData) {
				if (Arrays.asList(stringArr).contains("TradeDtTime")) {
					// Read header row. Moving to next.
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

						listOfIntrumentTradeDetails
								.add(intrumentTradeDetailsObj);
					}

				}

			}

			System.out.println();
			System.out
					.println("Instrument wise traded volume between the date range:");
			System.out
					.println("Instrument ID, Instrument Name, Trading Date, Volumne");

			for (IntrumentTradeDetails tradeDetails : listOfIntrumentTradeDetails) {
				System.out.println(tradeDetails.intrumentId + ","
						+ tradeDetails.intrumentName + ","
						+ tradeDetails.tradingDate + "," + tradeDetails.volume);
			}

			System.out.println();

			// PART 2:
			// GET List of instrument not traded between the dates
			for (String[] strInstData : instFileData) {

				if (Arrays.asList(strInstData).contains("instname")) {

					// Read header row. Moving to next.

				} else if (!instrumentsTraded.contains(strInstData[0])) {
					IntrumentsNotTraded intrumentsNotTradedObj = new IntrumentsNotTraded();
					intrumentsNotTradedObj.intrumentId = strInstData[0];
					intrumentsNotTradedObj.intrumentName = strInstData[1];
					listOfIintrumentsNotTraded.add(intrumentsNotTradedObj);
				}

			}
			System.out.println();
			System.out
					.println("List of instrument not traded between the dates "
							+ startDate + " & " + toDate);
			System.out.println("Instrument ID, Instrument Name");

			for (IntrumentsNotTraded instrumentDetails : listOfIintrumentsNotTraded) {

				System.out.println(instrumentDetails.intrumentId + ","
						+ instrumentDetails.intrumentName);

			}

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	private List<String[]> readFile(String filePath) throws IOException {

		List<String[]> FileData = new ArrayList<String[]>();
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String line;

		System.out.println("File Contents:");
		while ((line = br.readLine()) != null) {
			// line = br.readLine();
			System.out.println(line);
			FileData.add(line.split(","));
		}
		return FileData;

	}
} // End of class Program

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