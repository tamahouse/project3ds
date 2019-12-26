package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class PS_fasterpay2 {
	
	Driver driver;
	String EMAIL = "pwoptiman1-buyer@gmail.com";
	String PASSWORD = "PeterInc#";
	
	String redirectUrl = "pay2.fasterpay";
	
	private String firstName = "First";
	private String lastName = "Last";
	private String cardNumber = "5200000000001096";
	private String exp = "1230";
	private String cvv = "123";
	private String phone = "5555555";
	private String address = "address";
	private String city = "fl";
	private String zip = "32043";
	private String password = "Ez654321";
	private String email;
	
	private String is_active_step = "//*[contains(@class,'is-active')][contains(@class,'step')]";
	private By activeStep = By.xpath(is_active_step);
	private By emailTxb = By.xpath(is_active_step+"//*[@data-text='Email']");
	private By firstNameTxb = By.xpath(is_active_step+"//*[contains(@class,'js-firstname')]");
	private By lastNameTxb = By.xpath(is_active_step+"//*[contains(@class,'js-lastname')]");
	private By cardNumberIframe = By.xpath(is_active_step+"//*[contains(@class,'js-number-hosted')]/iframe");
	private By expIframe = By.xpath(is_active_step+"//*[contains(@class,'js-exp-hosted')]/iframe");
	private By cvvIframe = By.xpath(is_active_step+"//*[contains(@class,'js-cvv-hosted')]/iframe");
	private By termCheckbox = By.xpath(is_active_step+"//*[contains(@class,'js-terms-accepted')]/label");
	private By payButton = By.xpath(is_active_step+"//*[contains(@class,'js-button-text')][./*[@data-text='Pay']]");
	
	private By passwordTxb = By.xpath(is_active_step+"//*[@data-text='Password']");
	private By setPasswordBtn = By.xpath(is_active_step+"//*[@data-text='Set password']");
	
	private By continueButton = By.xpath(is_active_step+"//*[contains(@class,'js-button-text')][@data-text='Continue']");
	
	private By loginLink = By.xpath(is_active_step+"//*[@data-text='Log in']");
	private By loginButton = By.xpath(is_active_step+"//*[contains(@class,'js-button-text')][@data-text='Log in']");
	
	private By phoneTxb = By.xpath(is_active_step+ "//*[contains(@class,'js-phone-number')]");
	private By addressTxb = By.xpath(is_active_step+ "//*[contains(@class,'js-address')]");
	private By cityTxb = By.xpath(is_active_step+ "//*[contains(@class,'js-city')]");
	private By zipTxb = By.xpath(is_active_step+ "//*[contains(@class,'js-zip')]");
	
	private By backButton = By.xpath(is_active_step+"//*[contains(@class,'js-button-back')][@data-text='Back']");
	

	
	public PS_fasterpay2 (Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	
	public ThankyouPage finishPayment() {
		this.setDynamicEmail();
		this.setFirstName();
		this.setLastName();
		this.setCardNumber();
		this.setExp();
		this.setCvv();
		this.clickPayButton();
		this.setPassword();
		this.clickSetPasswordButton();
		return new ThankyouPage(driver);
	}
	
	
	public String getEmail() {
		return this.email;
	}
	
	private void setDynamicEmail() {
		String time = String.valueOf(System.currentTimeMillis());
		email = "meo"+ time + "@spam4.me";
		Element element = driver.getElement(emailTxb);
		driver.sleep(1000);
		element.sendKeys(email);
	}
	
	private void setEmail(String email) {
		Element element = driver.getElement(emailTxb);
		driver.sleep(1000);
		element.sendKeys(email);
	}
	
	private void setFirstName() {
		Element element = driver.getElement(firstNameTxb);
		element.sendKeys(firstName);
	}
	
	private void setLastName() {
		Element element = driver.getElement(lastNameTxb);
		element.sendKeys(lastName);
	}
	
	private void setCardNumber() {
		WebElement iframe = driver.getElement(cardNumberIframe).getWebElement();
		driver.switchTo().frame(iframe);
		Element element = driver.getElement(By.id("input"));
		element.sendKeys(cardNumber);
		driver.switchTo().defaultContent();
	}
	
	private void setExp() {
		WebElement iframe = driver.getElement(expIframe).getWebElement();
		driver.switchTo().frame(iframe);
		Element element = driver.getElement(By.id("input"));
		element.sendKeys(exp);
		driver.switchTo().defaultContent();
	}
	
	private void setCvv() {
		WebElement iframe = driver.getElement(cvvIframe).getWebElement();
		driver.switchTo().frame(iframe);
		Element element = driver.getElement(By.id("input"));
		element.sendKeys(cvv);
		driver.switchTo().defaultContent();
	}
	
	private void clickTermCheckbox() {
		try {
		Element element = driver.getElement(termCheckbox);
		element.click();
		}catch(Exception e) {
			
		}
	}
	
	private void clickPayButton() {
		Element element = driver.getElement(payButton);
		element.click();
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueButton);
		element.click();
	}
	
	private void clickLoginLink() {
		Element element = driver.getElement(loginLink);
		element.click();
	}
	
	private void setPassword() {
		Element element = driver.getElement(passwordTxb,60000);
		driver.sleep(1000);
		element.sendKeys(password);
	}
	
	private void setPassword(String password) {
		Element element = driver.getElement(passwordTxb);
		driver.sleep(1000);
		element.sendKeys(password);
	}
	
	private void clickSetPasswordButton() {
		Element element = driver.getElement(setPasswordBtn);
		driver.sleep(1000);
		element.click();
	}
	
	private void clickLoginButton() {
		Element element = driver.getElement(loginButton);
		element.click();
	}
	
	private void setPhone() {
		Element element = driver.getElement(phoneTxb);
		driver.sleep(1000);
		element.sendKeys(phone);
	}
	
	private void setAddress() {
		Element element = driver.getElement(addressTxb);
		element.sendKeys(address);
	}
	
	private void setCity() {
		Element element = driver.getElement(cityTxb);
		element.sendKeys(city);
	}
	
	private void setZip() {
		Element element = driver.getElement(zipTxb);
		element.sendKeys(zip);
	}

	
	public void payWithResignForm() {
		setDynamicEmail();
		setFirstName();
		setLastName();
		setCardNumber();
		setExp();
		setCvv();
		clickTermCheckbox();
		clickPayButton();
	}
	
	public void payWithLoginedForm() {
		setCardNumber();
		setExp();
		setCvv();
		clickContinueButton();
	}
	
	
	public void login(String email, String password) {
		clickLoginLink();
		setEmail(email);
		setPassword(password);
		clickLoginButton();
	}
	
	public void fillAddress() {
		setPhone();
		setAddress();
		setCity();
		setZip();
		clickPayButton();
	}
	
	public String getLastActiveStep() {
		driver.getElement(backButton,60000);
		Element element = driver.getElement(activeStep);
		String className = element.getAttribute("class");
		return className;
	}



}
