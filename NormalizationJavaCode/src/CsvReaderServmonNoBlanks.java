import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvReaderServmonNoBlanks {

	public static void main(String[] args) {
		String outputFile = "C:\\Francia\\Baseline1\\baseline1_Servmon_NoBlanks.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();

		try {
			
			CsvReader products = new CsvReader("C:\\Francia\\Baseline1\\baseline1_servmon_Clean.csv");
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out the header line
			

		
			products.readHeaders();
			
			if (!alreadyExists)
				{
					csvOutput.write("Serv_Desc");
					csvOutput.write("DisplayName");
					csvOutput.write("Serv_Name");
					csvOutput.write("Serv_Path");
					csvOutput.write("StartMode");
					csvOutput.write("Serv_Started");
					csvOutput.write("Serv_State");
					csvOutput.write("Serv_time");
					
					csvOutput.endRecord();
					
				}
				else
					System.exit(0);
				
			int recNum = 2;
			while (products.readRecord())
			{
				String Serv_Desc = products.get("Serv_Desc");
				String DisplayName = products.get("DisplayName");
				if (DisplayName.equals(""))
					continue;
				String Serv_Name = products.get("Serv_Name");
				String Serv_Path = products.get("Serv_Path");
				String StartMode = products.get("StartMode");
				String Serv_Started = products.get("Serv_Started");
				String Serv_State = products.get("Serv_State");
				String Serv_time = products.get("Serv_time");
				//System.out.println("Record number: " + recNum);
				
				//write to output file
				csvOutput.write(Serv_Desc);
				csvOutput.write(DisplayName);
				csvOutput.write(Serv_Name);
				csvOutput.write(Serv_Path);
				csvOutput.write(StartMode);
				csvOutput.write(Serv_Started);
				csvOutput.write(Serv_State);
				csvOutput.write(Serv_time);
								
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
