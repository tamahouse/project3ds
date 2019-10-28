package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MerchantAdd {
	
	static Driver driver = AnnotationPage.getDriver();
	
//	By nameTxb = By.name("data[d_name]");
	By lastNameTxb = By.name("data[d_lastname]");
	By emailTxb = By.name("data[d_email]");
	By saveBtn = By.xpath("//*[@value='Save']");
	
	MerchantAdd merchantAdd = PageFactory.initElements(driver, MerchantAdd.class);
	
	@FindBy(name = "data[d_name]")
	private Element nameTxb;
}
