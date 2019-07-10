package automation.project3ds;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.testng.annotations.*;

	

public class Aa {
	String hostValue = null;
	
	@Parameters({"hostValue"})
	@BeforeClass
	public void setUp(@Optional("url") String hostValue) {
		this.hostValue = hostValue;
	}
	
	@DataProvider(name = "data")
	public Object[][] data() {
		String param1 = "param1Value";
		String param2 = "param2Value";
		Object[][] obj = {{param1, hostValue, param2}, {param1, hostValue, param2}};
		return obj;
	}
  
	
	@Test(dataProvider = "data")
	public void meo(String param1, String hostValue, String param2) throws Exception {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		  Date date = new Date(stamp.getTime());
		  SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd_hh-mm");
		  String formattedDate = sdf.format(date);
	System.out.println("test do working");
	System.out.println(formattedDate);
	MySoftAssertAll assertion = new MySoftAssertAll();
//	assertion.assertEquals("meo", "gau", "[MESSAGE]");
	assertion.assertAll();
	}
}
