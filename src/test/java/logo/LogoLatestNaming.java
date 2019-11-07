package logo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.BaseTest;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.LogoReportBuilder;
import automation.project3ds.DBWallapi;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetTerminal;
import automation.project3ds.WidgetUni;
import javafx.util.Pair;

@Listeners(automation.project3ds.ExtentListener.class)
public class LogoLatestNaming extends BaseTest{

	static Map<String, Pair<Integer, Integer>> ruleMap;


	static String branch = "http://feature-pwl-2035.wallapi.bamboo.stuffio.com";
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
		driver = new Driver();
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
				} else if (name.equals("pm_" + called_name + "_big")) {
					logoMap.put("uni", file);
				} else if (name.equals("pm_" + called_name + "_ma5")) {
					logoMap.put("v5", file);
				} else if (name.equals(called_name)) {
					logoMap.put("light", file);
					if (!logo.equals("")) {
						logoMap.put("lightLogo", file);
					}
				} else if (name.equals(called_name + "@2x")) {
					logoMap.put("light2", file);
					if (!logo.equals("")) {
						logoMap.put("light2Logo", file);
					}
				} else if (name.equals(called_name + "@3x")) {
					logoMap.put("light3", file);
					if (!logo.equals("")) {
						logoMap.put("light3Logo", file);
					}
				} else if (name.equals(called_name + "_d")) {
					logoMap.put("dark", file);
					if (!logo.equals("")) {
						logoMap.put("darkLogo", file);
					}
				} else if (name.equals(called_name + "_d@2x")) {
					logoMap.put("dark2", file);
					if (!logo.equals("")) {
						logoMap.put("dark2Logo", file);
					}
				} else if (name.equals(called_name + "_d@3x")) {
					logoMap.put("dark3", file);
					if (!logo.equals("")) {
						logoMap.put("dark3Logo", file);
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
