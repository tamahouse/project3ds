package automation.project3ds;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Wallapi {
	
	static MyTunnel tunnel;
	static Map<String, List<Map<String, String>>> countryByPs = new HashMap<String, List<Map<String, String>>>();

	private static Statement getStatement() throws Exception {
		if(tunnel == null || tunnel.isWorking() == false) {
			tunnel = new MyTunnel("wallapi");
		}
		Statement statement = tunnel.getStatement("wallapi");
		return statement;
	}
	
	public static List<Map<String, String>> getCountryByPSID(String project_id) throws Exception {
		List<Map<String, String>> list = countryByPs.get(project_id);
		if(list == null) {
			list = new ArrayList<Map<String, String>>();
			String query = "SELECT applications_ps.ps_id, payment_systems.ps_name, countries.co_id, countries.co_name FROM applications_ps" + 
					" LEFT JOIN applications_ps_countries ON applications_ps.aps_id = applications_ps_countries.aps_id" + 
					" LEFT JOIN countries ON applications_ps_countries.co_id = countries.co_id" + 
					" LEFT JOIN payment_systems ON applications_ps.ps_id = payment_systems.ps_id" + 
					" where a_id ="+ project_id + 
					" and co_active = 1 ORDER BY ps_id";
		ResultSet resultSet = getStatement().executeQuery(query);
		ResultSetMetaData metaData = resultSet.getMetaData();
		List<String> columnNames = new ArrayList<String>();
		for(int i = 1; i <= metaData.getColumnCount(); i++) {
			String columnName = metaData.getColumnLabel(i);
			columnNames.add(columnName);
		}
		while(resultSet.next()) {
			Map<String, String> map = new HashMap<String, String>();
			for(String columnName: columnNames) {
				String value = resultSet.getString(columnName);
				map.put(columnName, value);
			}
			list.add(map);
		}
	}
		return list;
	}
	
	public static Map<String, String> getPSIDByName(String ps_name) throws Exception {
		String query = "SELECT * FROM payment_systems where ps_name LIKE '%"+ps_name+"%'";
		ResultSet resultSet = getStatement().executeQuery(query);
		resultSet.next();
		try {
			String ps_id = resultSet.getString("ps_id");
			String ps_name_db = resultSet.getString("ps_name");
			Map<String, String> map = new HashMap<String, String>();
			map.put("ps_id", ps_id);
			map.put("ps_name", ps_name_db);
			return map;
		}catch(Exception e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("ps_id", null);
			map.put("ps_name", null);
			return map;
		}
	}

}