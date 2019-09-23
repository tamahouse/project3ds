package logo;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import automation.project3ds.ExtentManager;
import javafx.util.Pair;

public class LogoImageCompare {
	
	public static void createPngImage(BufferedImage image, String fileName) throws IOException {
		ImageIO.write(image, "png", new File(fileName));
	}

	public static void createJpgImage(BufferedImage image, String fileName) throws IOException {
		ImageIO.write(image, "jpg", new File(fileName));
	}
	
	public static BufferedImage combineImage(BufferedImage expectedImage, BufferedImage actualImage)
			throws IOException {
	
		int w = Math.max(expectedImage.getWidth(), actualImage.getWidth());
		int h = expectedImage.getHeight() + actualImage.getHeight();
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = combined.getGraphics();
		g.drawImage(expectedImage, 0, 0, null);
		g.drawImage(actualImage, 0, expectedImage.getHeight(), null);

		
		return combined;
	}

//	public static void compareWithBaseImage(File baseImage, File compareImage, String resultOfComparison)
//			throws IOException {
//		BufferedImage bImage = ImageIO.read(baseImage);
//		BufferedImage cImage = ImageIO.read(compareImage);
//		int height = bImage.getHeight();
//		int width = bImage.getWidth();
//		BufferedImage rImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				try {
//					int pixelC = cImage.getRGB(x, y);
//					int pixelB = bImage.getRGB(x, y);
//					if (pixelB == pixelC) {
//						rImage.setRGB(x, y, bImage.getRGB(x, y));
//					} else {
//						int a = 0xff | bImage.getRGB(x, y) >> 24, r = 0xff & bImage.getRGB(x, y) >> 16,
//								g = 0x00 & bImage.getRGB(x, y) >> 8, b = 0x00 & bImage.getRGB(x, y);
//
//						int modifiedRGB = a << 24 | r << 16 | g << 8 | b;
//						rImage.setRGB(x, y, modifiedRGB);
//					}
//				} catch (Exception e) {
//					// handled hieght or width mismatch
//					rImage.setRGB(x, y, 0x80ff0000);
//				}
//			}
//		}
//		String filePath = baseImage.toPath().toString();
//		String fileExtenstion = filePath.substring(filePath.lastIndexOf('.'), filePath.length());
//		if (fileExtenstion.toUpperCase().contains("PNG")) {
//			createPngImage(rImage, resultOfComparison + fileExtenstion);
//		} else {
//			createJpgImage(rImage, resultOfComparison + fileExtenstion);
//		}
//	}
}