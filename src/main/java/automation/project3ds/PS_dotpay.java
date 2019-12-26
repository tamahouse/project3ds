package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class PS_dotpay {
	
	Driver driver;
	
	String FIRSTNAME = "Payment";
	String LASTNAME = "Wall";
	String EMAIL = "meo@spam4.me";
	
	By paymentOptions = By.xpath("//*[@id='btpoland_wrapper']//*[@data-channel-id]");
	By firstnameTxb = By.id("first_name");
	By lastnameTxb = By.id("last_name");
	By emailTxb = By.id("email");
	By proceedBtn = By.id("submitButton");

	
	

	public PS_dotpay(Driver driver) {
		this.driver = driver;
	}
	
	public PS_dotpay2 createPayment() throws Exception {
		this.fillForm();
		return new PS_dotpay2(driver);
	}
	
	public void fillForm() {
		this.setFirstName();
		this.setLastName();
		this.setEmail();
		this.clickProceedBtn();
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
		Element element = banks.get(6);
		element.highlight();
//		By bybank = By.xpath("//*[@id='payment-options']//*[@data-id='" + data_id + "']");
//		By byxButton = By.xpath("//*[@id='ps_new_window_popup']/div/a");
//		driver.getElement(bybank).click();
		element.click();
		
	}
	
	public void setFirstName() {
		Element element = driver.getElement(firstnameTxb);
		element.sendKeys(FIRSTNAME);
	}
	
	public void setLastName() {
		Element element = driver.getElement(lastnameTxb);
		element.sendKeys(LASTNAME);
	}
	
	public void setEmail() {
		Element element = driver.getElement(emailTxb);
		element.sendKeys(EMAIL);
	}
	
	public void clickProceedBtn() {
		Element element = driver.getElement(proceedBtn);
		element.click();
	}

}
