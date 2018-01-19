import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvReader_Labeled {

	public static void main(String[] args) {
		String outputFile = "C:\\Francia\\Baseline1\\baseline1_LabeledNorm.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();

		try {
			
			CsvReader products = new CsvReader("C:\\Francia\\Baseline1\\Baseline_Normal_Data_Unlabeled.csv");
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out the header line
			

		
			products.readHeaders();
			
			if (!alreadyExists)
				{
					
					csvOutput.write("ProcName");
					csvOutput.write("Path");
					csvOutput.write("ProcessId");
					csvOutput.write("_time");
					csvOutput.write("app");
					csvOutput.write("app:able_to_transfer_file");
					csvOutput.write("app:category");
					csvOutput.write("app:default_ports");
					csvOutput.write("app:evasive");
					csvOutput.write("app:excessive_bandwidth");
					csvOutput.write("app:has_known_vulnerability");
					csvOutput.write("app:risk");
					csvOutput.write("app:subcategory");
					csvOutput.write("app:used_by_malware");
					csvOutput.write("application");
					csvOutput.write("bytes_in");
					csvOutput.write("bytes_out");
					csvOutput.write("dest_port");
					csvOutput.write("protocol");
					csvOutput.write("src_ip");
					csvOutput.write("threat:category");
					csvOutput.write("threat:cve");
					csvOutput.write("threat:severity");
					csvOutput.write("threat_id");
					csvOutput.write("threat_name");
					csvOutput.write("transport");
					csvOutput.write("type");
					csvOutput.write("url");
					csvOutput.write("DriveType");
					csvOutput.write("FileSystem");
					csvOutput.write("FreeSpaceKB");
					csvOutput.write("DriveName");
					csvOutput.write("TotalSpaceKB");
					csvOutput.write("UsedSpaceKB");
					csvOutput.write("EventCode");
					csvOutput.write("Logon_ID");
					csvOutput.write("User");
					csvOutput.write("Event_time");
					csvOutput.write("Network_transfer");
					csvOutput.write("Network_time");
					csvOutput.write("Traffic_type");
					csvOutput.write("Reg_time");
					csvOutput.write("Rdata");
					csvOutput.write("Revent_status");
					csvOutput.write("key_path");
					csvOutput.write("Rpid");
					csvOutput.write("Rprocess_image");
					csvOutput.write("registry_type");
					csvOutput.write("SEP_time");
					csvOutput.write("SEP_action");	
					csvOutput.write("SEP_app");	
					csvOutput.write("direction");
					csvOutput.write("SEP_local_port");		
					csvOutput.write("SEP_packet_id");	
					csvOutput.write("SEP_remote_host");		
					csvOutput.write("SEP_tag");
					csvOutput.write("Serv_Desc");	
					csvOutput.write("DiaplayName");
					csvOutput.write("Serv_Name");	
					csvOutput.write("StartMode");			
					csvOutput.write("Serv_Started");	
					csvOutput.write("Serv_time");	
					csvOutput.write("Normal");
					csvOutput.write("Abnormal");
										
					csvOutput.endRecord();
					
				}
				else
					System.exit(0);
				
			int recNum = 2;
			while (products.readRecord())
			{
				String ProcName = products.get("ProcName");
				//if (ProcName.equals("")) 
				//	continue;
				String Path = products.get("Path");
				String ProcessId = products.get("ProcessId");
				String _time = products.get("_time");
				String app = products.get("app");
				String transFile = products.get("app:able_to_transfer_file");
				String appCat = products.get("app:category");
				String appDefault = products.get("app:default_ports");
				String appEvasive = products.get("app:evasive");
				String appEvaBand = products.get("app:excessive_bandwidth");
				String appKnown = products.get("app:has_known_vulnerability");
				String appRisk = products.get("app:risk");
				String appSub = products.get("app:subcategory");
				String appUsed = products.get("app:used_by_malware");
				String Application = products.get("application");
				String bytesIn = products.get("bytes_in");
				String bytesOut = products.get("bytes_out");
				String dstPort = products.get("dest_port");
				String protocol = products.get("protocol");
				String srcIP = products.get("src_ip");
				String threatCat = products.get("threat:category");
				String threatCVE = products.get("threat:cve");
				String threatSev = products.get("threat:severity");
				String threatID = products.get("threat_id");
				String threatName = products.get("threat_name");
				String transport = products.get("transport");
				String type = products.get("type");
				String URL = products.get("url");
					
				String DriveType = products.get("DriveType");
				if (DriveType.equals("")) DriveType="fixed";
				
				String FSystem = products.get("FileSystem");
				if (FSystem.equals("NTFS"))   FSystem="NTFS";
				
				String FreeSpace = products.get("FreeSpaceKB");
				if (FreeSpace.equals(""))  FreeSpace="0";
				int fSpace = Integer.parseInt(FreeSpace);
				if (fSpace == 0) fSpace = 920000000 + (int)(Math.random()* 1000000 + 1);
				FreeSpace = Integer.toString(fSpace);
				
				String DriveName = products.get("Name");
				
				String TotalSpace = products.get("TotalSpaceKB");
				if (TotalSpace.equals(""))  TotalSpace="0";
				int tSpace = Integer.parseInt(TotalSpace);
				if (tSpace == 0) tSpace = 976743932;
				TotalSpace = Integer.toString(tSpace);
				
				String DiffSpace = Integer.toString(tSpace-fSpace);
				
				String EventCode = products.get("EventCode");
				String Logon_ID = products.get("Logon_ID");
				String User = products.get("User");
				String Event_time = products.get("Event_time");
				String Network_transfer = products.get("Network_transfer");
				String Network_time = products.get("Network_time");
				String Traffic_type = products.get("Traffic_type");
				String Reg_time = products.get("Reg_time");
				String Rdata = products.get("Rdata");
				String Revent_status = products.get("Revent_status");
				String key_path = products.get("key_path");
				String Rpid = products.get("Rpid");
				String Rprocess_image = products.get("Rprocess_image");
				String registry_type = products.get("registry_type");
				String SEP_time = products.get("SEP_time");
				String SEP_action = products.get("SEP_action");	
				String SEP_app = products.get("SEP_app");	
				String direction = products.get("direction");
				String SEP_local_port = products.get("SEP_local_port");		
				String SEP_packet_id = products.get("SEP_packet_id");	
				String SEP_remote_host = products.get("SEP_remote_host");		
				String SEP_tag = products.get("SEP_tag");
				String Serv_Desc = products.get("Serv_Desc");	
				String DisplayName = products.get("DiaplayName");
				String Serv_Name = products.get("Serv_Name");	
				String StartMode = products.get("StartMode");			
				String Serv_Started = products.get("Serv_Started");	
				String Serv_time = products.get("Serv_time");	
				String Normal = products.get("Normal");
				Normal = "1";
				String Abnormal = products.get("Abnormal");
				Abnormal = "0";
				//System.out.println("Record number: " + recNum);
				
				//write to output file
				csvOutput.write(ProcName);
				csvOutput.write(Path);
				csvOutput.write(ProcessId);
				csvOutput.write(_time);
				csvOutput.write(app);
				csvOutput.write(transFile);
				csvOutput.write(appCat);
				csvOutput.write(appDefault);
				csvOutput.write(appEvasive);
				csvOutput.write(appEvaBand);
				csvOutput.write(appKnown);
				csvOutput.write(appRisk);
				csvOutput.write(appSub);
				csvOutput.write(appUsed);
				csvOutput.write(Application);
				csvOutput.write(bytesIn);
				csvOutput.write(bytesOut);
				csvOutput.write(dstPort);
				csvOutput.write(protocol);
				csvOutput.write(srcIP);
				csvOutput.write(threatCat);
				csvOutput.write(threatCVE);
				csvOutput.write(threatSev);
				csvOutput.write(threatID);
				csvOutput.write(threatName);
			    csvOutput.write(transport);
				csvOutput.write(type);
				csvOutput.write(URL);
				csvOutput.write(DriveType);
				csvOutput.write(FSystem);
				csvOutput.write(FreeSpace);
				csvOutput.write(DriveName);
				csvOutput.write(TotalSpace);
				csvOutput.write(DiffSpace);
				
				csvOutput.write( EventCode );
				csvOutput.write( Logon_ID );
				csvOutput.write( User );
				csvOutput.write( Event_time );
				csvOutput.write( Network_transfer );
				csvOutput.write( Network_time );
				csvOutput.write( Traffic_type );
				csvOutput.write( Reg_time );
				csvOutput.write( Rdata );
				csvOutput.write( Revent_status );
				csvOutput.write( key_path );
				csvOutput.write( Rpid );
				csvOutput.write( Rprocess_image );
				csvOutput.write( registry_type );
				csvOutput.write( SEP_time );
				csvOutput.write( SEP_action );	
				csvOutput.write( SEP_app );	
				csvOutput.write( direction );
				csvOutput.write( SEP_local_port );		
				csvOutput.write( SEP_packet_id );	
				csvOutput.write( SEP_remote_host );		
				csvOutput.write( SEP_tag );
				csvOutput.write( Serv_Desc );	
				csvOutput.write( DisplayName );
				csvOutput.write( Serv_Name );	
				csvOutput.write( StartMode );			
				csvOutput.write( Serv_Started );	
				csvOutput.write( Serv_time );	
				csvOutput.write( Normal );
				csvOutput.write( Abnormal );
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
