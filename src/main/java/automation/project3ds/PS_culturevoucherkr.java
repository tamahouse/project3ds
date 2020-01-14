package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class PS_culturevoucherkr {
	
	Driver driver;
	
	String ID = "test1";
	String PASSWORD = "1111";
	
	By idTxb = By.id("cultureid-auth");
	By passwordTxb = By.id("culturepass-auth");
	By loginBtn = By.id("submit-button");
	By balanceTxt = By.id("culture-account-amount");
	
	

	public PS_culturevoucherkr(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() throws Exception {
		this.setID();
		this.setPassword();
		this.waitForBalance();
		this.clickPayButton();
	}

	private void setID() {
		Element element = driver.getElement(idTxb);
		element.sendKeys(ID);
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb);
		element.sendKeys(PASSWORD);
	}
	
	private void clickLoginButton() {
		Element element = driver.getElement(loginBtn);
		element.click();
	}
	
	private void waitForBalance() {
		driver.getElement(balanceTxt);
	}
	
	private void clickPayButton() {
		Element element = driver.getElement(loginBtn);
		element.click();
	}

}
