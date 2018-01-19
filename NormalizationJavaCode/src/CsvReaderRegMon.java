import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvReaderRegMon {

	public static void main(String[] args) {
		String outputFile = "C:\\Francia\\Baseline1\\baseline1_regmonNoBlanks.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();

		try {
			
			CsvReader products = new CsvReader("C:\\Francia\\Baseline1\\baseline1_regmon_Clean.csv");
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out the header line
			

		
			products.readHeaders();
			
			if (!alreadyExists)
				{
					csvOutput.write("Reg_time");
					csvOutput.write("Rdata");
					csvOutput.write("Rdata_type");
					csvOutput.write("Revent_status");
					csvOutput.write("key_path");
					csvOutput.write("Rpid");
					csvOutput.write("Rprocess_image");
					csvOutput.write("registry_type");
					
					csvOutput.endRecord();
					
				}
				else
					System.exit(0);
				
			int recNum = 2;
			while (products.readRecord())
			{
				String Reg_time = products.get("Reg_time");
				if (Reg_time.equals(""))
					continue;
				String Rdata = products.get("Rdata");
				String Rdata_type = products.get("Rdata_type");
				String Revent_status = products.get("Revent_status");
				String key_path = products.get("key_path");
				String Rpid = products.get("Rpid");
				String Rprocess_image = products.get("Rprocess_image");
				String registry_type = products.get("registry_type");
				//System.out.println("Record number: " + recNum);
				
				//write to output file
				csvOutput.write(Reg_time);
				csvOutput.write(Rdata);
				csvOutput.write(Rdata_type);
				csvOutput.write(Revent_status);
				csvOutput.write(key_path);
				csvOutput.write(Rpid);
				csvOutput.write(Rprocess_image);
				csvOutput.write(registry_type);
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
