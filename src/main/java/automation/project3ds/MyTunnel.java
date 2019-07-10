package automation.project3ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class MyTunnel{

	String dbName;
	String sessionName;
	Session session;
	static String filePath = utility.ConfigFile.openKeyPath;
	static Properties properties = new java.util.Properties();
	static {
		properties.put("StrictHostKeyChecking", "no");
		properties.put("connectTimeout", "2000");
	}
	static int setPortForwardingL_SSHTunnel_LeftLocalPort = 3301;
	static Table<String, String, String> table = HashBasedTable.create();
	static {
		table.put("sp", "getSession_SSHTunnel_Username", "chase");
		table.put("sp", "getSession_SSHTunnel_Host", "159.8.51.234");
		table.put("sp", "getSession_SSHTunnel_Port", "53714");
		table.put("sp", "setPortForwardingL_Settings_Host", "10.70.27.104");
		table.put("sp", "setPortForwardingL_Settings_RightPort", "3308");
		table.put("p2", "getSession_SSHTunnel_Username", "chase");
		table.put("p2", "getSession_SSHTunnel_Host", "5.153.7.245");
		table.put("p2", "getSession_SSHTunnel_Port", "53714");
		table.put("p2", "setPortForwardingL_Settings_Host", "127.0.0.1");
		table.put("p2", "setPortForwardingL_Settings_RightPort", "3306");
		table.put("wallapi", "getSession_SSHTunnel_Username", "chase");
		table.put("wallapi", "getSession_SSHTunnel_Host", "159.8.51.234");
		table.put("wallapi", "getSession_SSHTunnel_Port", "53714");
		table.put("wallapi", "setPortForwardingL_Settings_Host", "127.0.0.1");
		table.put("wallapi", "setPortForwardingL_Settings_RightPort", "3306");
//		table.put("z2", "getSession_SSHTunnel_Username", "chase");
//		table.put("z2", "getSession_SSHTunnel_Host", "159.8.51.234");
//		table.put("z2", "getSession_SSHTunnel_Port", "53714");
//		table.put("z2", "setPortForwardingL_Settings_Host", "127.0.0.1");
//		table.put("z2", "setPortForwardingL_Settings_RightPort", "3306");
//		table.put("pslogs", "getSession_SSHTunnel_Username", "chase");
//		table.put("pslogs", "getSession_SSHTunnel_Host", "159.8.51.234");
//		table.put("pslogs", "getSession_SSHTunnel_Port", "53714");
//		table.put("pslogs", "setPortForwardingL_Settings_Host", "127.0.0.1");
//		table.put("pslogs", "setPortForwardingL_Settings_RightPort", "3306");
		table.put("spiderpipe", "db_Username", "chase");
		table.put("spiderpipe", "db_Password", "hnu5EfFjLN19jC2oYFvF");
		table.put("p2", "db_Username", "chase");
		table.put("p2", "db_Password", "hnu5EfFjLN19jC2oYFvF");
		table.put("wallapi", "db_Username", "chase");
		table.put("wallapi", "db_Password", "hnu5EfFjLN19jC2oYFvF");
		table.put("z2", "db_Username", "chase");
		table.put("z2", "db_Password", "hnu5EfFjLN19jC2oYFvF");
		table.put("paymentwall_umi", "db_Username", "chase");
		table.put("paymentwall_umi", "db_Password", "hnu5EfFjLN19jC2oYFvF");
	}
	
	public MyTunnel (String sessionName) throws Exception {
		this.sessionName = sessionName;
	}
	
	public void createTunnel() throws Exception {
		if(session == null || !session.isConnected()) {
			try {
		System.out.print("creating tunnel connection ....");
			String getSession_SSHTunnel_Username = table.get(sessionName, "getSession_SSHTunnel_Username");
			String getSession_SSHTunnel_Host = table.get(sessionName, "getSession_SSHTunnel_Host");
			String getSession_SSHTunnel_Port_string = table.get(sessionName, "getSession_SSHTunnel_Port");
			int getSession_SSHTunnel_Port = Integer.parseInt(getSession_SSHTunnel_Port_string);
			String setPortForwardingL_Settings_Host = table.get(sessionName, "setPortForwardingL_Settings_Host");
			String setPortForwardingL_Settings_RightPort_string = table.get(sessionName, "setPortForwardingL_Settings_RightPort");
			int setPortForwardingL_Settings_RightPort = Integer.parseInt(setPortForwardingL_Settings_RightPort_string);
			JSch jsch = new JSch();
			jsch.addIdentity(filePath);
			session = jsch.getSession(getSession_SSHTunnel_Username, getSession_SSHTunnel_Host, getSession_SSHTunnel_Port);
			session.setConfig(properties);
			session.setPortForwardingL(setPortForwardingL_SSHTunnel_LeftLocalPort, setPortForwardingL_Settings_Host, setPortForwardingL_Settings_RightPort);
			session.connect();
		System.out.println(" successful !!!!");
			}catch (JSchException e) {
				System.out.println(" created !!!!");
			}
		}
	}
	
	public Statement getStatement(String dbName) throws Exception {
		this.createTunnel();
		String db_Username = table.get(dbName, "db_Username");
		String db_Password = table.get(dbName, "db_Password");
		Boolean x = false;
		Connection connection = null;
		while (setPortForwardingL_SSHTunnel_LeftLocalPort <3310 && x == false) {
		try {
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:"+setPortForwardingL_SSHTunnel_LeftLocalPort+"/"+dbName+"?"
					+ "user="+db_Username+"&password="+db_Password+"" + "&serverTimezone=UTC");
		System.out.println("setPortForwardingL_SSHTunnel_LeftLocalPort : " +setPortForwardingL_SSHTunnel_LeftLocalPort);
		x = true;
		}catch(Exception e) {
			setPortForwardingL_SSHTunnel_LeftLocalPort++;
		}
		}
		Statement statement = connection.createStatement();
		return statement;
	}
	
	
	
