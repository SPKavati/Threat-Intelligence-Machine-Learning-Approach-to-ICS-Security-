import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;


import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvReader_Labeled_AbnormalSeed {

	public static void main(String[] args) {
		String outputFile = "C:\\Francia\\Baseline1\\baseline1_LabeledSeed.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
		boolean insert = false;
		Random rand = new Random();
		int rValue;
		int yes=0, no=0;
		
		try {
			
			attribute = new CsvReader("C:\\Francia\\Baseline1\\baseline1_LabeledNorm.csv");
			// use FileWriter constructor that specifies open for appending
			csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out the header line
			

		
			attribute.readHeaders();
			
			if (!alreadyExists)
				{
					writeHeaders();
					
				}
				else
					System.exit(0);
				
			int recNum = 2;
			while (attribute.readRecord())
			{
				getValues();
				
				setValues();
				//System.out.println("Record number: " + recNum);
				
							
				recNum++;
				rValue = rand.nextInt(100) + 1;
				if (rValue < 50) {
					insert = true;
					yes++;
				}  else no++;
				
				
			}//end of while
			
			System.out.println(" YES: "  + yes + "--------------- NO: " + no);
			attribute.close();
			csvOutput.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} //end of main
	
	static void setValues()
	{
		try {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void getValues()
	{
		try {
			ProcName = attribute.get("ProcName");
			Path = attribute.get("Path");
			ProcessId = attribute.get("ProcessId");
			_time = attribute.get("_time");
			app = attribute.get("app");
			transFile = attribute.get("app:able_to_transfer_file");
			appCat = attribute.get("app:category");
			appDefault = attribute.get("app:default_ports");
			appEvasive = attribute.get("app:evasive");
			appEvaBand = attribute.get("app:excessive_bandwidth");
			appKnown = attribute.get("app:has_known_vulnerability");
			appRisk = attribute.get("app:risk");
			appSub = attribute.get("app:subcategory");
			appUsed = attribute.get("app:used_by_malware");
			Application = attribute.get("application");
			bytesIn = attribute.get("bytes_in");
			bytesOut = attribute.get("bytes_out");
			dstPort = attribute.get("dest_port");
			protocol = attribute.get("protocol");
			srcIP = attribute.get("src_ip");
			threatCat = attribute.get("threat:category");
			threatCVE = attribute.get("threat:cve");
			threatSev = attribute.get("threat:severity");
			threatID = attribute.get("threat_id");
			threatName = attribute.get("threat_name");
			transport = attribute.get("transport");
			type = attribute.get("type");
			URL = attribute.get("url");
				
			DriveType = attribute.get("DriveType");
			
			FSystem = attribute.get("FileSystem");
					
			FreeSpace = attribute.get("FreeSpaceKB");
			
			fSpace = Integer.parseInt(FreeSpace);
			if (fSpace == 0) fSpace = 920000000 + (int)(Math.random()* 1000000 + 1);
			FreeSpace = Integer.toString(fSpace);
			
			DriveName = attribute.get("Name");
			
			TotalSpace = attribute.get("TotalSpaceKB");
			
			tSpace = Integer.parseInt(TotalSpace);
			if (tSpace == 0) tSpace = 976743932;
			TotalSpace = Integer.toString(tSpace);
			
			DiffSpace = Integer.toString(tSpace-fSpace);
			
			EventCode = attribute.get("EventCode");
			Logon_ID = attribute.get("Logon_ID");
			User = attribute.get("User");
			Event_time = attribute.get("Event_time");
			Network_transfer = attribute.get("Network_transfer");
			Network_time = attribute.get("Network_time");
			Traffic_type = attribute.get("Traffic_type");
			Reg_time = attribute.get("Reg_time");
			Rdata = attribute.get("Rdata");
			Revent_status = attribute.get("Revent_status");
			key_path = attribute.get("key_path");
			Rpid = attribute.get("Rpid");
			Rprocess_image = attribute.get("Rprocess_image");
			registry_type = attribute.get("registry_type");
			SEP_time = attribute.get("SEP_time");
			SEP_action = attribute.get("SEP_action");	
			SEP_app = attribute.get("SEP_app");	
			direction = attribute.get("direction");
			SEP_local_port = attribute.get("SEP_local_port");		
			SEP_packet_id = attribute.get("SEP_packet_id");	
			SEP_remote_host = attribute.get("SEP_remote_host");		
			SEP_tag = attribute.get("SEP_tag");
			Serv_Desc = attribute.get("Serv_Desc");	
			DisplayName = attribute.get("DiaplayName");
			Serv_Name = attribute.get("Serv_Name");	
			StartMode = attribute.get("StartMode");			
			Serv_Started = attribute.get("Serv_Started");	
			Serv_time = attribute.get("Serv_time");	
			Normal = attribute.get("Normal");
			Abnormal = attribute.get("Abnormal");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	static void writeHeaders()
	{
		try{
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	static CsvReader attribute;
	static CsvWriter csvOutput;
	static String ProcName, Path, ProcessId, _time, app, transFile, appCat, appDefault, appEvasive, appEvaBand, appKnown, appRisk, appSub;
	static String appUsed, Application, bytesIn, bytesOut, dstPort, protocol, srcIP, threatCat, threatCVE, threatSev,threatID,threatName;
	static String transport, type, URL, DriveType, FSystem, FreeSpace;
	static int fSpace, tSpace;
	static String DriveName, TotalSpace,DiffSpace, EventCode, Logon_ID, User, Event_time, Network_transfer, Network_time,Traffic_type, Reg_time;
	static String Rdata,Revent_status, key_path, Rpid, Rprocess_image, registry_type,SEP_time, SEP_action, SEP_app, direction,SEP_local_port;		
	static String SEP_packet_id, SEP_remote_host, SEP_tag, Serv_Desc, DisplayName, Serv_Name, StartMode, Serv_Started, Serv_time;	
	static String Normal, Abnormal;
	
}
