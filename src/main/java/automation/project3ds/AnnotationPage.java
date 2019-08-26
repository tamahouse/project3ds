package automation.project3ds;

import automation.project3ds.Driver.Browser;

public class AnnotationPage {

public static Driver driver;
	
	public static Driver getDriver() {
		if(driver == null) {
			driver = new Driver(Browser.Chrome);
		}
		return driver;
	}
}
