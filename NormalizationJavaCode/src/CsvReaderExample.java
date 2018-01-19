import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvReaderExample {

	public static void main(String[] args) {
		String outputFile = "C:\\Francia\\Baseline1\\baseline1_diskmonNoCD.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();

		try {
			
			CsvReader products = new CsvReader("C:\\Francia\\Baseline1\\baseline1_diskmon_clean.csv");
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out the header line
			

		
			products.readHeaders();
			
			if (!alreadyExists)
				{
					csvOutput.write("DriveType");
					csvOutput.write("FileSystem");
					csvOutput.write("FreeSpaceKB");
					csvOutput.write("Name");
					csvOutput.write("TotalSpaceKB");
					csvOutput.write("UsedSpaceKB");
					csvOutput.endRecord();
					
				}
				else
					System.exit(0);
				
			int recNum = 2;
			while (products.readRecord())
			{
				String DriveType = products.get("DriveType");
				if (DriveType.equals("CD-ROM") || DriveType.equals("removable"))
					continue;
				String FSystem = products.get("FileSystem");
				String FreeSpace = products.get("FreeSpaceKB");
				int fSpace = Integer.parseInt(FreeSpace);
				String DriveName = products.get("Name");
				String TotalSpace = products.get("TotalSpaceKB");
				int tSpace = Integer.parseInt(TotalSpace);
				String DiffSpace = Integer.toString(tSpace-fSpace);
				//System.out.println("Record number: " + recNum);
				
				//write to output file
				csvOutput.write(DriveType);
				csvOutput.write(FSystem);
				csvOutput.write(FreeSpace);
				csvOutput.write(DriveName);
				csvOutput.write(TotalSpace);
				csvOutput.write(DiffSpace);
				csvOutput.endRecord();
					
				recNum++;
			}
	
			products.close();
			csvOutput.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
