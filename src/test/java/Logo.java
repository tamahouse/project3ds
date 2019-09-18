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
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.Wallapi;
import automation.project3ds.WidgetMainFrame;
import javafx.util.Pair;

public class Logo {

	static Map<String, Pair<Integer, Integer>> ruleMap = new HashMap<String, Pair<Integer, Integer>>();
	static {
		ruleMap.put("multi", new Pair<>(107, 43));
		ruleMap.put("uni", new Pair<>(149, 50));
		ruleMap.put("v5", new Pair<>(140, 45));
		ruleMap.put("light", new Pair<>(100, 40));
		ruleMap.put("light2", new Pair<>(200, 80));
		ruleMap.put("light3", new Pair<>(300, 120));
		ruleMap.put("dark", new Pair<>(100, 40));
		ruleMap.put("dark2", new Pair<>(200, 80));
		ruleMap.put("dark3", new Pair<>(300, 120));
	}

	Driver driver;

	String host = "http://feature-pwl-1986.wallapi.bamboo.stuffio.com";
//	String featureBranchOther = "http://feature-pwl-1986.t3payments.bamboo.stuffio.com";
	String featureBranchOther = "http://develop.wallapi.bamboo.stuffio.com";
	
//	String	host = "http://feature-pwg-1129.wallapi.bamboo.stuffio.com";
//	String featureBranchOther = "http://feature-pwg-1129.wallapi.bamboo.stuffio.com";
	String project_id = "99894";
	String hostOther = featureBranchOther+ "/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894";

	
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
//		String hostP1 = "http://feature-pwl-1986.wallapi.bamboo.stuffio.com" + "/admin/test-offerwall?_application_name=1201+test+%28Payment%29%5B99889%5D&data%5Ba_id%5D=99889"
//				+ "&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=21806921d21d2&data%5Bps%5D=mercadopago";
//		Login.login(hostP1);
//		String hostP2 = "http://feature-pwg-1129.wallapi.bamboo.stuffio.com" + "/admin/test-offerwall?_application_name=1201+test+%28Payment%29%5B99889%5D&data%5Ba_id%5D=99889"
//				+ "&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=21806921d21d2&data%5Bps%5D=mercadopago";
//		Login.login(hostP2);
//		String hostP1 = "http://develop.wallapi.bamboo.stuffio.com" + "/admin/test-offerwall?_application_name=1201+test+%28Payment%29%5B99889%5D&data%5Ba_id%5D=99889"
//		+ "&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=21806921d21d2&data%5Bps%5D=mercadopago";
		
//		**Host test bank transfer thailand pwl-1994
//		String hostP1 = "http://feature-pwl-1994.wallapi.bamboo.stuffio.com" + "/admin/test-offerwall?_application_name=1201+test+%28Payment%29%5B99889%5D&data%5Ba_id%5D=99889"
//		+ "&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=21806921d21d2&data%5Bps%5D=mercadopago";
		
//		**Host test btue pwl-1996
		String hostP1 = "http://feature-pwl-1996.wallapi.bamboo.stuffio.com" + "/admin/test-offerwall?_application_name=1201+test+%28Payment%29%5B99889%5D&data%5Ba_id%5D=99889"
		+ "&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=21806921d21d2&data%5Bps%5D=mercadopago";
		
