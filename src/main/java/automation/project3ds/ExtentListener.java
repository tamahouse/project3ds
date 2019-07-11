package automation.project3ds;

import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.asserts.IAssert;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.Status;

import automation.project3ds.MySoftAssert.MyIAssert;



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
		ExtentTestManager.getTest().info("Params: " + map);
		}

		
	}
	
	private void printAttribute(ITestResult result) {
		String assertInfo = "";
		String actual = null;
		String expected = null;
		try {
			@SuppressWarnings("unchecked")
			List<MyIAssert<?>> list = (List<MyIAssert<?>>) result.getAttribute("assertCommandList");
			if(list != null) {
			for(MyIAssert<?> a : list) {
				if(a.getActual() instanceof String) {
				actual = (String) a.getActual();
				expected = (String) a.getExpected();
				}else if(!(a.getActual() instanceof String) ) {
					actual = String.valueOf(a.getActual());
					expected = String.valueOf(a.getExpected());
				}
				String message = a.getMessage();
				String assertType = a.getAssertType();
				String line = message + " expected {" + expected + "}"+ assertType +"actual {" + actual + "}" + "<br />";
				assertInfo = assertInfo + line;
			}
		
			ExtentTestManager.getTest().info(assertInfo);
			}
		}catch(Exception e) {
			throw e;
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