//	public void setConnection() throws Exception {
//		Boolean x = false;
//		int count = 0;
//		Connection connection = null;
//		while (x == false) {
//			try {
//				if (count != 0) {
//					System.out.print("connecting to tunnel ....");
//				}
//				connection = this.connect();
//				x = connection.isValid(2);
//				if (count != 0) {
//					System.out.println(" successful !!!!");
//				}
//			} catch (Exception e) {
//				if (count != 0) {
//					System.out.println(" failed !!!!");
//				}
//
//				this.createTunnel();
//				if (++count > 10) {
//					throw e;
//				}
//			}
//
//		}
//
//		this.connection = connection;
//		
//	}
//
//	private Connection getConnection() throws Exception {
//		try {
//			this.connection.close();
//		}catch(Exception e) {
//			
//		}
//		try {
//			this.session.disconnect();
//		}catch(Exception e) {
//			
//		}
//		try {
//		this.createTunnel();
//		}catch(Exception e) {
//			
//		}
//		return this.connect();
////		if (connection == null) {
////			this.setConnection();
////		} else if (connection.isValid(2) == false) {
////			this.setConnection();
////		}
////		return connection;
//	}
//	
//
//
//
//
//
//	public void close() throws Exception {
//		this.connection.close();
//		this.session.disconnect();
//
//	}
//
//	public ResultSet query(String strQuery) throws Exception {
//		ResultSet result = this.queryNotNext(strQuery);
//		result.first();
//		return result;
//	}
//
//	public ResultSet queryNotNext(String strQuery) throws Exception {
//		Statement statement = this.getConnection().createStatement();
//		statement.executeQuery(strQuery);
//		ResultSet result = statement.getResultSet();
////		CachedRowSetImpl crs = new CachedRowSetImpl();
////		crs.populate(result);
//		return result;
//	}

	

}
