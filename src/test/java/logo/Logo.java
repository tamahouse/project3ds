package logo;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
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

public class Logo extends BaseTest {

	static Map<String, Pair<Integer, Integer>> ruleMap;

	Driver driver;

	String branch = "http://feature-pwl-2036.wallapi.bamboo.stuffio.com";
	String host = AnnotationPage.WallapiUrl.host(branch).generate();

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
	public void buildLogoReport(String shortcode, Map<String, String> excelMap) throws IOException, Exception {
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

				if (name.contains("107x43")) {
					logoMap.put("multi", file);
				} else if (name.contains("149x50")) {
					logoMap.put("uni", file);
				} else if (name.contains("140x45")) {
					logoMap.put("v5", file);
				} else if (name.contains("100x40") && !name.contains("dark")) {
					logoMap.put("light", file);
					if (!logo.equals("")) {
						logoMap.put("lightLogo", file);
					}
				} else if (name.contains("100x40") && name.contains("dark")) {
					logoMap.put("dark", file);
					if (!logo.equals("")) {
						logoMap.put("darkLogo", file);
					}
				} else if (name.contains("200x80") && !name.contains("dark")) {
					logoMap.put("light2", file);
					if (!logo.equals("")) {
						logoMap.put("light2Logo", file);
					}
				} else if (name.contains("200x80") && name.contains("dark")) {
					logoMap.put("dark2", file);
					if (!logo.equals("")) {
						logoMap.put("dark2Logo", file);
					}
				} else if (name.contains("300x120") && !name.contains("dark")) {
					logoMap.put("light3", file);
					if (!logo.equals("")) {
						logoMap.put("light3Logo", file);
					}
				} else if (name.contains("300x120") && name.contains("dark")) {
					logoMap.put("dark3", file);
					if (!logo.equals("")) {
						logoMap.put("dark3Logo", file);
					}
				} else if (name.contains("pm") && !name.contains("big") && !name.contains("merchantareav5")
						&& !name.contains("@2x") && !name.contains("@3x")&& !name.contains("ps")
						&& !name.contains("ma5")) {
					logoMap.put("multi", file);
				} else if (name.contains("big")) {
					logoMap.put("uni", file);
				} else if (name.contains("merchantareav5")) {
					logoMap.put("v5", file);
				} else if (name.contains("@2x")) {
					String sublast2 = name.substring(name.length() - 5, name.length() - 3);
					if (!sublast2.equals("-d")&& !sublast2.equals("_d")) {
						logoMap.put("light2", file);
						if (!logo.equals("")) {
							logoMap.put("light2Logo", file);
						}
					} else {
						logoMap.put("dark2", file);
						if (!logo.equals("")) {
							logoMap.put("dark2Logo", file);
						}
					}
				} else if (name.contains("@3x")) {
					String sublast2 = name.substring(name.length() - 5, name.length() - 3);
					if (!sublast2.equals("-d") && !sublast2.equals("_d")) {
						logoMap.put("light3", file);
						if (!logo.equals("")) {
							logoMap.put("light3Logo", file);
						}
					} else {
						logoMap.put("dark3", file);
						if (!logo.equals("")) {
							logoMap.put("dark3Logo", file);
						}
					}
				} else if (name.contains("ps") && !name.contains("pm") && !name.contains("big")
						&& !name.contains("merchantareav5") && !name.contains("@2x") && !name.contains("@3x")) {
					String sublast2 = name.substring(name.length() - 2, name.length());
					if (sublast2.equals("-d") || name.contains("-d ") || sublast2.equals("_d") || name.contains("_d ")) {
						logoMap.put("dark", file);
						if (!logo.equals("")) {
							logoMap.put("darkLogo", file);
						}
					} else {
						logoMap.put("light", file);
						if (!logo.equals("")) {
							logoMap.put("lightLogo", file);
						}
					}
				} else if (name.contains("v7")) {
					logoMap.put("multi", file);
				} else if (name.contains("v5")) {
					logoMap.put("v5", file);
					
					
				} else if (name.contains("ma5")) {
					logoMap.put("v5", file);
				} else if (name.contains("_d@2x")) {
					logoMap.put("dark2", file);
				} else if (name.contains("_d@3x")) {
					logoMap.put("dark3", file);
				} else if (name.contains("@2x")) {
					logoMap.put("light2", file);
				} else if (name.contains("@3x")) {
					logoMap.put("light3", file);
				} else if (name.contains("_d")) {
					logoMap.put("dark", file);
				} else if (!name.contains("_d")) {
					logoMap.put("light", file);
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
