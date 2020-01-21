package automation.project3ds;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

public class PS_mycardcard {
	
	Driver driver;
	
	static Map<String, String> testCards = new HashMap<String, String>();
	static {
		testCards.put("MCARCH0000004358","5P9AMTP6APLRSRY6");
		testCards.put("MCARCH0000004359","A6EACC4RF74F486L");
		testCards.put("MCARCH0000004360","MLNWC4TYTDLAWLX4");
		testCards.put("MCARCH0000004361","G559LFGN6CHWF6QX");
		testCards.put("MCARCH0000004362","6PTGH4855LLPSH55");
		testCards.put("MCARCH0000004363","M453A5CX6Y5ALPTG");
		testCards.put("MCARCH0000004364","EPMEFA5D86SP5F6D");
		testCards.put("MCARCH0000004365","MGQAEDPM54HHQ59H");
		testCards.put("MCARCH0000004366","6CKPSFMEMRMDXF74");
		testCards.put("MCARCH0000004367","AH9M4PWDLSFDFYSM");
	};
	
	By cardIDTxb = By.id("card_id");
	By passwordTxb = By.id("card_password");
	By buyBtn = By.id("paybutton");
	By error = By.xpath("//*[@id='error'][not(contains(@style,'none'))]");

	public PS_mycardcard(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		for(Map.Entry<String, String> entry: testCards.entrySet()) {
			String cardID = entry.getKey();
			String password = entry.getValue();
			this.setCardID(cardID);
			this.setPassword(password);
			this.clickBuyButton();
			try {
				driver.sleep(1000);
				driver.getElement(error,30000);
				System.out.println(cardID +","+password+","+"(used)");
			}catch(Exception e) {
				System.out.println(cardID +","+password);
				break;
			}
		}
	}

	private void setCardID(String cardID) {
		Element element = driver.getElement(cardIDTxb);
		element.clear();
		element.sendKeys(cardID);
	}
	
	private void setPassword(String password) {
		Element element = driver.getElement(passwordTxb);
		element.clear();
		element.sendKeys(password);
	}
	
	private void clickBuyButton() {
		Element element = driver.getElement(buyBtn);
		element.click();
		driver.sleep(1000);
		try {
		driver.getElement(By.xpath("//*[@id='paybutton'][not(contains(@style,'none'))]"),30000);
		}catch(Exception ignore) {
			
		}
	}

	
}
