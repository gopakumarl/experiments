import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class InsertInto {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@(DESCRIPTION= (ADDRESS_LIST= (ADDRESS= (PROTOCOL=TCP) (HOST=sn30040-vip) (PORT=41521) ) (ADDRESS= (PROTOCOL=TCP) (HOST=sn30041-vip) (PORT=41521) ) (ADDRESS= (PROTOCOL=TCP) (HOST=sn30042-vip) (PORT=41521) ) ) (CONNECT_DATA= (FAILOVER_MODE= (TYPE=select) (METHOD=basic) (RETRIES=180) (DELAY=5) ) (SERVER=dedicated) (SERVICE_NAME=GDSDV) ) )";
	private static final String DB_USER = "EMDASHBOARD";
	private static final String DB_PASSWORD = "EMDASHBOARD";


	private static final String [] queries = {


"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002497',	'KIDC',	'EM-CAD-ON',	'DV103',	'DV103IRISAutographIntQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002629',	'KIDC',	'EM-CAD-ON',	'ITS01',	'ITS01EntCDEPaymentCardExtWebQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002630',	'KIDC',	'EM-CAD-ON',	'DV101',	'DV101EntCDEPaymentCardExtWebQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002631',	'KIDC',	'EM-CAD-ON',	'ITS01',	'ITS01EntCDEPaymentCardExtWebQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002632',	'KIDC',	'EM-CAD-ON',	'ITS01',	'ITS01EntCDEPaymentCardIntWebQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002633',	'KIDC',	'EM-CAD-ON',	'ITS02',	'ITS02EntCDEPaymentCardExtWebQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002634',	'KIDC',	'EM-CAD-ON',	'ITS02',	'ITS02EntCDEPaymentCardExtWebQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002635',	'KIDC',	'EM-CAD-ON',	'ITS03',	'ITS03EntCDEPaymentCardExtWebQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln002636',	'KIDC',	'EM-CAD-ON',	'ITS03',	'ITS03EntCDEPaymentCardExtWebQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln007100',	'KIDC',	'EM-CAD-ON',	'ITS04',	'ITS04ACustomerManagementFulfillmentWLSQSvc'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln007101',	'KIDC',	'EM-CAD-ON',	'ITS04',	'ITS04ACustomerManagementFulfillmentWLSQSvc'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln007102',	'KIDC',	'EM-CAD-ON',	'ITS04',	'ITS04BCustomerManagementBilling12SvcQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln007103',	'KIDC',	'EM-CAD-ON',	'ITS04',	'ITS04BCustomerManagementBilling12SvcQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln007128',	'KIDC',	'EM-CAD-ON',	'ITS04',	'ITS04DigitalSignatureIntQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln007129',	'KIDC',	'EM-CAD-ON',	'ITS04',	'ITS04DigitalSignatureIntQ'	)",
"insert into ALL_EM_VIRTUAL_MACHINES (VM_ID,VM_NAME, VM_IDC, VM_OWNER_TEAM, VM_ENV, VM_APP) values (VM_ALL_SEQ.NEXTVAL, 'btln007130',	'KIDC',	'EM-CAD-ON',	'ITS01',	'ITS01DigitalSignatureIntQ'	)"





			};

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	public static void main(String[] argv) {

		try {

			insertRecordIntoDbUserTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

	}

	private static void insertRecordIntoDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		int total_count =0;
		int success_count=0;

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();




			for (String query : queries) {
				System.out.println("Executing query: "+query);
				try{
					total_count++;
			    	statement.execute(query);
			    	success_count++;
			    	System.out.println("Record insterted successfully! ");
				}catch (SQLException e) {
					System.out.println("Query failed: ");
					System.out.println(e.getMessage());
					System.out.println("Continuing with next.. ");
				}
			}

			// execute insert SQL stetement
			//statement.executeUpdate(insertTableSQL);

			dbConnection.commit();
			System.out.println("Total "+success_count+" records inserted into table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

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

	private static String getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());

	}



}