package automation.project3ds;

import org.openqa.selenium.By;

public class CodeFeature {
	
	Driver driver;
	
	public static String CF_3DS_V2 = "3ds_v2";
	
	public static void setCodeFeature(Driver driver, String host, String feature, Boolean isEnabled) {
		CodeFeature cf = new CodeFeature(driver);
		cf.setCF(host, feature, isEnabled);
	}
	
	public CodeFeature (Driver driver) {
		this.driver = driver;
	}
	
	public void setCF(String host, String feature, Boolean isEnabled) {
		open(host, feature);
		setStatus(feature, isEnabled);
	}
	
	private void open(String host, String feature) {
		String url = host + "/admin/code-features/?search%5Bcf_id%5D=&search%5Bcf_key%5D="+feature+"&search%5Bcf_description%5D=&search%5Bcf_active%5D=all";
		driver.get(url);
	}
	
	private Element activeButton(String feature) {
		By activeButton = By.xpath("//tr[./td[text()='"+feature+"']]//a[contains(@class,'activate action_link')][not(contains(@style,'none'))]");
		Element element = driver.getElement(activeButton);
		return element;
	}
	
	private Boolean getStatus(String feature) {
		String str = activeButton(feature).getText();;
		if(str.equals("Activate")) {
			return false;
		}else if(str.equals("Deactivate")) {
			return true;
		}else {
			return null;
		}
	}
	
	
	private void setStatus(String feature, Boolean x) {
		Boolean status = getStatus(feature);
		if(x !=null && x != status) {
			activeButton(feature).click();
			AnnotationPage.sleep(2000);
		}
	}

}
