import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetPaymentMethods;

public class WidgetBrick_1v6_Test_tab_epinpaymentsystem_Mint {

	String host;
	String type;
	
	static Driver driver;

	@Parameters({"type"})
	@BeforeClass
	public void setUp(String type) throws Exception {
		this.type = type;
		host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D="+type+"&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&data%5Bps%5D=dummy&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";
		Login.login(host);
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("src\\main\\java\\utility\\paymentMethodByCountry.xlsx");
		Recordset record = connection.executeQuery("select * from paymentMethod where p1_paymentMethod = '"+id+"'");
		record.next();
		id = record.getField(type+"_paymentMethod");
	}

	
	String id = "tab_epinpaymentsystem";

	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}

	@Test
	public void pinCurrency() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPaymentMethod(type,id);
		WidgetPaymentMethods.tab_epinpaymentsystem_Mint.setPinCurrency("EUR");
		List<Element> list = WidgetMainFrame.getPrices();
		for(Element element : list) {
			String priceText = element.getElement(By.xpath("./*[@class='price']")).getText();
			Assertion.get().assertContains(priceText, "€", "[pinCurrency]");
		}
		Assertion.end();
		
	}
	
	@Test
	public void buyMintLink() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPaymentMethod(type,id);
		WidgetPaymentMethods.tab_epinpaymentsystem_Mint.clickBuyMint();
		List<String> list = driver.waitForNewTab();
		driver.switchTo().window(list.get(1));
		driver.waitUrlNotBlank();
		String url = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(list.get(0));
		Assertion.get().assertEquals(url, "https://www.mintprepaid.com/buy", "[Buy Mint Link]");
		Assertion.end();
	}
	
	@Test
	public void buyMintLinkBuyForm() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPaymentMethod(type,id);
		WidgetMainFrame.clickBuyButton(type);
		WidgetPaymentMethods.tab_epinpaymentsystem_Mint.clickBuyMint();
		List<String> list = driver.waitForNewTab();
		driver.switchTo().window(list.get(1));
		driver.waitUrlNotBlank();
		String url = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(list.get(0));
		Assertion.get().assertEquals(url, "https://www.mintprepaid.com/buy", "[Buy Mint Link]");
		Assertion.end();
	}
	
	@Test
	public void priceTag() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickPaymentMethod(type,id);
		WidgetPaymentMethods.tab_epinpaymentsystem_Mint.setPinCurrency("EUR");
		String price = WidgetMainFrame.getPrices().get(0).getElement(By.xpath("./*[@class='price']")).getText();
		String priceValue = price.substring(1,price.length());
		if(priceValue.substring(priceValue.length()-1).equals("0")) {
			priceValue = priceValue.substring(0, priceValue.length()-1);
		}
		WidgetMainFrame.clickBuyButton(type);
		String displayAmount = WidgetPaymentMethods.tab_epinpaymentsystem_Mint.getDisplayAmount();
		Assertion.get().assertContains(displayAmount, "EUR", "[Currency]");
		Assertion.get().assertContains(displayAmount, priceValue, "[priceValue]");
		Assertion.end();
	}
	
	
	
	
	
	
	

	
	
}
