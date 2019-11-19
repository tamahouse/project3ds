package automation.project3ds;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_psecolombia extends PS_ebanx {
	

	public PS_psecolombia(Driver driver) {
		super(driver);
	}
	
	public PS_ebanx2 createPayment() throws Exception {
		this.clickBank();
		this.set_name();
		this.set_email();
		this.set_phone();
		this.clickSummit();
		return new PS_ebanx2(driver);
	}
	
	private void clickBank() {
		driver.getElement(bankOptions);
		driver.sleep(1000);
		List<Element> list = driver.getElements(bankOptions);
		Random random = new Random();
		int index = random.nextInt(list.size());
		Element element = list.get(index);
		element.click();
		
	}
	

}

