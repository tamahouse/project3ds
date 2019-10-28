package automation.project3ds;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class WidgetPaymentMethods extends WidgetMainFrame {
	
	Driver driver;

	public WidgetPaymentMethods(Driver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public class tab_epinpaymentsystem_Mint {

		By pinCurrency = By.id("select_currency");
		By buyMint = By.xpath("//*[text()=\"Don't have Mint yet? Click here to buy!\"]");
		By displayAmount = By.xpath("//*[@id='form']/h3/strong");

		public void setPinCurrency(String visibleText) {
			Select select = new Select(driver.getElement(pinCurrency));
			select.selectByVisibleText(visibleText);
		}

		public void clickBuyMint() {
			Element element = driver.getElement(buyMint);
			element.click();
		}

		public String getDisplayAmount() {
			Element element = driver.getElement(displayAmount);
			String str = element.getText();
			return str;
		}
	}

	public class tab_mobilegateway_Mobiamo {
		By enterPinIcon = By.xpath(
				"//article[contains (@id,'step') and not (contains(@style, 'none'))]//*[@id='input_pin_cover']/label");
		By enterPinTxb = By
				.xpath("//article[contains (@id,'step') and not (contains(@style, 'none'))]//input[@id='input_pin']");
		By enterPinErrorMessage = By.xpath(
				"//article[contains (@id,'step') and not (contains(@style, 'none'))]//*[@id='pwsmsError' and contains(@style,'block')]");
		By continueButton = By
				.xpath("//article[contains (@id,'step') and not (contains(@style, 'none'))]//input[@id='button_pin']");
		By success = By.xpath("//*[@id='final_animation' and contains(@style,'block')]");
		By term = By.xpath("//footer//a[text()='Terms of Service']");
		private By button_pin_loader = By.xpath(
				"//article[contains(@id,'step') and not (contains(@style,'none'))]//input[@id='button_pin_loader' and not(contains(@style,'none'))]");

		public void setPin() {
			setPin("ACBDEF");
		}

		public void setPin(String pin) {
			Element element = driver.getElement(enterPinTxb);
			element.sendKeys(pin);
		}

		public void clickContinue() throws Exception {
			Element element = driver.getElement(continueButton);
			element.click();
			driver.waitForNumberOfElement(button_pin_loader, 0, 60000);
		}

		public Element getEnterPinIcon() {
			return driver.getElement(enterPinIcon);
		}

		public Element getEnterPinTxb() {
			return driver.getElement(enterPinTxb);
		}

		public Element getPinErrorMessage() {
			return driver.getElement(enterPinErrorMessage);
		}

		public Element getSuccessBlock() {
			return driver.getElement(success);
		}

		public void clickTerm() {
			Element element = driver.getElement(term);
			element.click();
		}
	}

	public class tab_boletobancario_Boleto {

		By type = By.id("type");
		By responsible_cpf = By.id("responsible_cpf");
		By cnpj = By.id("cnpj");
		By cpf = By.id("cpf");
		By name = By.id("name");
		By birth_date_day = By.id("birth_date_day");
		By birth_date_month = By.id("birth_date_month");
		By birth_date_year = By.id("birth_date_year");
		By email = By.id("email");
		By phone = By.id("phone");
		By company_name = By.id("company_name");
		By zipcode = By.id("zipcode");
		By payment_button = By.id("payment_button");
		By error = By.xpath("//*[@id='error_message' and contains(@style,'block')]");
		By spinner = By.xpath("//*[@id='preloader_container' and not(contains(@style,'none'))]");
		By print = By.xpath(
				"//*[contains(@id,'step') and not(contains(@style,'none'))]//*[@id ='step2_link']");

		String TYPE = "personal";
		String CPF = "43622652101";
		String CNPJ = "67878183000113";
		String NAME = "Leonor S Alves";
		String BIRTH_DATE_DAY = "12";
		String BIRTH_DATE_MONTH = "12";
		String BIRTH_DATE_YEAR = "1912";
		String EMAIL = "meo@spam4.me";
		String PHONE = "(27) 4945-8003";
		String ZIPCODE = "29161401";
		String COMPANY_NAME = "Company Name";

		public Element getPrintButton() {
			return driver.getElement(print);
		}

		public void set_type(String str) {
			Select select = new Select(driver.getElement(type));
			select.selectByValue(str);
		}

		public void set_cpf(String str) {
			driver.getElement(cpf).sendKeys(str);
		}

		public void set_responsible_cpf(String str) {
			driver.getElement(responsible_cpf).sendKeys(str);
		}

		public void set_cnpj(String str) {
			driver.getElement(cnpj).sendKeys(str);
		}

		public void set_name(String str) {
			driver.getElement(name).sendKeys(str);
		}

		public void set_birth_date_day(String str) {
			driver.getElement(birth_date_day).sendKeys(str);
		}

		public void set_birth_date_month(String str) {
			driver.getElement(birth_date_month).sendKeys(str);
		}

		public void set_birth_date_year(String str) {
			driver.getElement(birth_date_year).sendKeys(str);
		}

		public void set_email(String str) {
			driver.getElement(email).sendKeys(str);
		}

		public void set_phone(String str) {
			driver.getElement(phone).sendKeys(str);
		}

		public void set_company_name(String str) {
			driver.getElement(company_name).sendKeys(str);
		}

		public void set_zipcode(String str) {
			driver.getElement(zipcode).sendKeys(str);
		}

		public void set_cpf() {
			set_cpf(CPF);
		}

		public void set_responsible_cpf() {
			set_responsible_cpf(CPF);
		}

		public void set_cnpj() {
			set_cnpj(CNPJ);
		}

		public void set_name() {
			set_name(NAME);
		}

		public void set_birth_date_day() {
			set_birth_date_day(BIRTH_DATE_DAY);
		}

		public void set_birth_date_month() {
			set_birth_date_month(BIRTH_DATE_MONTH);
		}

		public void set_birth_date_year() {
			set_birth_date_year(BIRTH_DATE_YEAR);
		}

		public void set_email() {
			set_email(EMAIL);
		}

		public void set_phone() {
			set_phone(PHONE);
		}

		public void set_company_name() {
			set_company_name(COMPANY_NAME);
		}

		public void set_zipcode() {
			set_zipcode(ZIPCODE);
		}

		public void clickSummit() throws Exception {
			driver.getElement(payment_button).click();
			driver.waitForNumberOfElement(spinner, 0, 20000);
		}

		public Element getError() {
			return driver.getElement(error);
		}

		public void createPersonalPayment() throws Exception {
			set_type("personal");
			set_cpf();
			set_name();
			set_birth_date_day();
			set_birth_date_month();
			set_birth_date_year();
			set_email();
			set_phone();
			set_zipcode();
			clickSummit();
		}

		public void createBussinessPayment() throws Exception {
			set_type("business");
			set_responsible_cpf();
			set_cnpj();
			set_name();
			set_birth_date_day();
			set_birth_date_month();
			set_birth_date_year();
			set_email();
			set_phone();
			set_company_name();
			set_zipcode();
			clickSummit();
		}
	}

	public class tab_idealpayments_Ideal {
		By paymentOptions = By.xpath("//*[@id='payment-options']/*[@class='payment-method']");
		By thankyou = By.xpath("//*[./h3[text()='Thank you for your purchase!']]");
		

		public List<String> getListBankIds() {
			List<String> list = new ArrayList<String>();
			driver.getElement(paymentOptions);
			List<Element> elements = driver.getElements(paymentOptions);
			for (Element element : elements) {
				String data_id = element.getElement(By.xpath("./a")).getAttribute("data-id");
				list.add(data_id);
			}
			return list;
		}

		public void clickBank(String data_id) {
			By by = By.xpath("//*[@id='payment-options']//*[@data-id='" + data_id + "']");
			driver.getElement(by).click();
		}

		public void finishPaymentStep() throws Exception {
			By primaryButton = By.xpath("//button[@class='btn btn-primary']");
			List<String> tabs = driver.waitForNewTab();
			Driver driver = AnnotationPage.driver;
			driver.switchTo().window(tabs.get(1));
			driver.getElement(primaryButton).click();;
			driver.getElement(primaryButton).click();
			driver.getElement(primaryButton).click();
			driver.navigate().refresh();
			driver.getElement(thankyou);
			driver.close();
			driver.switchTo().window(tabs.get(0));
		}

	}

}
