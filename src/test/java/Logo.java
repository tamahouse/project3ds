import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
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

import automation.project3ds.Assertion;
import automation.project3ds.ExtentManager;
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

	String host = "http://feature-pwl-1986.wallapi.bamboo.stuffio.com";

	private List<Map<String, String>> importData() throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("src\\main\\java\\utility\\logo.xlsx");
		String query = "select * from psname";
		Recordset record = connection.executeQuery(query);
		List<String> names = record.getFieldNames();
		while (record.next()) {
			Map<String, String> map = new HashMap<String, String>();
			for (String name : names) {
				String key = name;
				String value = record.getField(name);
				map.put(key, value);
			}
			list.add(map);
		}
		return list;
	}

	@DataProvider(name = "data")
	public Object[][] data() throws Exception {
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
	public void getLogoFile(String shortcode, Map<String, String> map) throws IOException, Exception {
//		ExtentManager.startTest("logo", shortcode);
		Map<String, File> logoMap = new HashMap<String, File>();
		String folderName = map.get("folder");
		String folderPath = "G:\\My Drive\\PS platform team\\PS logos resized\\" + folderName;
		File folder = new File(folderPath);
		File[] files = folder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".png");
			}
		});
//		Assertion.get().assertEquals(files.length > 8, true, "[NumberImage]" + "[" + files.length + "]");
		for (File file : files) {
			String name = file.getName().toLowerCase().replace(".png", "");
			String called_name = map.get("called_name");
			String first2 = name.substring(0, 2);
			String first3 = name.substring(0, 3);
			String last3 = name.substring(name.length() - 3, name.length());
			String last14 = "";
			try {
				last14 = name.substring(name.length() - 14, name.length());
			} catch (StringIndexOutOfBoundsException ignore) {
			}
			if (name.contains(called_name) && first2.equals("pm") && !last3.equals("big")
					&& !last14.equals("merchantareav5")) {
				logoMap.put("multi", file);
			} else if (name.contains(called_name) && first2.equals("pm") && last3.equals("big")
					&& !last14.equals("merchantareav5")) {
				logoMap.put("uni", file);
			} else if (name.contains(called_name) && first2.equals("pm") && !last3.equals("big")
					&& last14.equals("merchantareav5")) {
				logoMap.put("v5", file);
			} else if (name.contains(called_name) && first2.equals("ps") && !last3.equals("@2x")
					&& !last3.equals("@3x")) {
				String sublast2 = name.substring(name.length() - 2, name.length());
				if (!sublast2.equals("-d")) {
					logoMap.put("light", file);
				} else {
					logoMap.put("dark", file);
				}
			} else if (name.contains(called_name) && first3.equals("_ps") && last3.equals("@2x")
					&& !last3.equals("@3x")) {
				String sublast2 = name.substring(name.length() - 5, name.length() - 3);
				if (!sublast2.equals("-d")) {
					logoMap.put("light2", file);
				} else {
					logoMap.put("dark2", file);
				}
			} else if (name.contains(called_name) && first2.equals("ps") && !last3.equals("@2x")
					&& last3.equals("@3x")) {
				String sublast2 = name.substring(name.length() - 5, name.length() - 3);
				if (!sublast2.equals("-d")) {
					logoMap.put("light3", file);
				} else {
					logoMap.put("dark3", file);
				}
			} else {
				Assertion.get().assertEquals(name, "name", "[NamedWrong]");
				if (first2.equals("ps") && last3.equals("@2x") && !last3.equals("@3x")) {
					String sublast2 = name.substring(name.length() - 5, name.length() - 3);
					if (!sublast2.equals("-d")) {
						logoMap.put("light2", file);
					} else {
						logoMap.put("dark2", file);
					}
				} else if (name.contains("v7")) {
					logoMap.put("multi", file);
				} else if (name.contains("v5")) {
					logoMap.put("v5", file);
				}
			}
		}
//		Assertion.get().assertEquals(files.length >= logoMap.size(), true,"[ImageNotMatchType]"+"["+files.length+"]");

//		if (logoMap.size() != 9) {
//			String missingType = "Missing type: ";
//			for (Entry<String, Pair<Integer, Integer>> entry : ruleMap.entrySet()) {
//				if (logoMap.get(entry.getKey()) == null) {
//					missingType = missingType + " " + entry.getKey();
//				}
//
//			}
//			Assertion.get().assertEquals(logoMap.size(), 9, "[NotEnoughDesign]" + "[" + missingType + "]");
//			ExtentManager.getTest().log(Status.FAIL, missingType);
//		}

		for (Map.Entry<String, File> entry : logoMap.entrySet()) {
			String name = entry.getKey();
			File file = entry.getValue();
			BufferedImage image = ImageIO.read(file);
//			ExtentManager.addImage(image, name +"-"+ file.getName());
			Assertion.get().assertEquals(image.getWidth(), ruleMap.get(name).getKey(), "[" + name + "]" + "[Width]");
			Assertion.get().assertEquals(image.getHeight(), ruleMap.get(name).getValue(),
					"[" + name + "]" + "[Height]");

		}

		Map<String, String> hostName = new HashMap<String, String>();
		hostName.put("multi", null);
		hostName.put("uni", null);
		hostName.put("v5", null);
		hostName.put("light", "/images/ps_logos/v2/" + shortcode + ".png");
		hostName.put("light2", "/images/ps_logos/v2/" + shortcode + "@2x.png");
		hostName.put("light3", "/images/ps_logos/v2/" + shortcode + "@3x.png");
		hostName.put("dark", "/images/ps_logos/v2/" + shortcode + "_d.png");
		hostName.put("dark2", "/images/ps_logos/v2/" + shortcode + "_d@2x.png");
		hostName.put("dark3", "/images/ps_logos/v2/" + shortcode + "_d@3x.png");

		for (Map.Entry<String, String> entry : hostName.entrySet()) {
			String name = entry.getKey();
			File file = logoMap.get(name);
			BufferedImage actualImage = null;
			BufferedImage expectedImage = null;
			try {
			expectedImage = ImageIO.read(file);
			}catch(IllegalArgumentException e) {
				Assertion.get().assertEquals(name, "isMissing", "[NotEnoughDesign]");
				int width = ruleMap.get(name).getKey();
				int height = ruleMap.get(name).getValue();
				expectedImage = generateBlankImage(width, height);
			}
			try {

				URL url = new URL(host + hostName.get(name));
				System.out.println(url);
				actualImage = ImageIO.read(url);
				BufferedImage combined = combineImage(expectedImage, actualImage);
				ExtentManager.addImage(combined, name);
			} catch (IIOException e) {
				if(!"multi uni v5".contains(name)) {
				Assertion.get().assertEquals(name, "", "[ImageNotReplaced]");
				}
				actualImage = generateBlankImage(expectedImage.getWidth(), expectedImage.getWidth());
				BufferedImage combined = combineImage(expectedImage, actualImage);
				ExtentManager.addImage(combined, name);
				}
		}

		Assertion.end();

	}
	
	private static BufferedImage generateBlankImage(int width, int height) {
		BufferedImage actualImage = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = actualImage.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0,width , height );
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

}
