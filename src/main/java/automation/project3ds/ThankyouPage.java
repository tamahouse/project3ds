package automation.project3ds;

import org.openqa.selenium.By;

public class ThankyouPage {
	
	Driver driver;
	
	By complete = By.xpath("//*[contains(@class,'thanks')]");

	public ThankyouPage(Driver driver) {
		this.driver = driver;
		driver.getElement(complete);
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public String getUnique(String pattern1) {
		String url = this.getCurrentUrl();
		String unique = BasePage.getBetweenText(url, pattern1, "&");
		if(unique == null) {
			unique = url.substring(url.indexOf(pattern1)+pattern1.length(), url.length());
		}
		return unique;
	}
	
	public String getClickID(String pattern1) throws Exception {
		String unique = this.getUnique(pattern1);
		String cl_id = Pslog.get_cl_id_email_Fasterpay(unique);
		return cl_id;
	}
}
