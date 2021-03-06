package utility;


import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfigFile {

	public static String name = "doanhkaka";
	public static String password = "maideudcnhi";
	
	// "http://feature-pwg-1107.wallapi.bamboo.stuffio.com/admin/reports/payment-transaction";
	
	public static String getWorkspacePath() {
		String str = getProjectPath();
		int index = str.lastIndexOf("\\", str.lastIndexOf("\\")-1);
		String newStr = str.substring(0, index+1);
		return newStr;
	}
	
	public static String getProjectPath() {
		String str = new File(".").getAbsolutePath();
		String newStr = str.substring(0, str.length()-1);
		return newStr;
	}
	
	public static String[] getTime() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date currentTime = new Date(stamp.getTime());
		SimpleDateFormat sdfDate = new SimpleDateFormat("YYYY-MM-dd");
		String date = sdfDate.format(currentTime);
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH-mm-ss");
		String time = sdfTime.format(currentTime);
		return new String[] {date, time};
	}
	
	public static String filePath = getProjectPath() + "src\\main\\java\\utility\\cardNumber.xlsx";
	
	public static String openKeyPath = getProjectPath() + "chasHoangOpenSSH";

//	public static String filexml = "C://Workspace/spiderpipe/spiderpipe-common/src/main/java/utility/meo.pdf";
//	public static String credentialPath = "C://Program Files (x86)/Jenkins/workspaceV2/SP/SP/src/main/java/utility/client_secret_885252365679-8tk70f2ps8tiv299jpudp6faolicj9a1.apps.googleusercontent.com.json";
//	public static String geckoPath = "C://Path/geckodriver.exe";
//	public static String chromePath = "C://Path/chromedriver.exe";
//	public static String staticEmail = "test@spam4.me";
//	public static String staticPassword = "000000";
//	public static Dimension d = new Dimension(1920, 1060);
//	public static Point p = new Point(1366, -608);
//	 public static String host = "http://feature-sp-582.spiderpipe.s3.stuffio.com";
//	public static String host = "http://https-test.spiderpipe.s3.stuffio.com";
//	 public static String host = "http://feature-sp-573.spiderpipe.s3.stuffio.com";
//	public static String host = "http://release-1.1.0.spiderpipe.s3.stuffio.com";
//	public static String host = "http://develop.spiderpipe.s3.stuffio.com";
//	public static String host = "http://payment-status.spiderpipe.s3.stuffio.com";
//	public static Boolean screenShot = false;
//	public static Integer[] exceptionPS = { 8, 9 };
//	public static Integer[] nodefaultPS = { 11, 12 };
//	public static Integer[] automationPS = { 3, 5, 6, 8, 9, 10, 11 };
//	public static int is_chrome = 0;
//	
//	public static final String feeHost = "http://develop.fee-storage.s3.stuffio.com";
//	public static String accountFilePath = "C://Program Files (x86)/Jenkins/workspaceV2/SP/SP/src/test/java/paymentwall/SP/Account.xlsx"; 
//	public static String mycardcardFilePath = "C://Program Files (x86)/Jenkins/workspaceV2/SP/SP/src/test/java/paymentwall/SP/MyCardCard.xlsx"; 
//
//	public static final String URL = "http://develop.spiderpipe.s3.stuffio.com";
//	public static final String host_165 = "http://feature-sp-165.spiderpipe.s3.stuffio.com";
//	public static final String host_166 = "http://feature-sp-166.spiderpipe.s3.stuffio.com/api";
//	public static final String host_z2 = "http://test-z2.spiderpipe.s3.stuffio.com";
//	public static final String host_develop = "http://develop.spiderpipe.s3.stuffio.com";
//	public static final String fee_dev = "http://develop.fee-storage.s3.stuffio.com";
//	public static final String Path_TestData = "src//main//java//testdata//TestData.xlsx";
//	public static final String File_TestData = "TestData.xlsx";
//	public static final String hostFeeStorage = fee_dev;
//	public static final String hostFeeAPI = hostFeeStorage + "/api/v1/fees/calculation";


}