		Login.login(hostP1);
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
			}else {
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
		String ps_id = excelMap.get("ps_id");
		String ps_name = excelMap.get("ps_name");
		if(shortcode.contains("gateway") && !ps_name.contains("Amex")) {
//			host = "http://feature-pwg-1129.wallapi.bamboo.stuffio.com";
//			featureBranchOther = "http://feature-pwg-1129.wallapi.bamboo.stuffio.com";
//			hostOther = featureBranchOther + "/admin/test-offerwall?_application_name=1201+test+%28Payment%29%5B99889%5D&data%5Ba_id%5D=99889";
//			project_id = "99889";
			
//			host = "http://develop.wallapi.bamboo.stuffio.com";
//			featureBranchOther = "http://develop.wallapi.bamboo.stuffio.com";
//			hostOther = featureBranchOther + "/admin/test-offerwall?_application_name=1201+test+%28Payment%29%5B99889%5D&data%5Ba_id%5D=99889";
//			project_id = "99889";
			
			host = "http://feature-pwl-1996.wallapi.bamboo.stuffio.com";
			featureBranchOther = "http://feature-pwl-1996.wallapi.bamboo.stuffio.com";
			hostOther = featureBranchOther + "/admin/test-offerwall?_application_name=1201+test+%28Payment%29%5B99889%5D&data%5Ba_id%5D=99889";
			project_id = "99889";
		}else if("200 19 132".contains(ps_id)) {
//			host = "http://feature-pwg-1129.wallapi.bamboo.stuffio.com";
//			featureBranchOther = "http://feature-pwg-1129.wallapi.bamboo.stuffio.com";
//			project_id = "99894";
//			hostOther = featureBranchOther+ "/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894";
//			
//			host = "http://develop.wallapi.bamboo.stuffio.com";
//			featureBranchOther = "http://develop.wallapi.bamboo.stuffio.com";
//			project_id = "99894";
//			hostOther = featureBranchOther+ "/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894";
		
			host = "http://feature-pwl-1996.wallapi.bamboo.stuffio.com";
			featureBranchOther = "http://feature-pwl-1996.wallapi.bamboo.stuffio.com";
			project_id = "99894";
			hostOther = featureBranchOther+ "/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894";
		
		}else {
//			host = "http://feature-pwl-1986.wallapi.bamboo.stuffio.com";
//			featureBranchOther = "http://feature-pwl-1986.t3payments.bamboo.stuffio.com";
//			project_id = "99894";
//			hostOther = featureBranchOther+ "/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894";
//			
//			host = "http://develop.wallapi.bamboo.stuffio.com";
//			featureBranchOther = "http://develop.wallapi.bamboo.stuffio.com";
//			project_id = "99894";
//			hostOther = featureBranchOther+ "/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894";
			
			
			host = "http://feature-pwl-1996.wallapi.bamboo.stuffio.com";
			featureBranchOther = "http://feature-pwl-1996.wallapi.bamboo.stuffio.com";
			project_id = "99894";
			hostOther = featureBranchOther+ "/admin/test-offerwall?_application_name=Ajingon+%28Payment%29%5B99894%5D&data%5Ba_id%5D=99894";
			
		}
		
		String urlShortcode = shortcode;
		if(ps_name.contains("Amex") && shortcode.equals("gateway") ) {
			urlShortcode = "gateway_amex";
		}
		Map<String, String> hostName = new HashMap<String, String>();
		hostName.put("multi p1", hostOther
				+ "&data%5Bwidget%5D=p1&data%5Bco_id%5D="
				+ co_id + "&data%5Buid%5D=21806921d21d2&data%5Bps%5D=mercadopago");
		hostName.put("multi p10", hostOther
				+ "&data%5Bwidget%5D=p10&data%5Bco_id%5D="
				+ co_id + "&data%5Buid%5D=21806921d21d2&data%5Bps%5D=mercadopago");
		hostName.put("uni", hostOther
				+ "&data%5Bwidget%5D=p2&data%5Bco_id%5D="
				+ co_id + "&data%5Buid%5D=21806921d21d2&data%5Bps%5D=" + shortcode);
		hostName.put("v5",
				featureBranchOther + "/admin/developers/login?id=86844&admin_login=true");
		hostName.put("light", "/images/ps_logos/v2/" + urlShortcode + ".png");
		hostName.put("light2", "/images/ps_logos/v2/" + urlShortcode + "@2x.png");
		hostName.put("light3", "/images/ps_logos/v2/" + urlShortcode + "@3x.png");
		hostName.put("dark", "/images/ps_logos/v2/" + urlShortcode + "_d.png");
		hostName.put("dark2", "/images/ps_logos/v2/" + urlShortcode + "_d@2x.png");
		hostName.put("dark3", "/images/ps_logos/v2/" + urlShortcode + "_d@3x.png");

		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		for (Entry<String, Pair<Integer, Integer>> entry : ruleMap.entrySet()) {

			String name = entry.getKey();
			File file = logoMap.get(name);

			BufferedImage actualImage = null;
			String hostP1 = null;
//			Map<Boolean, String> branchMap = new HashMap<Boolean, String>();
//			branchMap.put(true, "p1");
//			branchMap.put(false, "p10");
			Boolean reLoop = false;
			String newName = null;
			URL url = null;
			if (file == null) {
				actualImage = generateBlankImage(ruleMap.get(name).getKey(), ruleMap.get(name).getValue());
				map.put(name, actualImage);
			} else {
				try {

					Boolean start = true;
					while (start == true || reLoop == true) {
						start = false;

						if (!"multi uni v5".contains(name)) {
							newName = name;
							hostP1 = host + hostName.get(name);
							url = new URL(hostP1);
							System.out.println(url);
							ExtentManager.logInfo(hostP1);
						} else if (name.equals("multi")) {

							if (reLoop == false) {
								reLoop = true;
								newName = "multi p1";
							} else {
								reLoop = false;
								newName = "multi p10";
							}
							if (!co_id.equals("")) {
								hostP1 = hostName.get(newName);
								driver = AnnotationPage.getDriver();
								driver.get(hostP1);
								String type = newName;
								String tab_id = excelMap.get("p1");
								String imageUrl = WidgetMainFrame.getLogoUrl(type, tab_id);
								url = new URL(imageUrl);
							}
						} else if (name.equals("uni")) {
							newName = name;
							if (!co_id.equals("")) {
								hostP1 = hostName.get(name);
								driver = AnnotationPage.getDriver();
								driver.get(hostP1);
								String type = name;
								String imageUrl = WidgetMainFrame.getLogoUrl(type, null);
								url = new URL(imageUrl);
							}
						} else if (name.equals("v5")) {
							newName = name;
							hostP1 = hostName.get(name);
							driver = AnnotationPage.getDriver();
							driver.get(hostP1);
							driver.getCurrentUrl("wallapi");
							driver.get(
									host + "/developers/applications/paymentsystems/?id=" + project_id);
							String type = name;
							String imageUrl = WidgetMainFrame.getLogoUrl(type, ps_id);
							url = new URL(imageUrl);
						}
						actualImage = ImageIO.read(url);
						map.put(newName, actualImage);
					}
				} catch (IIOException e) {
					Assertion.get().assertEquals(url, "", "[NotActualReplaced]" + "[" + newName + "]");
					actualImage = generateBlankImage(ruleMap.get(name).getKey(), ruleMap.get(name).getValue());
					map.put(newName, actualImage);
				}
				
			}

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
				expectedImage = generateBlankImage(width, height);
			}
			map.put(name, expectedImage);
		}
		if (!missing.equals("Missing design: ,uni,multi,v5") && !missing.equals("Missing design: ")) {
			Assertion.get().assertEquals("", "png", "[NotEnoughDesign]"+"["+missing+"]");
			ExtentManager.getTest().debug(missing);
		}
		return map;
	}

	private Map<String, File> distributeImageIntoType(Map<String, String> excelMap) {
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
				} else if (name.contains("100x40") && name.contains("dark")) {
					logoMap.put("dark", file);
				} else if (name.contains("200x80") && !name.contains("dark")) {
					logoMap.put("light2", file);
				} else if (name.contains("200x80") && name.contains("dark")) {
					logoMap.put("dark2", file);
				} else if (name.contains("300x120") && !name.contains("dark")) {
					logoMap.put("light3", file);
				} else if (name.contains("300x120") && name.contains("dark")) {
					logoMap.put("dark3", file);
				}else if (name.contains("pm") && !name.contains("big") && !name.contains("merchantareav5")
						&& !name.contains("@2x") && !name.contains("@3x")) {
					logoMap.put("multi", file);
				} else if (name.contains("big")) {
					logoMap.put("uni", file);
				} else if (name.contains("merchantareav5")) {
					logoMap.put("v5", file);
				} else if (name.contains("@2x")) {
					String sublast2 = name.substring(name.length() - 5, name.length() - 3);
					if (!sublast2.equals("-d")) {
						logoMap.put("light2", file);
					} else {
						logoMap.put("dark2", file);
					}
				} else if (name.contains("@3x")) {
					String sublast2 = name.substring(name.length() - 5, name.length() - 3);
					if (!sublast2.equals("-d")) {
						logoMap.put("light3", file);
					} else {
						logoMap.put("dark3", file);
					}
				} else if (name.contains("ps") && !name.contains("pm") && !name.contains("big")
						&& !name.contains("merchantareav5") && !name.contains("@2x") && !name.contains("@3x")) {
					String sublast2 = name.substring(name.length() - 2, name.length());
					if (sublast2.equals("-d") || name.contains("-d ")) {
						logoMap.put("dark", file);
					} else {
						logoMap.put("light", file);
					}
				} else if (name.contains("v7")) {
					logoMap.put("multi", file);
				} else if (name.contains("v5")) {
					logoMap.put("v5", file);
				} else {
					Assertion.get().assertEquals(name, "name", "[NotInType]");
				}
			} else {
				Assertion.get().assertEquals(name, "name", "[NamedWrong]");
			}
		}
		return logoMap;

	}

