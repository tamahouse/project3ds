package automation.project3ds;


public class AnnotationPage {

public static Driver driver;
	
	public static Driver getDriver() {
		if(driver == null) {
			driver = new Driver();
		}
		return driver;
	}
}
