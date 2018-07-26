import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class SelectFrom {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@(DESCRIPTION= (ADDRESS_LIST= (ADDRESS= (PROTOCOL=TCP) (HOST=sn30040-vip) (PORT=41521) ) (ADDRESS= (PROTOCOL=TCP) (HOST=sn30041-vip) (PORT=41521) ) (ADDRESS= (PROTOCOL=TCP) (HOST=sn30042-vip) (PORT=41521) ) ) (CONNECT_DATA= (FAILOVER_MODE= (TYPE=select) (METHOD=basic) (RETRIES=180) (DELAY=5) ) (SERVER=dedicated) (SERVICE_NAME=GDSDV) ) )";
	private static final String DB_USER = "EMDASHBOARD";
	private static final String DB_PASSWORD = "EMDASHBOARD";


	private static final String [] queries = {


"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL,	'btlp000052',	'QIDC',	'EM-CAD(BC)',	'DRN',	'DRCustMgtFulWRNQSvc;;AdminServer'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL,	'btlp000052',	'QIDC',	'EM-CAD(BC)',	'DRN',	'DRCustMgtFulWRNQSvc;;AdminServer;DRCustMgtFulWRNQSvc;DVSal;DVSalSvr1'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL,	'btlp000052',	'QIDC',	'EM-CAD(BC)',	'DRN',	'DRCustMgtFulWRNQSvc;;AdminServer;DRCustMgtFulWRNQSvc;DVSal;DVSalSvr1;DRCustMgtFulWRNQSvc;N/A;adminUrl'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL,	'btlp000052',	'QIDC',	'EM-CAD(BC)',	'DRN',	'DRCustMgtFulWRNQSvc;;AdminServer;DRCustMgtFulWRNQSvc;DVSal;DVSalSvr1;DRCustMgtFulWRNQSvc;N/A;adminUrl;DRNCustMgtSvcQ;;AdminServer'	)"



			};

	private static final String selectQuery = "select vms.VM_NAME, vms.VM_IDC, vms.VM_Env, vms.VM_OWNER_TEAM, vms.VM_APP from ALL_EM_VIRTUAL_MACHINES vms";

	private static PrintWriter file ;

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	public static void main(String[] argv) {

		try {

			//insertRecordIntoDbUserTable();
			openFile();
			selectFromTable();
			closeFile();
		}
		catch (Exception e1) {
					System.out.println(e1.getMessage());

		}

	}

	private static void selectFromTable() throws SQLException{

		Connection dbConnection = null;
		Statement statement = null;



		try {
					dbConnection = getDBConnection();
					statement = dbConnection.createStatement();

					ResultSet rs = statement.executeQuery(selectQuery);

					writeToFile(rs);

			}catch (SQLException e) {

				System.out.println(e.getMessage());

			} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
			if(file !=null) file.close();
		}
	}

	private static void writeToFile(ResultSet rs) throws SQLException{

		int total_count=0;
		//STEP 5: Extract data from result set
      		while(rs.next()){
         //Retrieve by column name
         String vmName  = rs.getString("VM_NAME");
         String vmIDC  = rs.getString("VM_IDC");
         String vmEnv  = rs.getString("VM_ENV");
         String vmOwner  = rs.getString("VM_OWNER_TEAM");
         String vmApps  = rs.getString("VM_APP");

         //Display values
         System.out.print("VM_NAME: " + vmName);
         System.out.print(", VM_IDC: " + vmIDC);
         System.out.print(", VM_ENV: " + vmEnv);
         System.out.print(", VM_OWNER_TEAM: " + vmOwner);
         System.out.println(", VM_APP: " + vmApps);

		file.println("<tr>"+"<td>"+vmName+"</td>"+"<td>"+vmIDC+"</td>"+"<td>"+vmEnv+"</td>"+"<td>"+vmOwner+"</td>"+"<td>"+vmApps+"</td>"+"</tr>");
		total_count++;
      }
      System.out.println("Total count written to file :" + total_count);
	}

public static void openFile(){

	try{
		file = new PrintWriter("Results_"+System.currentTimeMillis()+".html","UTF-8");
	}catch (Exception e){
		System.out.println(e.getMessage());
		System.exit(0);
	}

	file.println("<table border='1'>\n");
	file.println("<tr><th>VM Name</th><th>IDC</th><th>Env</th><th>Owner Team</th><th>Applications</th></tr>");
}

public static void closeFile(){


	file.println("</table>");
	file.close();
}


	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                               DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	private static String getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());

	}



}