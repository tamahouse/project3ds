package automation.project3ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.codoid.products.fillo.Recordset;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class MyTunnel {

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
		table.put("old_z2", "getSession_SSHTunnel_Username", "chase");
		table.put("old_z2", "getSession_SSHTunnel_Host", "50.23.7.82");
		table.put("old_z2", "getSession_SSHTunnel_Port", "53714");
		table.put("old_z2", "setPortForwardingL_Settings_Host", "127.0.0.1");
		table.put("old_z2", "setPortForwardingL_Settings_RightPort", "3306");
		table.put("spiderpipe", "db_Username", "chase");
		table.put("spiderpipe", "db_Password", "hnu5EfFjLN19jC2oYFvF");
		table.put("p2", "db_Username", "chase");
		table.put("p2", "db_Password", "hnu5EfFjLN19jC2oYFvF");
		table.put("wallapi", "db_Username", "chase");
		table.put("wallapi", "db_Password", "hnu5EfFjLN19jC2oYFvF");
		table.put("z2", "db_Username", "chase");
		table.put("z2", "db_Password", "hnu5EfFjLN19jC2oYFvF");
		table.put("ccgateway", "db_Username", "chase");
		table.put("ccgateway", "db_Password", "");
		table.put("paymentwall_umi", "db_Username", "chase");
		table.put("paymentwall_umi", "db_Password", "hnu5EfFjLN19jC2oYFvF");
	}
	
	public MyTunnel(String sessionName) throws Exception {
		this.sessionName = sessionName;
		this.setSession();
	}
	
	public Boolean isWorking() {
		if(session == null || session.isConnected() == false) {
			return false;
		}else {
			return true;
		}
	}

	public Session setSession() throws Exception {
			String getSession_SSHTunnel_Username = table.get(sessionName, "getSession_SSHTunnel_Username");
			String getSession_SSHTunnel_Host = table.get(sessionName, "getSession_SSHTunnel_Host");
			String getSession_SSHTunnel_Port_string = table.get(sessionName, "getSession_SSHTunnel_Port");
			int getSession_SSHTunnel_Port = Integer.parseInt(getSession_SSHTunnel_Port_string);
			String setPortForwardingL_Settings_Host = table.get(sessionName, "setPortForwardingL_Settings_Host");
			String setPortForwardingL_Settings_RightPort_string = table.get(sessionName,
					"setPortForwardingL_Settings_RightPort");
			int setPortForwardingL_Settings_RightPort = Integer.parseInt(setPortForwardingL_Settings_RightPort_string);
			JSch jsch = new JSch();
			jsch.addIdentity(filePath);
			session = jsch.getSession(getSession_SSHTunnel_Username, getSession_SSHTunnel_Host,
					getSession_SSHTunnel_Port);
			session.setConfig(properties);
			Boolean x = false;
			while (x == false && setPortForwardingL_SSHTunnel_LeftLocalPort < 3310) {
				try {
					session.setPortForwardingL(setPortForwardingL_SSHTunnel_LeftLocalPort,
							setPortForwardingL_Settings_Host, setPortForwardingL_Settings_RightPort);
					session.connect();
					session.isConnected();
					x = true;
				} catch (JSchException e) {
					setPortForwardingL_SSHTunnel_LeftLocalPort++;
				}
			}

		return session;
	}

	public Statement getStatement(String dbName) throws Exception {
		String[] ports = session.getPortForwardingL();
		String port = ports[0].substring(0, ports[0].indexOf(":"));
		String db_Username = table.get(dbName, "db_Username");
		String db_Password = table.get(dbName, "db_Password");
		Connection connection = null;

		if (db_Password.equals("")) {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:" + port + "/" + dbName + "?" + "user="
					+ db_Username + "" + "&serverTimezone=UTC");
		} else {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:" + port + "/" + dbName + "?" + "user="
					+ db_Username + "&password=" + db_Password + "" + "&serverTimezone=UTC");
		}
		Statement statement = connection.createStatement();
		return statement;
	}
	
	public static List<Map<String, String>> getMap(Recordset record) throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<String> titles = record.getFieldNames();
		while (record.next()) {
			Map<String, String> map = new HashMap<String, String>();
			for (String key : titles) {
				String value = record.getField(key);
				map.put(key, value);
			}
			list.add(map);
		}
		return list;
	}
	

}
