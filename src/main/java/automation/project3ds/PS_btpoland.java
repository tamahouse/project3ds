package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class PS_btpoland {
	
	Driver driver;
	
	String NAME = "Payment Wall";
	String EMAIL = "meo@spam4.me";
	
	By paymentOptions = By.xpath("//*[@id='btpoland_banks']//*[@data-bank-code]");
	By nameTxb = By.id("full-name");
	By emailTxb = By.id("p-form-email");
	By proceedBtn = By.id("submitButton");

	
	

	public PS_btpoland(Driver driver) {
		this.driver = driver;
	}
	
	public PS_btpoland2 createPayment() throws Exception {
		this.clickBank();
		this.setName();
		this.setEmail();
		this.clickProceedBtn();
		return new PS_btpoland2(driver);
	}

	private List<Element> getListBankIds() {
		driver.getElement(paymentOptions);
		driver.sleep(2000);
		List<Element> elements = driver.getElements(paymentOptions);
//		for (Element element : elements) {
//			String data_id = element.getElement(By.xpath("./a")).getAttribute("data-id");
//			list.add(data_id);
//		}
		return elements;
	}

	public void clickBank() {
		List<Element> banks = this.getListBankIds();
		Random random = new Random();
		Element element = banks.get(random.nextInt(banks.size()));
//		By bybank = By.xpath("//*[@id='payment-options']//*[@data-id='" + data_id + "']");
//		By byxButton = By.xpath("//*[@id='ps_new_window_popup']/div/a");
//		driver.getElement(bybank).click();
		element.click();
		
	}
	
	private void setName() {
		Element element = driver.getElement(nameTxb);
		element.sendKeys(NAME);
	}
	
	private void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	private void clickProceedBtn() {
		Element element = driver.getElement(proceedBtn);
		element.click();
	}

}
