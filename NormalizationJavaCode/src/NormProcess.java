import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;


import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class NormProcess {

	public static void main(String[] args) {
		String outputFile = "C:\\Francia\\\\Baseline1\\Normalized.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
		boolean insert = false;
		Random rand = new Random();
		int rValue;
		int yes=0, no=0;
		
		try {
			
			//NoSeedAttribute = new CsvReader("C:\\Francia\\Baseline1\\baseline1_LabeledSeedNorm.csv");
			SeededAttribute = new CsvReader("C:\\Francia\\\\Baseline1\\baseline1_LabeledSeed.csv");
			// use FileWriter constructor that specifies open for appending
			//csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			csvOutput = new CsvWriter(new FileWriter(outputFile), ',');
			// if the file didn't already exist then we need to write out the header line
			

		
			//NoSeedAttribute.readHeaders();
			SeededAttribute.readHeaders();
			
			///if (!alreadyExists)
					writeHeaders();
				//else
				//	System.exit(0);
				
			int recNum = 2;
			while (SeededAttribute.readRecord())
			{
				getValues();
				setValues();
										
				recNum++;
		
			}//end of while
			
			//System.out.println(" YES: "  + yes + "--------------- NO: " + no);
			//NoSeedAttribute.close();
			SeededAttribute.close();
			csvOutput.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} //end of main
	
	//function to convert time to a numeric equivalent
	static String parse_time (String inTime)
	{
		if (inTime.equals(""))
		    return "0";
			
		String s, temp;
		s = inTime;
		temp = "";
		int i=0;
		for (char c:s.toCharArray()){
			if ((i > 10) && (i <13))
				temp += c;
				else if ((i> 13) && (i <16))
					temp += c;
				else if ((i > 16) && (i < 19))
					temp += c;
				i++;
				if (i >19) break;
		}
		return temp;	
	}
	
	//function to parse an IP address and convert it to numeric value
	static String parse_IP(String inIP)
	{
		if (inIP.equals(""))
			return "0";
		
		String temp = inIP;
		
		String delims="[.]";
		String[] tokens = temp.split(delims);
			
		int val=0, exp=3;
		for (String s: tokens){
			val += Integer.parseInt(s) * Math.pow(10, exp);
			exp--;
		}
		return Integer.toString(val);
	}
	
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
			 String temp="";
			//if (whichFile==1)
			//	attribute = NoSeedAttribute;
			//else 
				attribute = SeededAttribute;
			
			ProcName = attribute.get("ProcName");
			if (ProcName.equals(""))  ProcName ="0";
			else 					  ProcName = "1";
			
			Path = attribute.get("Path");
			if (Path.equals("")) 	Path ="0";
			else 					Path = "1";
			
			//process ID
			ProcessId = attribute.get("ProcessId");
			
			//Process Time
			_time = attribute.get("_time");
			if (!(_time.equals("")))
			{
				String s;
				s = _time;
				temp = "";
				int i=0;
				for (char c:s.toCharArray()){
					if ((i > 10) && (i <13))
						temp += c;
					else if ((i> 13) && (i <16))
						temp += c;
					else if ((i > 16) && (i < 19))
						temp += c;
					i++;
					if (i >19) break;
				}
				_time  = temp;
			}
			else   _time = "0";
			
			//Process Name
			app = attribute.get("app");
			if (app.equals("")) 	app ="0";
			else 					app = "1";
			
			//Able to transfer file
			transFile = attribute.get("app:able_to_transfer_file");
			if (transFile.equals("")) 			transFile="0";
			else if (transFile.equals("no")) 	transFile ="1";
			else 								transFile = "2";
			
			//application category
			appCat = attribute.get("app:category");
			if (appCat.equals("")) 						appCat = "0";
			else if (appCat.equals("general-internet"))	appCat = "1";
			else appCat = "2";

			//application default port
			appDefault = attribute.get("app:default_ports");
			if (!(appDefault.equals("")))
			{
				String s;
				s = appDefault;
				temp = "";
				int i=0;
				for (char c:s.toCharArray()){
					if (i==0)
					{
						if (c=='u')
							temp += "1";
						else temp += "2";
					}
					else if (i>3)
					{
						if (i==6)
						{ if (Character.isDigit(c))
							temp += c;
						  else break;
						}
						else
							temp += c;
					}
					i++;
					if (i==7) break;
					
				}
				appDefault  = temp;
			}
			else   appDefault = "0";
			
			// appEvasive
			appEvasive = attribute.get("app:evasive");
			if (appEvasive.equals("")) 						appEvasive = "0";
			else if (appEvasive.equals("no"))				appEvasive = "1";
			else appEvasive = "2";  //yes
			
			//app excessive bandwidth
			appEvaBand = attribute.get("app:excessive_bandwidth");
			if (appEvaBand.equals("")) 						appEvaBand = "0";
			else if (appEvaBand.equals("no"))				appEvaBand = "1";
			else appEvaBand = "2";  //yes
			
			//app has known vulnerability
			appKnown = attribute.get("app:has_known_vulnerability");
			if (appKnown.equals("")) 						appKnown = "0";
			else if (appKnown.equals("no"))				appKnown = "1";
			else appKnown = "2";  //yes
			
			//risk score
			appRisk = attribute.get("app:risk");
			
			//app subcategory
			appSub = attribute.get("app:subcategory");
			if (appSub.equals("software-update")) 						appSub = "0";
			else if (appSub.equals("infrastructure"))					appSub = "1";
			else if (appSub.equals("software-update"))					appSub = "2";
			else if (appSub.equals("encrypted-tunnel"))					appSub = "3";
			else if (appSub.equals("internet-utility"))					appSub = "4";
			else appSub = "5";  //yes
			
			//app used by malware
			appUsed = attribute.get("app:used_by_malware");
			if (appUsed.equals("")) 						appUsed = "0";
			else if (appUsed.equals("no"))					appUsed = "1";
			else appUsed = "2";  //yes
			
			
			//Application name
			Application = attribute.get("application");
			if (Application.equals("netbios-ns")) 						Application = "0";
			else if (Application.equals("ms-update"))					Application = "1";
			else if (Application.equals("google-cloud-storage"))		Application = "2";
			else if (Application.equals("web-browsing"))				Application = "3";
			else if (Application.equals("ssl"))							Application = "4";
			else Application = "5";  //yes
			
			//incoming packet size
			bytesIn = attribute.get("bytes_in");
			if (bytesIn.equals("")) 						bytesIn = "0";
			
			//outgoing packet size
			bytesOut = attribute.get("bytes_out");
			if (bytesOut.equals("")) 						bytesOut = "0";
			
			//destination port
			dstPort = attribute.get("dest_port");
			
			//protocol
			protocol = attribute.get("protocol");
			if (protocol.equals("")) 							protocol = "0";
			else if (protocol.equals("udp")) 					protocol = "1";
			else protocol = "2";
			
			//source IP address
			srcIP = attribute.get("src_ip");
			srcIP = parse_IP(srcIP);
			
			//threatCategory
			threatCat = attribute.get("threat:category");
			if (threatCat.equals("")) 							threatCat = "0";
			
			//threatCVE
			threatCVE = attribute.get("threat:cve");
			if (threatCVE.equals("")) 							threatCVE = "0";
			else threatCVE = "1";
			
			//threat Severity
			threatSev = attribute.get("threat:severity");
			if (threatSev.equals("")) 							threatSev = "0";
			else if (threatSev.equals("Low")) 					threatSev = "1";
			else if (threatSev.equals("Medium")) 				threatSev = "2";
			else threatSev = "4";
			
			//threat ID
			threatID = attribute.get("threat_id");
			if (threatID.equals("")) 							threatID = "0";
			else threatID = "1";
			
			//threat Name
			threatName = attribute.get("threat_name");
			if (threatName.equals("")) 							threatName = "0";
			else threatName = "1";
			
			//transport protocol
			transport = attribute.get("transport");
			if (transport.equals("")) 							transport = "0";
			else if (transport.equals("udp")) 					transport = "1";
			else transport = "2";
			
			//Traffic type
			type = attribute.get("type");
			if (type.equals("")) 							type = "0";
			else if (type.equals("TRAFFIC")) 				type = "1";
			else type = "2";
		
			//url 
			URL = attribute.get("url");
			if (URL.equals("")) 							URL = "0";
			else URL = "1";
			
			//DriveType
			DriveType = attribute.get("DriveType");
			if (DriveType.equals("")) 							DriveType = "0";
			else DriveType = "1";
			
			//File System
			FSystem = attribute.get("FileSystem");
			if (FSystem.equals("")) 							FSystem = "0";
			else FSystem = "1";

			//Free Space
			FreeSpace = attribute.get("FreeSpaceKB");
			
			fSpace = Integer.parseInt(FreeSpace);
			if (fSpace == 0) fSpace = 920000000 + (int)(Math.random()* 1000000 + 1);
			FreeSpace = Integer.toString(fSpace);
			
			//Drive Name
			DriveName = attribute.get("Name");
			if (DriveName.equals("")) 							DriveName = "1";
			else DriveName = "0";
			
			//Total Space in Drive
			TotalSpace = attribute.get("TotalSpaceKB");
			
			tSpace = Integer.parseInt(TotalSpace);
			if (tSpace == 0) tSpace = 976743932;
			TotalSpace = Integer.toString(tSpace);
			
			//Total Space USed
			DiffSpace = Integer.toString(tSpace-fSpace);
			
			//EventCode
			EventCode = attribute.get("EventCode");
			if (EventCode.equals("")) 							EventCode = "0";
			
			//Logon ID
			Logon_ID = attribute.get("Logon_ID");
			if (Logon_ID.equals("")) 							Logon_ID = "0";
			else Logon_ID = "1";

			//User type
			User = attribute.get("User");
			if (User.equals("")) 							User = "0";
			if (User.equals("System")) 						User = "1";
			else User = "2";

			//Time of Event
			Event_time = attribute.get("Event_time");
			if (!(Event_time.equals("")))
			{
				Event_time = parse_time(Event_time);
				/**
				String s;
				s = Event_time;
				temp = "";
				int i=0;
				for (char c:s.toCharArray()){
					if ((i > 10) && (i <13))
						temp += c;
					else if ((i> 13) && (i <16))
						temp += c;
					else if ((i > 16) && (i < 19))
						temp += c;
					i++;
					if (i >19) break;
				}
				Event_time  = temp;
				***/
			}
			else   Event_time = "0";
			
			//Network Transfer Rate
			Network_transfer = attribute.get("Network_transfer");
			if (Network_transfer.equals(""))		Network_transfer="0";
			else
				Network_transfer = Integer.toString((int) (Double.parseDouble(Network_transfer)));
			
			//Network Time
			Network_time = attribute.get("Network_time");
			if (!(Network_time.equals("")))
			{
				Network_time = parse_time(Network_time);
				/***
				String s;
				s = Network_time;
				temp = "";
				int i=0;
				for (char c:s.toCharArray()){
					if ((i > 10) && (i <13))
						temp += c;
					else if ((i> 13) && (i <16))
						temp += c;
					else if ((i > 16) && (i < 19))
						temp += c;
					i++;
					if (i >19) break;
				}
				Network_time  = temp;
				***/
			}
			else   Network_time = "0";
			
			//traffic type
			Traffic_type = attribute.get("Traffic_type");
			if (Traffic_type.equals("")) 							Traffic_type = "0";
			else Traffic_type = "1";

			//registry trabnsaction time
			Reg_time = attribute.get("Reg_time");
			if (!(Reg_time.equals("")))
			{
				Reg_time = parse_time(Reg_time);
				/***
				String s;
				s = Reg_time;
				temp = "";
				int i=0;
				for (char c:s.toCharArray()){
					if ((i > 10) && (i <13))
						temp += c;
					else if ((i> 13) && (i <16))
						temp += c;
					else if ((i > 16) && (i < 19))
						temp += c;
					i++;
					if (i >19) break;
				}
				Reg_time  = temp;
				***/
			}
			else   Reg_time = "0";
			
			//Regsitry data
			Rdata = attribute.get("Rdata");
			if (Rdata.equals("")) 							Rdata = "0";
			
			//Registry Evenet Status
			Revent_status = attribute.get("Revent_status");
			if (!(Revent_status.equals("")))
			{
				String s;
				s = Revent_status;
				temp = "";
				int i=0;
				for (char c:s.toCharArray()){
					if (i == 1)
						temp += c;
					i++;
					if (i >1) break;
				}
				Revent_status = temp;
			}
			else   Revent_status = "0";
			
			//Key path
			key_path = attribute.get("key_path");
			if (key_path.equals("")) 						key_path = "0";
			else key_path = "1";
						
			//Registry process ID
			Rpid = attribute.get("Rpid");
			if (Rpid.equals("")) 							Rpid = "0";
			
			//Registry process image
			Rprocess_image = attribute.get("Rprocess_image");
			if (Rprocess_image.equals("")) 					Rprocess_image = "0";
			else Rprocess_image = "1";
				
			//registry type
			registry_type = attribute.get("registry_type");
			if (registry_type.equals("")) 					registry_type = "0";
			else if (registry_type.equals("CreateKey"))		registry_type = "1";
			else registry_type = "2";
			
			//Symantec Endpoint Protection (SEP) time
			SEP_time = attribute.get("SEP_time");
			if (!(SEP_time.equals("")))
			{
				SEP_time = parse_time(SEP_time);
				/**
				String s;
				s = SEP_time;
				temp = "";
				int i=0;
				for (char c:s.toCharArray()){
					if ((i > 10) && (i <13))
						temp += c;
					else if ((i> 13) && (i <16))
						temp += c;
					else if ((i > 16) && (i < 19))
						temp += c;
					i++;
					if (i >19) break;
				}
				SEP_time  = temp;
				***/
			}
			else   SEP_time = "0";
			
			//SEP action
			SEP_action = attribute.get("SEP_action");
			if (SEP_action.equals("") || SEP_action.equals("Allowed")) 			SEP_action="0";
			else SEP_action = "1";		

			//SEP application
			SEP_app = attribute.get("SEP_app");	
			if (SEP_app.equals("")) 			SEP_app = "0";
			else SEP_app = "1";		
			
			//SEP traffic direction
			direction = attribute.get("direction");
			if (direction.equals("")) 					direction = "0";
			else if (direction.equals("Incoming"))		direction = "1";
			else	direction = "2";	
			
			//SEP local port used
			SEP_local_port = attribute.get("SEP_local_port");		
			if (SEP_local_port.equals("")) 				SEP_local_port = "0";
			
			// SEP Packet ID			
			SEP_packet_id = attribute.get("SEP_packet_id");	
			
			//SEP Remote Host IP Number
			SEP_remote_host = attribute.get("SEP_remote_host");	
			SEP_remote_host = parse_IP(SEP_remote_host);
			
			//SEP Tag
			SEP_tag = attribute.get("SEP_tag");
			if (SEP_tag.equals("")) 			SEP_tag = "0";
			else SEP_tag = "1";		
			
			//Service Description
			Serv_Desc = attribute.get("Serv_Desc");	
			if (Serv_Desc.equals("")) 			Serv_Desc = "0";
			else Serv_Desc = "1";		
			
			//Display Name
			DisplayName = attribute.get("DiaplayName");
			if (DisplayName.equals("")) 			DisplayName = "0";
			else DisplayName = "1";		
			
			//Service Name
			Serv_Name = attribute.get("Serv_Name");	
			if (Serv_Name.equals("")) 			Serv_Name = "0";
			else Serv_Name = "1";	
			
			//Start Mode
			StartMode = attribute.get("StartMode");		
			if (StartMode.equals("")) 				StartMode = "0";
			else if (StartMode.equals("Manual")) 	StartMode = "1";
			else if (StartMode.equals("Auto"))		StartMode = "2";	
			else 									StartMode = "3";	
			
			//Service Status
			Serv_Started = attribute.get("Serv_Started");	
			if (Serv_Started.equals("")) 				Serv_Started = "0";
			else if (Serv_Started.equals("FALSE")) 		Serv_Started = "1";
			else 										Serv_Started = "2";	
			
			//service time
			Serv_time = attribute.get("Serv_time");	
			Serv_time = parse_time(Serv_time);
			
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

	static CsvReader attribute, SeededAttribute, NoSeedAttribute;
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
