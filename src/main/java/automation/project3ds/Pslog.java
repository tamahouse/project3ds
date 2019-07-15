package automation.project3ds;

import java.sql.ResultSet;
import java.sql.Statement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Pslog {

	private static Statement getStatement() throws Exception {
		MyTunnel tunnel = new MyTunnel("wallapi");
		Statement statement = tunnel.getStatement("paymentwall_umi");
		return statement;
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

}