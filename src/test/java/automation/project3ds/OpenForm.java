package automation.project3ds;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.PageHome1108.MainIframe;
import automation.project3ds.PageHome1108.MainIframe.Iframecc;

public class OpenForm {
	final String host = "http://feature-pwg-1108.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=Brick+3DS+2.0+Bamboo+Test+%28JammyWall%29%5B101607%5D&data%5Ba_id%5D=101607&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=218069&data%5Bag_type%5D=fixed";
	Driver driver;
	By nameTxb = By.id("login");
	By passwordTxb = By.id("password");
	By loginBtn = By.id("submit_button");
	String name = utility.ConfigFile.name;
	String password = utility.ConfigFile.password;
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = new Driver();
		driver.get(host);
		Element nameTextbox = driver.getElement(nameTxb);
		Element passwordTextbox = driver.getElement(passwordTxb);
		Element loginButton = driver.getElement(loginBtn);
		nameTextbox.sendKeys(name);
		passwordTextbox.sendKeys(password);
		loginButton.click();
	}
	
	@Test
	public void execute() {
		Boolean x = false;
		while(x== false) {
		try {
		driver.get(host);
		PageHome1108 home = new PageHome1108(driver);
		MainIframe mainIframe = home.getMainIframe();
		String refID = null;
		mainIframe.clickBuyButton();
		Iframecc iframecc = mainIframe.getIframecc();
		iframecc.setCardNumber("111111111");
		}catch(Exception e) {
			x = true;
		}
		
		
		}
	}

}
