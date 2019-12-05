package automation.project3ds;

import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class PS_gateway_compact_short extends PS_gateway_compact {

	public PS_gateway_compact_short(Driver driver) {
		super(driver);
	}
	
	public void createPayment() {
		setCardHolder();
		setCardNumber();
		setCardFullExp();
		setCardCvv();
		setAddressZip();
		clickBuyButton();
	}

}
