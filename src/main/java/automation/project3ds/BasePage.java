package automation.project3ds;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class BasePage {
	
	Driver driver;
	
	public BasePage(Driver driver) {
		this.driver = driver;
	}

	public static String getBetweenText(String str, String pattern1, String pattern2) {
         Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
         Matcher m = p.matcher(str);
         if (m.find()) {
           return m.group(1);
         }else {
        	 return null;
         }
	}
	
	public static boolean isNumeric(String strNum) {
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
}
