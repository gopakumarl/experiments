import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class SelectFromCursorTest {

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
	private static final String selectQuery1 = "select vms.VM_NAME from ALL_EM_VIRTUAL_MACHINES vms where vms.VM_IDC=?";

	private PreparedStatement sql1 = null;
    private PreparedStatement sql2 = null;

	private static PrintWriter file ;

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	public static void main(String[] argv) throws Exception{

Connection dbConnection = null;
		Statement statement = null;

		try{

			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

					ResultSet rs1 = statement.executeQuery(selectQuery);

					while (rs1.next()) {
					   String vmName = rs1.getString("VM_NAME");
					   PreparedStatement st2=null;
					   st2 = dbConnection.prepareStatement(selectQuery1);
					   st2.setString(1,vmName);
					   ResultSet rs2 = st2.executeQuery();
						if(rs2.next()){
							System.out.println("got result:" + rs2.getString("VM_NAME") );
						}
						rs2.close();
					}
		}
		catch(Exception e){
			e.printStackTrace();
		}	finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}

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

}