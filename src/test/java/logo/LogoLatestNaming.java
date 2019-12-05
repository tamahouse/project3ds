package logo;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.LogoReportBuilder;
import javafx.util.Pair;

@Listeners(automation.project3ds.ExtentListener.class)
public class LogoLatestNaming extends BaseTest{

	static Map<String, Pair<Integer, Integer>> ruleMap;


	static String branch = "http://feature-pwl-2081.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(branch).generate();
	String developHost = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall";

	private List<Map<String, String>> importData() throws Exception {
		List<Map<String, String>> psname = new ArrayList<Map<String, String>>();
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("src\\main\\java\\utility\\logo.xlsx");
		String query = "select * from psname where enable = 1";
		Recordset record = connection.executeQuery(query);
		List<String> names = record.getFieldNames();
		while (record.next()) {
			Map<String, String> map = new HashMap<String, String>();
			for (String name : names) {
				String key = name;
				String value = record.getField(name);
				map.put(key, value);
			}
			psname.add(map);
		}
		connection.close();
		return psname;
	}

	@DataProvider(name = "data")
	public Object[][] data() throws Exception {
		driver = new Driver();
		login(developHost);
		login(host);
		List<Object[]> temp = new ArrayList<Object[]>();
		List<Map<String, String>> list = this.importData();
		for (Map<String, String> map : list) {
			String shortcode = map.get("shortcode");
			Object[] e = new Object[] { shortcode, map };
			temp.add(e);
		}
		Object[][] data = temp.toArray(new Object[][] {});
		return data;
	}

	@Test(dataProvider = "data")
	public void execute(String shortcode, Map<String, String> excelMap) throws IOException, Exception {
		Map<String, File> logoMap = distributeImageIntoType(excelMap);
		LogoReportBuilder report = new LogoReportBuilder(branch, host, driver);
		report.build(shortcode, excelMap, logoMap);
	}
	
	
	private Map<String, File> distributeImageIntoType(Map<String, String> excelMap) {
		String logo = excelMap.get("logo");
		String folderName = excelMap.get("folder");
		String folderPath = "G:\\My Drive\\PS platform team\\PS logos resized\\" + folderName;
		File folder = new File(folderPath);
		File[] files = folder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.toLowerCase().endsWith(".png") && !name.toLowerCase().contains("original")) {
					return true;
				}
				return false;
			}
		});
		Map<String, File> logoMap = new HashMap<String, File>();
		for (File file : files) {
			String name = file.getName().toLowerCase().replace(".png", "");
			String called_name = excelMap.get("called_name").toLowerCase();
			if (name.contains(called_name)) {
				if (name.equals("pm_" + called_name)) {
					logoMap.put("multi", file);
					System.out.println("multi "+ file.getName());
				} else if (name.equals("pm_" + called_name + "_big")) {
					logoMap.put("uni", file);
					System.out.println("uni "+ file.getName());
				} else if (name.equals("pm_" + called_name + "_ma5")) {
					logoMap.put("v5", file);
					System.out.println("v5 "+ file.getName());
				} else if (name.equals(called_name)) {
					logoMap.put("light", file);
					System.out.println("light "+ file.getName());
					if (!logo.equals("")) {
						logoMap.put("lightLogo", file);
						System.out.println("lightLogo "+ file.getName());
					}
				} else if (name.equals(called_name + "@2x")) {
					logoMap.put("light2", file);
					System.out.println("lgiht2 "+ file.getName());
					if (!logo.equals("")) {
						logoMap.put("light2Logo", file);
						System.out.println("light2Logo "+ file.getName());
					}
				} else if (name.equals(called_name + "@3x")) {
					logoMap.put("light3", file);
					System.out.println("light3 "+ file.getName());
					if (!logo.equals("")) {
						logoMap.put("light3Logo", file);
						System.out.println("light3Logo "+ file.getName());
					}
				} else if (name.equals(called_name + "_d")) {
					logoMap.put("dark", file);
					System.out.println("dark "+ file.getName());
					if (!logo.equals("")) {
						logoMap.put("darkLogo", file);
						System.out.println("darkLogo "+ file.getName());
					}
				} else if (name.equals(called_name + "_d@2x")) {
					logoMap.put("dark2", file);
					System.out.println("dark2 "+ file.getName());
					if (!logo.equals("")) {
						logoMap.put("dark2Logo", file);
						System.out.println("dark2Logo "+ file.getName());
					}
				} else if (name.equals(called_name + "_d@3x")) {
					logoMap.put("dark3", file);
					System.out.println("dark3 "+ file.getName());
					if (!logo.equals("")) {
						logoMap.put("dark3Logo", file);
						System.out.println("dark3Logo "+ file.getName());
					}
				} else {
					Assertion.get().assertEquals(name, "name", "[NotInType]");
				}
			} else {
				Assertion.get().assertEquals(name, "name", "[NamedWrong]");
			}
		}
		return logoMap;

	}

	

}
