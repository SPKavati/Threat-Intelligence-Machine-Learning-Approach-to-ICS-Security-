import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;

public class CsvReaderExample {

	public static void main(String[] args) {
		try {
			
			CsvReader products = new CsvReader("C:\\Francia\\Baseline1\\baseline1_diskmon.csv");
		
			products.readHeaders();
			int recNum = 2;
			while (products.readRecord())
			{
				String DriveType = products.get("DriveType");
				if (DriveType.equals("CD-ROM"))
				        System.out.println("Record number: " + recNum);
				recNum++;
			}
	
			products.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
