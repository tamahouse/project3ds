package automation.project3ds;

public class MIDManagement extends BasePage{

	public MIDManagement(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
	}
	
	public String getMIDIDFromUrl() {
		String url = driver.getCurrentUrl("search",true);
		String mid_id = url.substring(url.indexOf("[dma_id]=")+9);
		return mid_id;
	}

}
