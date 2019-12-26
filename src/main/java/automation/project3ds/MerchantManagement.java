package automation.project3ds;

import org.openqa.selenium.By;

public class MerchantManagement extends BasePage{

	By firstMerchant = By.xpath("//*[contains(@id,'developer_')]");
	
	public MerchantManagement(Driver driver, String branch) {
		super(driver, branch);
	}

	public void openAID(String a_id) {
		String url = this.branch + "/admin/developers?&search%5Bapplication%5D="+a_id;
		driver.get(url);
	}
	
	public void openDID(String d_id) {
		String url = this.branch + "/admin/developers?&search%5Bapplication%5D="+d_id;
		driver.get(url);
	}
	
	public Merchant getFirstMerchant() {
		Element element = driver.getElement(firstMerchant);
		return new Merchant(element);
	}
	
	public String getDIDFromUrl() {
		String url = driver.getCurrentUrl("search",true);
		String d_id = url.substring(url.indexOf("[id]=")+5);
		return d_id;
	}
	
	public class Merchant {
		
		By idTxt = By.xpath("./*[count(//thead/tr/th[@id='ID']/preceding-sibling::*)+1]");
		By adminLoginBtn = By.xpath(".//a[contains(@href,'admin_login')]");
		
		String did;
		By xpath;
		
		public Merchant(Element element) {
			Element idElement = element.getElement(idTxt);
			String did = idElement.getText();
			this.did = did;
			this.xpath = By.id("developer_"+did);
		}
		
		public String getID() {
			return this.did;
		}
		
		public void clickAdminLogin() {
			Element element = driver.getElement(xpath);
			Element adminLogin = element.getElement(adminLoginBtn);
			adminLogin.click();
			driver.switchToWindows("dashboard", true);
		}
	}
	
}
