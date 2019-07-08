package utility;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class ConfigFile {

	public static String name = "chase";
	public static String password = "Conchon11#";
	public static String openKeyPath = "C://Path/chasHoangOpenSSH";
	public static String filePath = "C://Workspace/project3ds/src/main/java/utility/cardNumber.xlsx";
	public static String filexml = "C://Workspace/spiderpipe/spiderpipe-common/src/main/java/utility/meo.pdf";
	public static String credentialPath = "C://Program Files (x86)/Jenkins/workspaceV2/SP/SP/src/main/java/utility/client_secret_885252365679-8tk70f2ps8tiv299jpudp6faolicj9a1.apps.googleusercontent.com.json";
	public static String geckoPath = "C://Path/geckodriver.exe";
	public static String chromePath = "C://Path/chromedriver.exe";
	public static String staticEmail = "test@spam4.me";
	public static String staticPassword = "000000";
	public static Dimension d = new Dimension(1920, 1060);
	public static Point p = new Point(1366, -608);
//	 public static String host = "http://feature-sp-582.spiderpipe.s3.stuffio.com";
	public static String host = "http://https-test.spiderpipe.s3.stuffio.com";
//	 public static String host = "http://feature-sp-573.spiderpipe.s3.stuffio.com";
//	public static String host = "http://release-1.1.0.spiderpipe.s3.stuffio.com";
//	public static String host = "http://develop.spiderpipe.s3.stuffio.com";
//	public static String host = "http://payment-status.spiderpipe.s3.stuffio.com";
	public static Boolean screenShot = false;
	public static Integer[] exceptionPS = { 8, 9 };
	public static Integer[] nodefaultPS = { 11, 12 };
	public static Integer[] automationPS = { 3, 5, 6, 8, 9, 10, 11 };
	public static int is_chrome = 0;
	
	public static final String feeHost = "http://develop.fee-storage.s3.stuffio.com";
	public static String accountFilePath = "C://Program Files (x86)/Jenkins/workspaceV2/SP/SP/src/test/java/paymentwall/SP/Account.xlsx"; 
	public static String mycardcardFilePath = "C://Program Files (x86)/Jenkins/workspaceV2/SP/SP/src/test/java/paymentwall/SP/MyCardCard.xlsx"; 
	
// payment status
	
//	public static final HashMap<String, String> merchantMap = new HashMap<>();
//    static {
//    	merchantMap.put("NoMedusa", "NoMedusa235689101112emaWY@spam4.me");
//    	merchantMap.put("YesMedusa", "YesMedusa235689101112Gde0x@spam4.me");
//    }
//    
//	public static final HashMap<Integer, String> NoMedusa = new HashMap<>();
//    static {
//    	NoMedusa.put(2, "3400_mycardcard_18_1555048096_6926_5cb026a0a9150");
//    	NoMedusa.put(3, "3400_giropay_20_1555048113_6946_5cb026b1a9922");
//    	NoMedusa.put(5, "3400_unipinexpress_direct_1555048130_9734_5cb026c2eda83");
//    	NoMedusa.put(6, "3400_culturevoucherkr_default_1555048152_3156_5cb026d84d0b0");
//    	NoMedusa.put(10, "3400_paypal_default_1555048209_9923_5cb02711f2400");
//    	NoMedusa.put(11, "3400_przelewy24_v32_1555048227_2076_5cb0272332ac1");
//    	NoMedusa.put(12, "3400_alipay_default_1555048244_849_5cb02734cf44e");
//    }
//	
//	public static final HashMap<Integer, String> YesMedusa = new HashMap<>();
//    static {
//    	YesMedusa.put(2, "3401_mycardcard_18_1555048503_2901_5cb0283746d37");
//    	YesMedusa.put(3, "3401_giropay_20_1555048521_7942_5cb02849c1e47");
//    	YesMedusa.put(5, "3401_unipinexpress_direct_1555048538_6154_5cb0285a963d0");
//    	YesMedusa.put(6, "3401_culturevoucherkr_default_1555048555_8126_5cb0286bc6607");
//    	YesMedusa.put(10, "3401_paypal_default_1555048619_8938_5cb028abda389");
//    	YesMedusa.put(11, "3401_przelewy24_v32_1555048643_8875_5cb028c3d8a8b");
//    	YesMedusa.put(12, "3401_alipay_default_1555048665_0257_5cb028d90647c");
//    }
    
// payment event
	
//	public static final HashMap<String, String> merchantMap = new HashMap<>();
//    static {
//    	merchantMap.put("NoMedusa", "NoMedusaEvent235689101112K6qKG@spam4.me");
//    	merchantMap.put("YesMedusa", "YesMedusaEvent235689101112rERgn@spam4.me");
//    }
//    
//	public static final HashMap<Integer, String> NoMedusa = new HashMap<>();
//    static {
//    	NoMedusa.put(2, "3444_mycardcard_18_1555658973_8501_5cb978ddcf8dc");
//    	NoMedusa.put(3, "3444_giropay_20_1555659008_5588_5cb97900886c3");
//    	NoMedusa.put(5, "3444_unipinexpress_direct_1555659045_4077_5cb97925638a0");
//    	NoMedusa.put(6, "3444_culturevoucherkr_default_1555659072_8356_5cb97940cc004");
//    	NoMedusa.put(10, "3444_paypal_default_1555659164_6326_5cb9799c9a721");
//    	NoMedusa.put(11, "3444_przelewy24_v32_1555659186_0437_5cb979b20aab6");
//    	NoMedusa.put(12, "3444_alipay_default_1555659208_2402_5cb979c83aa23");
//    }
//	
//	public static final HashMap<Integer, String> YesMedusa = new HashMap<>();
//    static {
//    	YesMedusa.put(2, "3446_mycardcard_18_1555659717_2238_5cb97bc536a47");
//    	YesMedusa.put(3, "3446_giropay_20_1555659736_0565_5cb97bd80dc80");
//    	YesMedusa.put(5, "3446_unipinexpress_direct_1555659753_1329_5cb97be92074e");
//    	YesMedusa.put(6, "3446_culturevoucherkr_default_1555659774_1677_5cb97bfe28f0a");
//    	YesMedusa.put(10, "3446_paypal_default_1555659836_4576_5cb97c3c6fb53");
//    	YesMedusa.put(11, "3446_przelewy24_v32_1555659861_3898_5cb97c555f2b2");
//    	YesMedusa.put(12, "3446_alipay_default_1555659878_741_5cb97c66b4ea6");
//    }
	

	public static final String URL = "http://develop.spiderpipe.s3.stuffio.com";
	public static final String host_165 = "http://feature-sp-165.spiderpipe.s3.stuffio.com";
	public static final String host_166 = "http://feature-sp-166.spiderpipe.s3.stuffio.com/api";
	public static final String host_z2 = "http://test-z2.spiderpipe.s3.stuffio.com";
	public static final String host_develop = "http://develop.spiderpipe.s3.stuffio.com";
	public static final String fee_dev = "http://develop.fee-storage.s3.stuffio.com";
	public static final String Path_TestData = "src//main//java//testdata//TestData.xlsx";
	public static final String File_TestData = "TestData.xlsx";
	public static final String hostFeeStorage = fee_dev;
	public static final String hostFeeAPI = hostFeeStorage + "/api/v1/fees/calculation";

	public static String user_key = null;
	public static String certificate_number = null;
	public static String remaining_balance = null;

	public static final String[] scheme = { "non_mor", "mor" };
	public static final int[] psCode = { 2, 3, 5, 6, 8, 9, 10 };
	public static final int[] psCodeExcept = { 5, 6, 8, 9 };
	public static final int[] psCodeBut2 = { 3, 5, 6, 8, 9 };
	public static final int[] psCodeIndivisual = { 10 };
	public static final String[] is_default = { "Yes", "No" };
	public static final int[] is_complex = { 0, 1, 2 };

	public static String endpoint = "https://develop.wallapi.bamboo.stuffio.com/api/paymentpingback/test";

	public static final String[] psType = { "additional", "mol", "Prepaid", "Bank", "konbini", "Prepaid", "Credit",
			"webmoneyjapan", "Credit", "Credit", "Ewallet" };

	public static final String[] currency = { "additional", "mol", "mycardcard", "EUR", "konbini", "IDR", "KRW",
			"webmoneyjapan", "USD", "USD", "USD" };

	public static final String[] currencyCCBIN = { "additional", "mol", "mycardcard", "EUR", "konbini", "IDR", "KRW",
			"webmoneyjapan", "USD", "USD", "USD" };

//	public static final String[] currencyCCBIN = {"additional", "mol", "TWD", "VND", "konbini", "VND", "VND"
//			,"webmoneyjapan", "VND", "RUB", "USD"};

//	public static final String[] currencyCCBIN = {"additional", "mol", "TWD", "RUB", "konbini", "RUB", "RUB"
//			,"webmoneyjapan", "RUB", "RUB", "USD"};

	public static final String[] payment_system = { "additional", "mol", "mycardcard", "giropay", "konbini",
			"unipinexpress", "culturevoucherkr", "webmoneyjapan", "cc_dummy", "cc_oakland", "paypal" };

	public static final String[] countryShort = { "additional", "mol", "TW", "DE", "konbini", "ID", "KR",
			"webmoneyjapan", "US", "US", "US", };

	public static final String[] is_p2 = { "additional", "mol", "1", "1", "konbini", "1", "2", "webmoneyjapan", "0",
			"0", "1" };
	public static final String[] psInEdit = { "additional", "mol", "myCard Card (mycardcard)", "Giropay (giropay)",
			"konbini", "Unipin Express (unipinexpress)", "Culture Voucher (culturevoucherkr)", "webmoneyjapan",
			"Credit Card (cc_dummy)", "Credit card (cc_oakland)", "PayPal (paypal)" };

	public static final String[] psName = { "additional", "mol", "myCard Card", "Giropay", "konbini", "Unipin Express",
			"Culture Voucher", "webmoneyjapan", "Credit Card", "Credit card", "PayPal" };

	public static final String[] countryArea = { "additional", "mol", "Asia", "European", "konbini", "East Asia",
			"Asia", "webmoneyjapan", "United States", "United States", "United States", };

	public static final String[] country_name = { "additional", "mol", "Taiwan", "Germany", "konbini", "Indonesia",
			"Korea (Republic of)", "webmoneyjapan", "United States", "United States", "United States" };

	public static final String[] supportCountry = { "additional", "mol", "Taiwan", "Germany", "konbini", "Indonesia",
			"Korea (Republic of)", "webmoneyjapan", "United States", "United States", "United States" };

	public static final String[] is_onboarding_required = { "additional", "mol", "mycardcard", "giropay", "konbini",
			"unipinexpress", "1", "webmoneyjapan", "cc_dummy", "cc_oakland", "paypal" };

	public static String seed_key = "paymentwall--pay";
	public static String initialization_vector = "vpdlajscm---dnjf";
	public static String merchant_code = "Q300037";
	public static String remote_socket = "tcp://211.59.10.133:12045";
	public static String subMerchantCode = "tpayment";
	public static String strPublicKey = "pk_test_NFToVUUQIOd35mMxCU977yty";
	public static String strSecretKey = "sk_test_JJH7Zx7Qbk6Wa5hbraewaphh";

}
