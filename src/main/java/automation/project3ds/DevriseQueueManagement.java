package automation.project3ds;

import org.openqa.selenium.By;

public class DevriseQueueManagement extends BasePage {
	
	By queue = By.xpath("//*[./*[@id='qid']]");

	public DevriseQueueManagement(Driver driver, String branch, String a_id) {
		super(driver, branch);
		this.open(a_id);
	}

	private void open(String a_id) {
		String url = this.branch + "/admin/devrise/queue?&search%5Ba_id%5D=" + a_id;
		driver.get(url);
	}
	
	public String getFirstQueueID() {
		Element element = driver.getElement(queue);
		Queue queue = new Queue(element);
		String qid = queue.getID();
		return qid;
	}
	
	
	public class Queue {
		String qid;
		Element element;
		
		By qidTxt = By.id("qid");
		
		public Queue (String qid) {
			this.qid = qid;
		}
		
		public Queue (Element element) {
			this.element = element;
		}
		
		public String getID() {
			Element element = driver.getElement(qidTxt);
			String qid = element.getText();
			return qid;
		}
	}
}
