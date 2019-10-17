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
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.DBWallapi;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;
import automation.project3ds.WidgetTerminal;
import automation.project3ds.WidgetUni;
import javafx.util.Pair;

public class Logo {

	static Map<String, Pair<Integer, Integer>> ruleMap;

	Driver driver;

	String branch = "http://feature-pwl-2033.wallapi.bamboo.stuffio.com";
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

		Login.login(host);
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
		String logo = excelMap.get("logo");
		ruleMap = new HashMap<String, Pair<Integer, Integer>>();
		ruleMap.put("multi", new Pair<>(107, 43));
		ruleMap.put("uni", new Pair<>(149, 50));
		ruleMap.put("v5", new Pair<>(140, 45));
		ruleMap.put("light", new Pair<>(100, 40));
		ruleMap.put("light2", new Pair<>(200, 80));
		ruleMap.put("light3", new Pair<>(300, 120));
		ruleMap.put("dark", new Pair<>(100, 40));
		ruleMap.put("dark2", new Pair<>(200, 80));
		ruleMap.put("dark3", new Pair<>(300, 120));

		if (!logo.equals("")) {
			ruleMap.put("lightLogo", new Pair<>(100, 40));
			ruleMap.put("light2Logo", new Pair<>(200, 80));
			ruleMap.put("light3Logo", new Pair<>(300, 120));
			ruleMap.put("darkLogo", new Pair<>(100, 40));
			ruleMap.put("dark2Logo", new Pair<>(200, 80));
			ruleMap.put("dark3Logo", new Pair<>(300, 120));
		}
		Map<String, File> logoMap = this.distributeImageIntoType(excelMap);
		Map<String, BufferedImage> expectedImages = this.checkMissingDesign(logoMap);
		this.checkImageSize("Design", expectedImages);
		Map<String, BufferedImage> actualImages = this.checkMissingActualImage(excelMap, logoMap);
		this.checkImageSize("Actual", actualImages);
		this.combineImages(actualImages, expectedImages);
		Assertion.end();

	}

	private void combineImages(Map<String, BufferedImage> actualImages, Map<String, BufferedImage> expectedImages)
			throws Exception {
		for (Map.Entry<String, BufferedImage> entry : actualImages.entrySet()) {
			String name = entry.getKey();
			String newname = null;
			BufferedImage actualImage = entry.getValue();
			if (name.contains("multi")) {
				newname = "multi";
			} else {
				newname = name;
			}
			BufferedImage expectedImage = expectedImages.get(newname);
			BufferedImage combined = combineImage(expectedImage, actualImage);
			ExtentManager.addImage(combined, name);
		}
	}

	private Map<String, BufferedImage> checkMissingActualImage(Map<String, String> excelMap, Map<String, File> logoMap)
			throws Exception {
		String co_id = excelMap.get("co_id");
		String shortcode = excelMap.get("shortcode");
		String ps_name = excelMap.get("ps_name");
		String ps_id= excelMap.get("ps_id");
		String logo = excelMap.get("logo");
		String a_id;
		if (shortcode.contains("gateway") && !ps_name.contains("Amex")) {
			a_id = "99889";
		} else {
			a_id = "99894";
		}

		String urlShortcode = shortcode;
		if (ps_name.contains("Amex") && shortcode.equals("gateway")) {
			urlShortcode = "gateway_amex";
		}
		Map<String, String> hostName = new HashMap<String, String>();

		hostName.put("light", "/images/ps_logos/v2/" + urlShortcode + ".png");
		hostName.put("light2", "/images/ps_logos/v2/" + urlShortcode + "@2x.png");
		hostName.put("light3", "/images/ps_logos/v2/" + urlShortcode + "@3x.png");
		hostName.put("dark", "/images/ps_logos/v2/" + urlShortcode + "_d.png");
		hostName.put("dark2", "/images/ps_logos/v2/" + urlShortcode + "_d@2x.png");
		hostName.put("dark3", "/images/ps_logos/v2/" + urlShortcode + "_d@3x.png");

		if (!logo.equals("")) {
			hostName.put("lightLogo", "/images/ps_logos/v2/" + logo + ".png");
			hostName.put("light2Logo", "/images/ps_logos/v2/" + logo + "@2x.png");
			hostName.put("light3Logo", "/images/ps_logos/v2/" + logo + "@3x.png");
			hostName.put("darkLogo", "/images/ps_logos/v2/" + logo + "_d.png");
			hostName.put("dark2Logo", "/images/ps_logos/v2/" + logo + "_d@2x.png");
			hostName.put("dark3Logo", "/images/ps_logos/v2/" + logo + "_d@3x.png");
		}

		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		for (Entry<String, Pair<Integer, Integer>> entry : ruleMap.entrySet()) {

			String name = entry.getKey();
			File file = logoMap.get(name);
			if (file == null) {
				BufferedImage actualImage = generateBlankImage(name, ruleMap.get(name).getKey(), ruleMap.get(name).getValue());
				map.put(name, actualImage);
			} else {

				if (!"multi uni v5".contains(name)) {
					String host = branch + hostName.get(name);
					URL url = new URL(host);
					System.out.println(url);
					ExtentManager.logInfo(host);
					BufferedImage actualImage = ImageIO.read(url);
					map.put(name, actualImage);
					if((!name.contains("Logo") && ruleMap.get("light2Logo") == null) || (name.contains("Logo")&& ruleMap.get("light2Logo") != null)) {
						checkTerminal(map, shortcode, logo, name, hostName, co_id, a_id);
					}
				} else if (name.equals("multi")) {
					checkMulti(map, shortcode, co_id, a_id);
				} else if (name.equals("uni")) {
					checkUni(map, shortcode, co_id, a_id);
				} else if (name.equals("v5")) {
					checkV5(map, ps_id, a_id);
				}
			}

		}
		return map;
	}

	private Map<String, BufferedImage> checkMulti(Map<String, BufferedImage> map, String shortcode, String co_id,
			String a_id) throws Exception {
		try {
			String host = AnnotationPage.WallapiUrl.host(branch).a_id(a_id).widget("p1").co_id(co_id).isCustom().generate();
			WidgetMulti.get(host);
			String imageUrl = WidgetMulti.getLogoUrl(shortcode);
			ExtentManager.addScreenshot("multi p1");
			URL url = new URL(imageUrl);
			BufferedImage actualImage = ImageIO.read(url);
			map.put("multi p1", actualImage);
		} catch (IIOException e) {
			ExtentManager.addScreenshot("multi p1");
			Assertion.get().assertEquals("-", "", "[NotActualReplaced]" + "[multi P1]");
			BufferedImage actualImage = generateBlankImage("multi p1", ruleMap.get("multi").getKey(),
					ruleMap.get("multi").getValue());
			map.put("multi p1", actualImage);
		}

		try {
			String host = AnnotationPage.WallapiUrl.host(branch).a_id(a_id).widget("p10").co_id(co_id).isCustom().generate();
			WidgetMulti.get(host);
			String imageUrl = WidgetMulti.getLogoUrl(shortcode);
			ExtentManager.addScreenshot("multi p10");
			URL url = new URL(imageUrl);
			BufferedImage actualImage = ImageIO.read(url);
			map.put("multi p1", actualImage);
		} catch (IIOException e) {
			ExtentManager.addScreenshot("multi p10");
			Assertion.get().assertEquals("-", "", "[NotActualReplaced]" + "[multi P1]");
			BufferedImage actualImage = generateBlankImage("multi p1", ruleMap.get("multi").getKey(),
					ruleMap.get("multi").getValue());
			map.put("multi p1", actualImage);
		}
		return map;
	}

	private Map<String, BufferedImage> checkUni(Map<String, BufferedImage> map, String shortcode, String co_id,
			String a_id) throws Exception {

		try {
			String host = AnnotationPage.WallapiUrl.host(branch).a_id(a_id).uni(shortcode).co_id(co_id).isCustom().generate();
			WidgetUni.get(host);
			String imageUrl = WidgetUni.getLogoUrl(shortcode);
			ExtentManager.addScreenshot("uni");
			URL url = new URL(imageUrl);
			BufferedImage actualImage = ImageIO.read(url);
			map.put("uni", actualImage);
		} catch (IIOException e) {
			ExtentManager.addScreenshot("uni");
			Assertion.get().assertEquals("-", "", "[NotActualReplaced]" + "[uni]");
			BufferedImage actualImage = generateBlankImage("uni", ruleMap.get("uni").getKey(),
					ruleMap.get("uni").getValue());
			map.put("uni", actualImage);
		}

		return map;
	}

	private Map<String, BufferedImage> checkV5(Map<String, BufferedImage> map, String ps_id, String a_id)
			throws Exception {

		try {
			String host = branch + "/admin/developers/login?id=86844&admin_login=true";
			driver = AnnotationPage.getDriver();
			driver.get(host);
			driver.getCurrentUrl("wallapi");
			driver.get(branch + "/developers/applications/paymentsystems/?id=" + a_id);
			Element image = driver.getElement(By.xpath("//div[./*[@id='" + ps_id + "_active']]//img"));
			image.moveToTopView();
			ExtentManager.addScreenshot("v5");
			String imageUrl = image.getAttribute("src");
			URL url = new URL(imageUrl);
			BufferedImage actualImage = ImageIO.read(url);
			map.put("v5", actualImage);
		} catch (IIOException e) {
			ExtentManager.addScreenshot("v5");
			Assertion.get().assertEquals("-", "", "[NotActualReplaced]" + "[v5]");
			BufferedImage actualImage = generateBlankImage("v5", ruleMap.get("v5").getKey(),
					ruleMap.get("v5").getValue());
			map.put("v5", actualImage);
		}

		return map;
	}
	
	private Map<String, BufferedImage> checkTerminal(Map<String, BufferedImage> map, String shortcode, String logo,  String name, Map<String, String> hostName, String co_id, String a_id)
			throws Exception {
		try {

			if (name.contains("light2")) {
				String hostTerminal = AnnotationPage.WallapiUrl.host(branch).co_id(co_id).a_id(a_id).t3().isCustom().generate();
				WidgetTerminal.get(hostTerminal);
				Thread.sleep(2000);
				WidgetTerminal.clickPaymentMethod(shortcode, logo);
				Thread.sleep(1000);
				WidgetTerminal.getFrame().getElement(By.xpath("//img[@src='" + branch + hostName.get(name) + "']"));
				ExtentManager.addScreenshot(name);
			} else if (name.contains("dark2")) {
				String hostTerminal = AnnotationPage.WallapiUrl.host(branch).co_id(co_id).a_id(a_id).t3().isDark().isCustom().generate();
				WidgetTerminal.get(hostTerminal);
				Thread.sleep(2000);
				WidgetTerminal.clickPaymentMethod(shortcode, logo);
				Thread.sleep(1000);
				WidgetTerminal.getFrame().getElement(By.xpath("//img[@src='" + branch + hostName.get(name) + "']"));
				ExtentManager.addScreenshot(name);
			}
		} catch (IIOException e) {
			Assertion.get().assertEquals(host + hostName.get(name), "", "[NotActualReplaced]" + "[" + name + "]");
			BufferedImage actualImage = generateBlankImage(name, ruleMap.get(name).getKey(),
					ruleMap.get(name).getValue());
			map.put(name, actualImage);
		}
		return map;
	}

	private void checkImageSize(String title, Map<String, BufferedImage> map) throws IOException {
		for (Map.Entry<String, BufferedImage> entry : map.entrySet()) {
			String name = entry.getKey();
			if (name.contains("multi")) {
				name = "multi";
			}
			BufferedImage image = entry.getValue();
			Boolean x = false;
			if (image.getWidth() == ruleMap.get(name).getKey() && image.getHeight() == ruleMap.get(name).getValue()) {
				x = true;
			}
			if (image.getWidth() == (ruleMap.get(name).getKey() + 1)
					&& image.getHeight() == (ruleMap.get(name).getValue() + 1)) {
				x = true;
			}
			if (x == false) {
				Assertion.get().assertEquals(image.getWidth(), ruleMap.get(name).getKey(),
						"[" + title + "Width]" + "[" + name + "]");
				Assertion.get().assertEquals(image.getHeight(), ruleMap.get(name).getValue(),
						"[" + title + "Height]" + "[" + name + "]");
			}

		}
	}

	private Map<String, BufferedImage> checkMissingDesign(Map<String, File> logoMap) throws Exception {
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		String missing = "Missing design: ";
		for (Entry<String, Pair<Integer, Integer>> entry : ruleMap.entrySet()) {
			String name = entry.getKey();
			File file = logoMap.get(name);
			BufferedImage expectedImage = null;
			try {
				expectedImage = ImageIO.read(file);
			} catch (IllegalArgumentException e) {
//				Assertion.get().assertEquals("", "png", "[NotEnoughDesign]"+"["+name+"]");
				missing = missing + "," + name;
				int width = ruleMap.get(name).getKey();
				int height = ruleMap.get(name).getValue();
				expectedImage = generateBlankImage(name, width, height);
			}
			map.put(name, expectedImage);

		}
		if (!missing.equals("Missing design: ,uni,multi,v5") && !missing.equals("Missing design: ")) {
			Assertion.get().assertEquals("", "png", "[NotEnoughDesign]" + "[" + missing + "]");
			ExtentManager.getTest().debug(missing);
		}
		return map;
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

	private static BufferedImage generateBlankImage(String name, int width, int height) {
		BufferedImage actualImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = actualImage.createGraphics();
		if (name.contains("dark")) {
			g2d.setColor(Color.decode("#071d28"));
		} else {
			g2d.setColor(Color.WHITE);
		}
		g2d.fillRect(0, 0, width, height);
		return actualImage;
	}

	private static BufferedImage combineImage(BufferedImage expectedImage, BufferedImage actualImage)
			throws IOException {

		int w = Math.max(expectedImage.getWidth(), actualImage.getWidth());
		int h = expectedImage.getHeight() + actualImage.getHeight();
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics g = combined.getGraphics();
		g.drawImage(expectedImage, 0, 0, null);
		g.drawImage(actualImage, 0, expectedImage.getHeight(), null);

		return combined;
	}

	private void printCountryTable() throws Exception {

		String project_id = "99894";
		List<Map<String, String>> countryByPs = DBWallapi.getCountryByPSID(project_id);
		List<Map<String, String>> psname = this.importData();
		for (Map<String, String> excel : psname) {
			Boolean x = false;
			String ps_id_excel = excel.get("ps_id");
			String shortcode = excel.get("shortcode");
			for (Map<String, String> db : countryByPs) {
				String ps_id_db = db.get("ps_id");
				if (ps_id_excel.equals(ps_id_db)) {
					String co_id = db.get("co_id");
					String co_name = db.get("co_name");
					System.out.println(ps_id_excel + "+" + shortcode + "+" + co_id + "+" + co_name);
					x = true;
					break;
				}
			}
			if (x == false) {
				System.out.println(ps_id_excel);
			}
		}
	}

}
