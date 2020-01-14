package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class ProjectManagement extends BasePage{
	
	By addButton = By.id("add-app");
	By projectXpath = By.xpath("//*[contains(@id,'app_')]");
	By checkbox = By.xpath("//label[contains(@class,'checkboxes')]");
	

	public ProjectManagement(Driver driver, String branch) {
		super(driver, branch);
		// TODO Auto-generated constructor stub
		this.open();
	}

	public void open() {
		String url = this.branch + "/developers/applications";
		driver.get(url);
	}
	
	public String getFirstProjectID() {
		Element element = driver.getElement(projectXpath);
		String id = element.getAttribute("id");
		String a_id = id.replace("app_", "");
		return a_id;
	}
	
	public Project getProject(String a_id) {
		return new Project(a_id);
	}
	
	public void clickAddButton() {
		Element element = driver.getElement(addButton);
		List<Element> elements = driver.getElements(projectXpath);
		int oldSize = elements.size();
		element.click();
		By additionalProject = By.xpath("//*[contains(@id,'app_')]["+(oldSize+1)+"]");
		driver.getElement(additionalProject);
	}
	
	public class Project {
		String a_id;
		By xpath;
		
		By statusBtn = By.xpath(".//*[@class='app-header']/*[2]");
		By statusPendingBtn = By.xpath("//*[@class='app-header']/*[2][@class='app-pending']");
		By settingBtn = By.xpath(".//a[contains(@href,'applications/edit')]");
		
		By checkbox = By.xpath("//*[contains(@id,'golive_checkbox_')]");
		By submitForReviewBtn = By.id("create_queue_show_details");
		
		public Project(String a_id) {
			this.xpath = By.id("app_"+a_id);
			this.a_id = a_id;
		}
		
		public Element element() {
			return driver.getElement(xpath);
		}
		
		public void submitQueue() {
			this.clickStatusButton();
			this.clickAllCheckbox();
			this.clickSubmitForReviewButton();
			this.waitForStatusPending();
		}
		
		private void clickStatusButton() {
			Element element = this.element().getElement(statusBtn);
			element.click();
		}
		
		private void clickAllCheckbox() {
			driver.getElement(checkbox);;
			List<Element> elements = driver.getElements(checkbox);
			for (Element element: elements){
				element.clickJS();
			}
		}
		
		private void clickSubmitForReviewButton() {
			Element element = driver.getElement(submitForReviewBtn);
			element.sleep(1000);
			element.click();
			driver.sleep(3000);
		}
		
		private void waitForStatusPending() {
			try {
			this.element().getElement(statusPendingBtn,30000);
			}catch(Exception e) {
				
			}
		}
		
	}
	
}
