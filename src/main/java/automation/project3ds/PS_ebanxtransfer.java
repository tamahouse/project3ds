package automation.project3ds;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_ebanxtransfer extends PS_ebanx {
	
	
	
	By bankOptions = By.xpath("//*[@id='step1']//div[@class='payment-method']");
	By radioButton = By.xpath("(//i[@class='icon-radio'])[2]");


	public PS_ebanxtransfer(Driver driver) {
		super(driver);
	}
	
	public PS_ebanx2 createPaymentWithBank() throws Exception {
		this.clickBank();
		return this.createPayment();
	}
	
	private void clickBank() {
		driver.getElement(bankOptions);
		driver.sleep(1000);
		List<Element> list = driver.getElements(bankOptions);
		Random random = new Random();
		int index = random.nextInt(list.size());
		Element element = list.get(index);
		element.click();
		if(index == 1) {
			driver.getElement(radioButton).click();
		}
		
	}
	

}

