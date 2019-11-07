package automation.project3ds;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import javafx.util.Pair;

public class Network {
	
	public static String getCl_id(Driver driver) {
		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
		list.add(new Pair<>("referenceId=d", "&"));
		list.add(new Pair<>("MD=d", "&"));
		list.add(new Pair<>("ref=d", "&"));
		list.add(new Pair<>("refId=d", "&"));
		list.add(new Pair<>("ref_id=d", "&"));
		list.add(new Pair<>("sessionId/d", "/"));
		list.add(new Pair<>("referenceId=d", "&"));
		list.add(new Pair<>("gcMerchantTxId=d", "&"));
		list.add(new Pair<>("ref/d", "/"));
		list.add(new Pair<>("ref/d", "?"));
		list.add(new Pair<>("MD=d", "\""));
		list.add(new Pair<>("ref=d", "\""));
		list.add(new Pair<>("refId=d", "\""));
		list.add(new Pair<>("ref_id=d", "\""));
		list.add(new Pair<>("referenceId=d", "\""));
		list.add(new Pair<>("gcMerchantTxId=d", "\""));
		list.add(new Pair<>("gcMerchantTxId=d", "\""));
		for (LogEntry entry : entries) {
			System.out.println(entry.getMessage());
			 String str = entry.getMessage();
			for(Pair<String, String> pair: list) {
				String pattern1 = pair.getKey();
				String pattern2 = pair.getValue();
				String cl_id = BasePage.getBetweenText(str, pattern1, pattern2);
				Boolean isNumber = BasePage.isNumeric(cl_id);
				if(cl_id != null && isNumber == true) {
					return cl_id;
				}
			}
		}
		return "empty";
	}

}
