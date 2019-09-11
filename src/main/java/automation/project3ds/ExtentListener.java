package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

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
		try {
			lastName = lastName.substring(2);
		}catch(Exception ignore) {
			
		}
		String displayName = result.getMethod().getMethodName()+" ("+lastName+" )";
		ExtentManager.startTest(suiteName, displayName);
		ExtentManager.getTest().assignCategory(suiteName);
		if(map != null) {
			ExtentManager.getTest().info("Params: " + map);
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
		
			ExtentManager.getTest().info(assertInfo);
			}
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		this.printAttribute(result);
		ExtentManager.getTest().log(Status.PASS, "Test passed");
		ExtentManager.endTest();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		this.printAttribute(result);
		ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
		ExtentManager.getTest().log(Status.FAIL, "Test Failed");
		ExtentManager.endTest();
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		this.printAttribute(result);
		ExtentManager.getTest().log(Status.SKIP, "Test Skipped");
		ExtentManager.endTest();
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
		ExtentManager.endTest();
		ExtentManager.getReporter().flush();
	}



}
