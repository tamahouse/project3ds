package automation.project3ds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.utils.Reader;
import org.jsoup.nodes.Element;

public class ExtentReportImpl extends ExtentReports {

	String reportName;
	String reportPath;
	String dateReportName;
	String dateReportPath;
	ExtentReports index;
	ExtentReports dateIndex;

	public ExtentReportImpl(String reportName, String dateReportName) {
		this.reportName = reportName;
		this.dateReportName = dateReportName;
		this.reportPath = "C:\\Workspace\\project3ds\\test-output\\ExtendReport\\" + reportName + ".html";
		this.dateReportPath = "C:\\Workspace\\project3ds\\test-output\\ExtendReport\\" + dateReportName + ".html";
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(reportPath);
		this.attachReporter(reporter);
		this.setDateIndex();
		this.setIndex();
	}

	public ExtentReportImpl(String reportPath) {
		this.reportPath = reportPath;
	}

	public ExtentReports getIndex() {
		return index;
	}

	public ExtentReports getDateIndex() {
		return dateIndex;
	}

	private static Document getHTMLFile(String reportPath) {
		String html = Reader.readAllText(reportPath);
		Document doc = Jsoup.parse(html);
		return doc;
	}

	private static Boolean isTestExist(String reportPath, String reportNameToCheck) {
		Document doc = getHTMLFile(reportPath);
		Elements allTests = doc.select("#test-collection > .test");
		for (Element test : allTests) {
			String name = test.select(".test-name").first().html();
			if (name.contains(reportNameToCheck)) {
				return true;
			}
		}
		return false;
	}

	private void setIndex() {
		String indexReportPath = "C:\\Workspace\\project3ds\\test-output\\ExtendReport\\index.html";
		if (ExtentReportImpl.isTestExist(indexReportPath, dateReportName) == false) {
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(indexReportPath);
			reporter.setAppendExisting(true);
			this.index = new ExtentReports();
			this.index.attachReporter(reporter);
			ExtentTest indexTest = this.index.createTest(dateReportName);
			indexTest.info("<a href =\"" + dateReportPath + "\">" + dateReportName + "</a>");
		}
	}

	private void setDateIndex() {
		if (ExtentReportImpl.isTestExist(dateReportPath, reportName) == false) {
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(dateReportPath);
			reporter.setAppendExisting(true);
			this.dateIndex = new ExtentReports();
			this.dateIndex.attachReporter(reporter);
			ExtentTest dateIndexTest = this.dateIndex.createTest(reportName);
			dateIndexTest.info("<a href =\"" + reportPath + "\">" + reportName + "</a>");
		}
	}

}
