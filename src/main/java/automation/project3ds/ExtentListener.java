package automation.project3ds;

import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.Status;



public class ExtentListener implements ITestListener{
	
	String suiteName;
	String map;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		String lastName = "";
		Object[] parameters = result.getParameters();
		for(Object parameter: parameters) {
			if(parameter instanceof String) {
				String str = (String)parameter;
				if(str.length() > 30) {
					str = str.substring(0, 30)+"...";
				}
				names.add(str);
			}else if(parameter instanceof Map<?,?>) {
				parameter = (Map<?, ?>) parameter;
				map = parameter.toString();
			}
		}
		for(String name : names) {
			lastName = lastName +", " + name ;
		}
		String displayName = suiteName+" ("+lastName.substring(2)+" )";
		ExtentTestManager.startTest(displayName);
		ExtentTestManager.getTest().assignCategory(suiteName);
		if(map != null) {
		ExtentTestManager.getTest().info(map);
		}

		
	}
	
	private void printAttribute(ITestResult result) {
		try {
			String cardNumber = (String) result.getAttribute("cardNumber");
			String refID = (String) result.getAttribute("refID");
			String t_id = (String) result.getAttribute("t_id");
			ExtentTestManager.getTest().info("CardNumber: " + cardNumber);
			ExtentTestManager.getTest().info("refID: " + refID);
			ExtentTestManager.getTest().info("t_id: " + t_id);
		}catch(Exception e) {
			
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		this.printAttribute(result);
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		this.printAttribute(result);
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		this.printAttribute(result);
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		suiteName = context.getSuite().getName();
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}



}
