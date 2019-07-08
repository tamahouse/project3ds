package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class GatherTestcase {

	String host = "https://cardinaldocs.atlassian.net/wiki/spaces/CCen/pages/903577725/EMV+3DS+2.0+Test+Cases";

	By testName = By.xpath("//h2[contains(@id,'EMV3DS2')]");
	By scenario = By.xpath("./tr[./th[contains(text(),'Scenario')]]/td");
	By testValue = By.xpath("./tr[./th[contains(text(),'Test Values')]]/td");
	By lookup = By.xpath("./tr[./th[contains(text(),'Expected Results - Lookup')]]/td");
	By authentication = By.xpath("./tr[./th[contains(text(),'Expected Results - Authentication')]]/td");
	By action = By.xpath("./tr[./th[contains(text(),'Merchant Action')]]/td");

	@Test
	public void gatherData() throws Exception {
		Driver driver = new Driver();
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(utility.ConfigFile.filePath);
		String query;
		driver.get(host);
		int count = 0;
		List<Element> list = driver.getElements(testName);
		for (Element header : list) {
			System.out.println(++count);
			String B = header.getText();
			Element element = header.getElement(By.xpath("./following-sibling::*/table/tbody"));
			String C = element.findElement(scenario).highlight().getText();
			String D = element.findElement(testValue).highlight().getText();
			String E = element.findElement(lookup).highlight().getText();
			String F ="blank";
			try {
				Element fElement = element.findElement(authentication);
				element.highlight();
				F = fElement.getText();
			}catch(Exception e) {
			}
			String G = element.findElement(action).highlight().getText();
			query = "INSERT INTO Sheet1(testName,Scenario,test_value,expected_results_lookup,expected_results_authentication,merchant_action) VALUES ('"
					+ B + "','" + C + "','" + D + "','" + E + "','"
					+ F + "','" + G + "')";
			connection.executeUpdate(query);
		}
	}

}
