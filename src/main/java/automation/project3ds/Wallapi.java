package automation.project3ds;

import java.sql.ResultSet;
import java.sql.Statement;

public class Wallapi {
	
	static MyTunnel tunnel;
	public static String pagseguro_id;

	private static Statement getStatement() throws Exception {
		if(tunnel == null || tunnel.isWorking() == false) {
			tunnel = new MyTunnel("wallapi");
		}
		Statement statement = tunnel.getStatement("wallapi");
		return statement;
	}
	
	public static Boolean getIsConverted(String cl_id) throws Exception {
		Boolean x = isConverted(cl_id);
		for(int i =0; i< 20; i++) {
			if(x == true) {
				return x;
			}else {
				Thread.sleep(10000);
				x = isConverted(cl_id);
			}
		}
		return x;
	}
	
	private static Boolean isConverted(String cl_id) throws Exception {
		String query = "select cl_tracked from ps_clicks where cl_id ="+cl_id+" limit 1";
		ResultSet result = getStatement().executeQuery(query);
		result.next();
		Boolean x = result.getBoolean("cl_tracked");
		return x;
	}

}