//	private static Map<String, File> oldFilter(Map<String, String> excelMap) {
//		String folderName = excelMap.get("folder");
//		String folderPath = "G:\\My Drive\\PS platform team\\PS logos resized\\" + folderName;
//		File folder = new File(folderPath);
//		File[] files = folder.listFiles(new FilenameFilter() {
//			public boolean accept(File dir, String name) {
//				return name.toLowerCase().endsWith(".png");
//			}
//		});
//		Map<String, File> logoMap = new HashMap<String, File>();
//		for (File file : files) {
//			String name = file.getName().toLowerCase().replace(".png", "");
//			String called_name = excelMap.get("called_name");
//			String first2 = "";
//			String first3 = "";
//			String last3 = "";
//			String last14 = "";
//			try {
//				first2 = name.substring(0, 2);
//				first3 = name.substring(0, 3);
//				last3 = name.substring(name.length() - 3, name.length());
//				last14 = name.substring(name.length() - 14, name.length());
//			} catch (StringIndexOutOfBoundsException ignore) {
//			}
//			if (name.contains(called_name) && first2.equals("pm") && !last3.equals("big")
//					&& !last14.equals("merchantareav5")) {
//				logoMap.put("multi", file);
//			} else if (name.contains(called_name) && first2.equals("pm") && last3.equals("big")
//					&& !last14.equals("merchantareav5")) {
//				logoMap.put("uni", file);
//			} else if (name.contains(called_name) && first2.equals("pm") && !last3.equals("big")
//					&& last14.equals("merchantareav5")) {
//				logoMap.put("v5", file);
//			} else if (name.contains(called_name) && first2.equals("ps") && !last3.equals("@2x")
//					&& !last3.equals("@3x")) {
//				String sublast2 = name.substring(name.length() - 2, name.length());
//				if (!sublast2.equals("-d")) {
//					logoMap.put("light", file);
//				} else {
//					logoMap.put("dark", file);
//				}
//			} else if (name.contains(called_name) && first3.equals("_ps") && last3.equals("@2x")
//					&& !last3.equals("@3x")) {
//				String sublast2 = name.substring(name.length() - 5, name.length() - 3);
//				if (!sublast2.equals("-d")) {
//					logoMap.put("light2", file);
//				} else {
//					logoMap.put("dark2", file);
//				}
//			} else if (name.contains(called_name) && first2.equals("ps") && !last3.equals("@2x")
//					&& last3.equals("@3x")) {
//				String sublast2 = name.substring(name.length() - 5, name.length() - 3);
//				if (!sublast2.equals("-d")) {
//					logoMap.put("light3", file);
//				} else {
//					logoMap.put("dark3", file);
//				}
//			} else {
//				Assertion.get().assertEquals(name, "name", "[NamedWrong]");
//				if (first2.equals("ps") && last3.equals("@2x") && !last3.equals("@3x")) {
//					String sublast2 = name.substring(name.length() - 5, name.length() - 3);
//					if (!sublast2.equals("-d")) {
//						logoMap.put("light2", file);
//					} else {
//						logoMap.put("dark2", file);
//					}
//				} else if (first2.equals("ps") && !last3.equals("@2x") && last3.equals("@3x")) {
//					String sublast2 = name.substring(name.length() - 5, name.length() - 3);
//					if (!sublast2.equals("-d")) {
//						logoMap.put("light3", file);
//					} else {
//						logoMap.put("dark3", file);
//					}
//				} else if (first2.equals("pm") && last3.equals("big") && !last14.equals("merchantareav5")) {
//					logoMap.put("uni", file);
//				} else if (first2.equals("pm") && !last3.equals("big") && last14.equals("merchantareav5")) {
//					logoMap.put("v5", file);
//				} else if (first2.equals("ps") && !last3.equals("@2x") && !last3.equals("@3x")) {
//					String sublast2 = name.substring(name.length() - 2, name.length());
//					if (!sublast2.equals("-d")) {
//						logoMap.put("light", file);
//					} else {
//						logoMap.put("dark", file);
//					}
//				} else if (first2.equals("pm") && !last3.equals("big") && !last14.equals("merchantareav5")) {
//					logoMap.put("multi", file);
//
//				} else if (name.contains("v7")) {
//					logoMap.put("multi", file);
//				} else if (name.contains("v5")) {
//					logoMap.put("v5", file);
//				} else if (name.contains("107x43")) {
//					logoMap.put("multi", file);
//				} else if (name.contains("149x50")) {
//					logoMap.put("uni", file);
//				} else if (name.contains("140x45")) {
//					logoMap.put("v5", file);
//				} else if (name.contains("100x40") && !name.contains("dark")) {
//					logoMap.put("light", file);
//				} else if (name.contains("100x40") && name.contains("dark")) {
//					logoMap.put("dark", file);
//				} else if (name.contains("200x80") && !name.contains("dark")) {
//					logoMap.put("light2", file);
//				} else if (name.contains("200x80") && name.contains("dark")) {
//					logoMap.put("dark2", file);
//				} else if (name.contains("300x120") && !name.contains("dark")) {
//					logoMap.put("light3", file);
//				} else if (name.contains("300x120") && name.contains("dark")) {
//					logoMap.put("dark3", file);
//				}
//			}
//		}
//		return logoMap;
//	}

	private static BufferedImage generateBlankImage(int width, int height) {
		BufferedImage actualImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = actualImage.createGraphics();
		g2d.setColor(Color.WHITE);
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
		List<Map<String, String>> countryByPs = Wallapi.getCountryByPSID(project_id);
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
