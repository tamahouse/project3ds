package automation.project3ds;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static String getUniqueText() {
		String timestamp = String.valueOf(System.currentTimeMillis());
		return timestamp.replace("0", "a")
				.replace("1", "b")
				.replace("2", "c")
				.replace("3", "d")
				.replace("4", "e")
				.replace("5", "f")
				.replace("6", "g")
				.replace("7", "h")
				.replace("8", "i")
				.replace("9", "j");
				
	}
}
