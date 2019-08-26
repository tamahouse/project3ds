import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.MyTunnel;

public class FilterTestSample {

	@Test
	public void meo() throws Exception {
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("src\\main\\java\\utility\\paymentMethodByCountry.xlsx");
		Recordset record = connection.executeQuery("select * from Sheet1");
		List<Map<String, String>> countries = MyTunnel.getMap(record);
		Recordset recordType = connection.executeQuery("select * from paymentMethod");
		List<Map<String, String>> types = MyTunnel.getMap(recordType);
		for (Map<String, String> map : types) {
			String type = map.get("paymentMethod");
			for (Map<String, String> country : countries) {
				Boolean x = false;
				for (int i = 1; i < 11; i++) {
					String payment = country.get("payment" + i);
					if (payment.equals(type)) {
						String co_id = country.get("co_id");
						System.out.println(type + " " + co_id);
						x = true;
						break;
					}
				}
				if(x == true) {
					break;
				}
			}
		}
	}

}
