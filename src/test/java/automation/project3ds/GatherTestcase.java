package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class GatherTestcase {

	String host = "https://cardinaldocs.atlassian.net/wiki/spaces/CCen/pages/400654355/3DS+1.0+Test+Cases";

	By testName = By.xpath("//h2[contains(@id,'3DS1')]");
	By scenario = By.xpath("./tr[./th[contains(text(),'Scenario')]]/td");
	By testValue = By.xpath("./tr[./th[contains(text(),'Test Values')]]/td");
	By lookup = By.xpath("./tr[./th[contains(text(),'Expected Results - Lookup')]]/td");
	By authentication = By.xpath("./tr[./th[contains(text(),'Expected Results - Authentication')]]/td");
	By action = By.xpath("./tr[./th[contains(text(),'Merchant Action')]]/td");
	String filePath = utility.ConfigFile.getProjectPath() + "src\\main\\java\\utility\\cardNumber_v1.xlsx";

	@Test
	public void gatherData() throws Exception {
		Driver driver = new Driver();
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(filePath);
		String query;
		driver.get(host);
		int count = 0;
		List<Element> list = driver.getElements(testName);
		for (int i = 0; i<25; i++) {
			Element header = list.get(i);
			System.out.println(++count);
			String B = header.getText();
			Element element = header.getElement(By.xpath("./following-sibling::*/table/tbody"));
			String C = element.getElement(scenario).highlight().getText();
			String D = element.getElement(testValue).highlight().getText();
			String E = element.getElement(lookup).highlight().getText();
			String F ="blank";
			try {
				Element fElement = element.getElement(authentication);
				element.highlight();
				F = fElement.getText();
			}catch(Exception e) {
			}
			String G = element.getElement(action).highlight().getText();
			query = "INSERT INTO Sheet1(testName,Scenario,PAN,expected_results_lookup,expected_results_authentication,merchant_action) VALUES ('"
					+ B + "','" + C + "','" + D + "','" + E + "','"
					+ F + "','" + G + "')";
			connection.executeUpdate(query);
		}
	}

}
