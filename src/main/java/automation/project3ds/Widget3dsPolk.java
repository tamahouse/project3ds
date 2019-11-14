package automation.project3ds;

import org.openqa.selenium.By;

public class Widget3dsPolk {

	Driver driver;

	static By submitButton = By.xpath("//form[@name='postPAResToMPIForm']//input[@type='submit']");

	public Widget3dsPolk(Driver driver) {
		this.driver = driver;
	}
	
	public void clickSubmit() {
		Element element = driver.getElement(submitButton,60000);
		AnnotationPage.sleep(1000);
		element.highlight();
		element.clickJS();
		AnnotationPage.sleep(1000);
		try {
			driver.switchTo().alert().accept();
			AnnotationPage.sleep(1000);
		}catch(Exception ignore) {
			
		}
	}
	
}
