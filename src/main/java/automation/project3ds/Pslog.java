package automation.project3ds;

import java.sql.ResultSet;
import java.sql.Statement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pslog {
	
	static MyTunnel tunnel;
	public static String pagseguro_id;

	private static Statement getStatement() throws Exception {
		if(tunnel == null || tunnel.isWorking() == false) {
			tunnel = new MyTunnel("wallapi");
		}
		Statement statement = tunnel.getStatement("paymentwall_umi");
		return statement;
	}
	
	public static String get_cl_id(String t_id) throws Exception {
		String query = "select * from ps_logs where log_data like '%"+t_id+"%' and log_data like '%check3DSecure%'";
		ResultSet resultSet = getStatement().executeQuery(query);
		while(resultSet.next()) {
			ObjectMapper mapper = new ObjectMapper();
			String log_data = resultSet.getString("log_data");
			JsonNode logNode = mapper.readTree(log_data);
			String id = null;
			 id = logNode.path("response").path("charge").path("id").asText();
			if(id.equals("")) {
			 id = logNode.path("response").path("id").asText();
			}
			if(id.equals(t_id)) {
				return resultSet.getString("cl_id");
			}
		}
		return null;
	}
	
	public static String get_cl_id_email(String email) throws Exception {
		String query = "select * from ps_logs where log_data like '%"+email+"%' and log_data like '%check3DSecure%'";
		ResultSet resultSet = getStatement().executeQuery(query);
		resultSet.next();
		String cl_id = resultSet.getString("cl_id");
		return cl_id;
	}

	public static String getTID(String clickId) throws Exception {
		ResultSet resultSet = getStatement().executeQuery("select log_data from ps_logs where cl_id = '" + clickId
				+ "' and log_data like '%check3DSecure%' limit 1");
		resultSet.next();
		String result = resultSet.getString("log_data");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(result);
		String t_id;
		try {
			t_id = jsonNode.path("response").path("charge").get("id").toString();
		} catch (Exception e) {
			t_id = jsonNode.path("response").get("id").toString();
		}
		t_id = t_id.substring(1, t_id.length() - 1);
		return t_id;
	}
	
	public static String get_cl_id_email_Fasterpay(String email) throws Exception {
		System.out.println(email);
		String query = "select * from ps_logs where log_data like '%"+email+"%' order by cl_id desc limit 1";
		ResultSet resultSet = getStatement().executeQuery(query);
		resultSet.next();
		String cl_id = resultSet.getString("cl_id");
		try {
		String log_data = resultSet.getString("log_data");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(log_data);
		pagseguro_id = jsonNode.path("instructions").path("form_params").path("code").asText();
		}catch(Exception e) {
			
		}
		System.out.println("http://feature-pwg-1166.wallapi.bamboo.stuffio.com/admin/reports/payment-transaction/?search[cl_id]="+cl_id);
		ExtentManager.logInfo(cl_id);
		return cl_id;
	}
	
	public static String get_cl_id(int ps_id) throws Exception {
		String query = "select * from ps_logs where ps_id ="+ps_id+" order by cl_id desc limit 1";
		ResultSet resultSet = getStatement().executeQuery(query);
		resultSet.next();
		String cl_id = resultSet.getString("cl_id");
		System.out.println(cl_id);
		ExtentManager.logInfo(cl_id);
		return cl_id;
	}
	
	public static String get_Fasterpay_payment_order_id(String clickId) throws Exception {
		ResultSet resultSet = getStatement().executeQuery("select log_data from ps_logs where cl_id = '" + clickId
				+ "' and log_data like '%createPaymentOrder%' limit 5");
		resultSet.next();
		String result = resultSet.getString("log_data");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(result);
		String f_id = jsonNode.path("response").path("data").get("id").asText();
		return f_id;
	}

	

}