import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvReaderSEP {

	public static void main(String[] args) {
		String outputFile = "C:\\Francia\\Baseline1\\baseline1_SEP-packet_NoBlanks.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();

		try {
			
			CsvReader products = new CsvReader("C:\\Francia\\Baseline1\\baseline1_SEP-packet_Clean.csv");
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out the header line
			

		
			products.readHeaders();
			
			if (!alreadyExists)
				{
					csvOutput.write("SEP_time");
					csvOutput.write("SEP_action");
					csvOutput.write("SEP_app");
					csvOutput.write("direction");
					csvOutput.write("SEP_local_port");
					csvOutput.write("SEP_packet_id");
					csvOutput.write("SEP_remote_host");
					csvOutput.write("SEP_remote_port");
					csvOutput.write("SEP_tag");
					
					csvOutput.endRecord();
					
				}
				else
					System.exit(0);
				
			int recNum = 2;
			while (products.readRecord())
			{
				String SEP_time = products.get("SEP_time");
				if (SEP_time.equals(""))
					continue;
				String SEP_action = products.get("SEP_action");
				String SEP_app = products.get("SEP_app");
				String direction = products.get("direction");
				String SEP_local_port = products.get("SEP_local_port");
				String SEP_packet_id = products.get("SEP_packet_id");
				String SEP_remote_host = products.get("SEP_remote_host");
				String SEP_remote_port = products.get("SEP_remote_port");
				String SEP_tag = products.get("SEP_tag");
				//System.out.println("Record number: " + recNum);
				
				//write to output file
				csvOutput.write(SEP_time);
				csvOutput.write(SEP_action);
				csvOutput.write(SEP_app);
				csvOutput.write(direction);
				csvOutput.write(SEP_local_port);
				csvOutput.write(SEP_packet_id);
				csvOutput.write(SEP_remote_host);
				csvOutput.write(SEP_remote_port);
				csvOutput.write(SEP_tag);
				
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
