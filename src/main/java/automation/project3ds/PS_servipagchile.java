package automation.project3ds;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_servipagchile extends PS_ebanx {
	



	public PS_servipagchile(Driver driver) {
		super(driver);
	}
	
	public PS_ebanx2 createPayment() throws Exception {
		this.set_name();
		this.set_email();
		this.set_phone();
		this.clickSummit();
		return new PS_ebanx2(driver);
	}
	
	

}

