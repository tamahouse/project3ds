package automation.project3ds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FasterPay {
	
	static MyTunnel tunnel;

	private static Statement getStatement() throws Exception {
		if(tunnel == null || tunnel.isWorking() == false) {
			tunnel = new MyTunnel("wallapi");
		}
		Statement statement = tunnel.getStatement("fp-pay-laravel");
		return statement;
	}
	

	public static String getTID_Fasterpay(String f_id) throws Exception {
		ResultSet resultSet = getStatement().executeQuery("SELECT * FROM logs WHERE payment_order_id = '"+f_id+"' AND logs.context LIKE '%id%' AND message LIKE '%RESPONSE%'");
		resultSet.next();
		String result = resultSet.getString("context");
		String t_id ;
		try {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(result);
		t_id = jsonNode.get("id").asText();
		}catch(Exception e) {
			t_id = result.substring(result.indexOf("id")+7,result.indexOf("\\", result.indexOf("id")+7 ));
		}
		return t_id;
	}
	

}