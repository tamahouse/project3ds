package automation.project3ds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

public class MyReporter implements IReporter {
	
	static String green = "#a3ffba";
	static String yellow = "#ffffd9";
	static String red = "#ff9c9c";
	static String blue = "#9cfff0";

//	private static final String emailableReportTemplateFile = "C:/WorkSpace/dev2qa.com/TestNGExampleProject/src/com/dev2qa/example/testng/report/custom/customize-emailable-report-template.html";

	static String cardNumber;
	
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		

		// TODO Auto-generated method stub
		try {
			// Get content data in TestNG report template file.
			String customReportTemplateStr = this.readEmailabelReportTemplate(outputDirectory);

			// Create custom report title.
			String customReportTitle = this.getCustomReportTitle("Custom TestNG Report");

			// Create test suite summary data.
			String customSuiteSummary = this.getTestSuiteSummary(suites);
			
			// Create test suite summary data.
			String customSuiteTitle = this.getTestSuiteTitle(suites);

			// Create test methods summary data.
			String customTestMethodSummary = this.getTestMehodSummary(suites);

			// Replace report title place holder with custom title.
			customReportTemplateStr = customReportTemplateStr.replaceAll("\\$TestNG_Custom_Report_Title\\$",
					customReportTitle);
			

			// Replace test suite place holder with custom test suite summary.
			customReportTemplateStr = customReportTemplateStr.replaceAll("\\$Test_Case_Summary\\$", customSuiteSummary);
			
			// Replace test methods place holder with custom test method summary.
			customReportTemplateStr = customReportTemplateStr.replaceAll("\\$Test_Case_Detail_Title\\$",
					customSuiteTitle);

			// Replace test methods place holder with custom test method summary.
			customReportTemplateStr = customReportTemplateStr.replaceAll("\\$Test_Case_Detail\\$",
					customTestMethodSummary);

			// Write replaced test report content to custom-emailable-report.html.
			File targetFile = new File(outputDirectory + "/custom-emailable-report.html");
			FileWriter fw = new FileWriter(targetFile);
			fw.write(customReportTemplateStr);
			fw.flush();
			fw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* Read template content. */
	private String readEmailabelReportTemplate(String outputDirectory) {
		StringBuffer retBuf = new StringBuffer();

		try {

			File file = new File(outputDirectory + "/customize-emailable-report-template.html");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();
			while (line != null) {
				retBuf.append(line);
				line = br.readLine();
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}

	/* Build custom report title. */
	private String getCustomReportTitle(String title) {
		StringBuffer retBuf = new StringBuffer();
		retBuf.append(title + " " + this.getDateInStringFormat(new Date()));
		return retBuf.toString();
	}

	/* Build test suite summary data. */
	private String getTestSuiteSummary(List<ISuite> suites) {
		StringBuffer retBuf = new StringBuffer();

		try {
			int totalTestCount = 0;
			int totalTestPassed = 0;
			int totalTestFailed = 0;
			int totalTestSkipped = 0;

			for (ISuite tempSuite : suites) {
//				retBuf.append("<tr><td colspan=11><center><b>" + tempSuite.getName() + "</b></center></td></tr>");

				Map<String, ISuiteResult> testResults = tempSuite.getResults();

				for (ISuiteResult result : testResults.values()) {

					retBuf.append("<tr>");

					ITestContext testObj = result.getTestContext();

					totalTestPassed = testObj.getPassedTests().getAllMethods().size();
					totalTestSkipped = testObj.getSkippedTests().getAllMethods().size();
					totalTestFailed = testObj.getFailedTests().getAllMethods().size();

					totalTestCount = totalTestPassed + totalTestSkipped + totalTestFailed;

					/* Test name. */
					retBuf.append("<td>");
					retBuf.append(testObj.getName());
					retBuf.append("</td>");

					/* Total method count. */
					retBuf.append("<td>");
					retBuf.append(totalTestCount);
					retBuf.append("</td>");

					/* Passed method count. */
					retBuf.append("<td bgcolor="+green+">");
					retBuf.append(totalTestPassed);
					retBuf.append("</td>");

					/* Skipped method count. */
					retBuf.append("<td bgcolor="+yellow+">");
					retBuf.append(totalTestSkipped);
					retBuf.append("</td>");

					/* Failed method count. */
					retBuf.append("<td bgcolor="+red+">");
					retBuf.append(totalTestFailed);
					retBuf.append("</td>");

					/* Get browser type. */
					String browserType = tempSuite.getParameter("browserType");
					if (browserType == null || browserType.trim().length() == 0) {
						browserType = "Chrome";
					}

					/* Append browser type. */
					retBuf.append("<td>");
					retBuf.append(browserType);
					retBuf.append("</td>");

					/* Start Date */
					Date startDate = testObj.getStartDate();
					retBuf.append("<td>");
					retBuf.append(this.getDateInStringFormat(startDate));
					retBuf.append("</td>");

					/* End Date */
					Date endDate = testObj.getEndDate();
					retBuf.append("<td>");
					retBuf.append(this.getDateInStringFormat(endDate));
					retBuf.append("</td>");

					/* Execute Time */
					long deltaTime = endDate.getTime() - startDate.getTime();
					String deltaTimeStr = this.convertDeltaTimeToString(deltaTime);
					retBuf.append("<td>");
					retBuf.append(deltaTimeStr);
					retBuf.append("</td>");

					/* Include groups. */
					retBuf.append("<td>");
					retBuf.append(this.stringArrayToString(testObj.getIncludedGroups()));
					retBuf.append("</td>");

					/* Exclude groups. */
					retBuf.append("<td>");
					retBuf.append(this.stringArrayToString(testObj.getExcludedGroups()));
					retBuf.append("</td>");

					retBuf.append("</tr>");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}

	/* Get date string format value. */
	private String getDateInStringFormat(Date date) {
		StringBuffer retBuf = new StringBuffer();
		if (date == null) {
			date = new Date();
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		retBuf.append(df.format(date));
		return retBuf.toString();
	}

	/* Convert long type deltaTime to format hh:mm:ss:mi. */
	private String convertDeltaTimeToString(long deltaTime) {
		StringBuffer retBuf = new StringBuffer();

		long milli = deltaTime;

		long seconds = deltaTime / 1000;

		long minutes = seconds / 60;

		long hours = minutes / 60;

		retBuf.append(hours + ":" + minutes + ":" + seconds + ":" + milli);

		return retBuf.toString();
	}

	/* Get test method summary info. */
	private String getTestMehodSummary(List<ISuite> suites) {
		StringBuffer retBuf = new StringBuffer();

		try {
			for (ISuite tempSuite : suites) {
//				retBuf.append("<tr><td colspan=7><center><b>" + tempSuite.getName() + "</b></center></td></tr>");

				Map<String, ISuiteResult> testResults = tempSuite.getResults();

				for (ISuiteResult result : testResults.values()) {

					ITestContext testObj = result.getTestContext();

					String testName = testObj.getName();

					/* Get failed test method related data. */
					IResultMap testFailedResult = testObj.getFailedTests();
					String failedTestMethodInfo = this.getTestMethodReport(testName, testFailedResult, false, false);
					retBuf.append(failedTestMethodInfo);

					/* Get skipped test method related data. */
					IResultMap testSkippedResult = testObj.getSkippedTests();
					String skippedTestMethodInfo = this.getTestMethodReport(testName, testSkippedResult, false, true);
					retBuf.append(skippedTestMethodInfo);

					/* Get passed test method related data. */
					IResultMap testPassedResult = testObj.getPassedTests();
					String passedTestMethodInfo = this.getTestMethodReport(testName, testPassedResult, true, false);
					retBuf.append(passedTestMethodInfo);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}

	private String getTestSuiteTitle(List<ISuite> suites) {
		StringBuffer retBuf = new StringBuffer();
		String title = null;
		try {
			for (ISuite tempSuite : suites) {
//				retBuf.append("<tr><td colspan=7><center><b>" + tempSuite.getName() + "</b></center></td></tr>");

				Map<String, ISuiteResult> testResults = tempSuite.getResults();
				for (ISuiteResult result : testResults.values()) {

					ITestContext testObj = result.getTestContext();

				
				/* Get failed test method related data. */
				IResultMap testFailedResult = testObj.getFailedTests();
				String failedTestMethodInfo = this.getTestMethodReportTitle(testFailedResult);
				title = failedTestMethodInfo;

				/* Get skipped test method related data. */
				IResultMap testSkippedResult = testObj.getSkippedTests();
				String skippedTestMethodInfo = this.getTestMethodReportTitle(testSkippedResult);
				if(skippedTestMethodInfo.length() > title.length()) {
					title = skippedTestMethodInfo;
				}

				/* Get passed test method related data. */
				IResultMap testPassedResult = testObj.getPassedTests();
				String passedTestMethodInfo = this.getTestMethodReportTitle(testPassedResult);
				if(passedTestMethodInfo.length() > title.length()) {
					title = passedTestMethodInfo;
				}
				
				
				}

			}
			
			retBuf.append(title);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return retBuf.toString();
		}
	}

	/* Get failed, passed or skipped test methods report. */
	private String getTestMethodReportTitle(IResultMap testResultMap) {
		StringBuffer retStrBuf = new StringBuffer();


		String color = "gray";

//		retStrBuf.append(
//				"<tr bgcolor=" + color + "><td colspan=7><center><b>" + "Title" + "</b></center></td></tr>");

		Set<ITestResult> testResultSet = testResultMap.getAllResults();

		for (ITestResult testResult : testResultSet) {

			String testClassName = "";
			String testMethodName = "";
			String startDateStr = "";
			String executeTimeStr = "";
			String paramStr = "";
			String reporterMessage = "";
			String exceptionMessage = "";

			// Get testClassName
			testClassName = testResult.getTestClass().getName();

			// Get testMethodName
			testMethodName = testResult.getMethod().getMethodName();

			// Get startDateStr
			long startTimeMillis = testResult.getStartMillis();
			startDateStr = this.getDateInStringFormat(new Date(startTimeMillis));

			// Get Execute time.
			long deltaMillis = testResult.getEndMillis() - testResult.getStartMillis();
			executeTimeStr = this.convertDeltaTimeToString(deltaMillis);

			// Get reporter message list.
			List<String> repoterMessageList = Reporter.getOutput(testResult);
			for (String tmpMsg : repoterMessageList) {
				reporterMessage += tmpMsg;
				reporterMessage += " ";
			}

			// Get exception message.
			Throwable exception = testResult.getThrowable();
			if (exception != null) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				exception.printStackTrace(pw);

				exceptionMessage = sw.toString();
			}

			retStrBuf.append("<tr bgcolor=" + color + ">");

			/* Add test class name. */
			retStrBuf.append("<td>");
			retStrBuf.append("TEST CLASS NAME");
			retStrBuf.append("</td>");

			/* Add test method name. */
			retStrBuf.append("<td>");
			retStrBuf.append("TEST METHOD");
			retStrBuf.append("</td>");

			/* Add start time. */
			retStrBuf.append("<td>");
			retStrBuf.append("START");
			retStrBuf.append("</td>");

			/* Add execution time. */
			retStrBuf.append("<td>");
			retStrBuf.append("EXCECUTE");
			retStrBuf.append("</td>");

			// Get parameter list.
			
			Object paramObjArr[] = testResult.getParameters();
			for (Object paramObj : paramObjArr) {
				try {
					paramStr += (String) paramObj;
					paramStr = "CARD NUMBER";
//					paramStr += " ";
					/* Add parameter. */
					retStrBuf.append("<td>");
					retStrBuf.append(paramStr);
					retStrBuf.append("</td>");
				} catch (Exception e) {
//					Map<String, String> paramMap = (Map<String, String>)paramObj;
//					for(Map.Entry<String, String> entry : paramMap.entrySet()) {
//						String paramStrMap = "";
//						paramStrMap += entry.getKey();
////						paramStrMap += " ";
//						/* Add parameter. */
//						retStrBuf.append("<td>");
//						retStrBuf.append(paramStrMap);
//						retStrBuf.append("</td>");
//					}

				}
			}

			List<Object> messageList = (List<Object>) testResult.getTestContext().getAttribute("messageList");

			for (Object actual : messageList) {
				String paramStrMap = "";
				paramStrMap += (String) actual;
				/* Add parameter. */
				retStrBuf.append("<td>");
				retStrBuf.append(paramStrMap);
				retStrBuf.append("</td>");
			}

//			/* Add reporter message. */
//			retStrBuf.append("<td>");
//			retStrBuf.append(reporterMessage);
//			retStrBuf.append("</td>");

			/* Add exception message. */
			retStrBuf.append("<td>");
			retStrBuf.append("EXCEPTION LOG");
			retStrBuf.append("</td>");

			retStrBuf.append("</tr>");
			return retStrBuf.toString();
		}
		return null;

	}

	/* Get failed, passed or skipped test methods report. */
	private String getTestMethodReport(String testName, IResultMap testResultMap, boolean passedReault,
			boolean skippedResult) {
		StringBuffer retStrBuf = new StringBuffer();

		String resultTitle = testName;

		String color = green;

		if (skippedResult) {
			resultTitle += " - Skipped ";
			color = yellow;
		} else {
			if (!passedReault) {
				resultTitle += " - Failed ";
				color = red;
			} else {
				resultTitle += " - Passed ";
				color = green;
			}
		}
		Set<ITestResult> testResultSet = testResultMap.getAllResults();
		Boolean x = true;
		for (ITestResult testResult : testResultSet) {
			if(x== true) {
			testResult.getAttribute("messageList");
			int size = ((List<Object>) testResult.getTestContext().getAttribute("messageList")).size();
			int titlesize = 6 + size;

		retStrBuf.append(
				"<tr bgcolor=" + color + "><td colspan="+titlesize+"><center><b>" + resultTitle + "</b></center></td></tr>");
			x = false;
			}
			
		}

		for (ITestResult testResult : testResultSet) {
			String testClassName = "";
			String testMethodName = "";
			String startDateStr = "";
			String executeTimeStr = "";
			String paramStr = "";
			String reporterMessage = "";
			String exceptionMessage = "";

			// Get testClassName
			testClassName = testResult.getTestClass().getName();

			// Get testMethodName
			testMethodName = testResult.getMethod().getMethodName();

			// Get startDateStr
			long startTimeMillis = testResult.getStartMillis();
			startDateStr = this.getDateInStringFormat(new Date(startTimeMillis));

			// Get Execute time.
			long deltaMillis = testResult.getEndMillis() - testResult.getStartMillis();
			executeTimeStr = this.convertDeltaTimeToString(deltaMillis);

			// Get reporter message list.
			List<String> repoterMessageList = Reporter.getOutput(testResult);
			for (String tmpMsg : repoterMessageList) {
				reporterMessage += tmpMsg;
				reporterMessage += " ";
			}

			// Get exception message.
			Throwable exception = testResult.getThrowable();
			if (exception != null) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				exception.printStackTrace(pw);

				exceptionMessage = sw.toString().substring(0, 500);
			}

			retStrBuf.append("<tr bgcolor=" + color + ">");

			/* Add test class name. */
			retStrBuf.append("<td>");
			retStrBuf.append(testClassName);
			retStrBuf.append("</td>");

			/* Add test method name. */
			retStrBuf.append("<td>");
			retStrBuf.append(testMethodName);
			retStrBuf.append("</td>");

			/* Add start time. */
			retStrBuf.append("<td>");
			retStrBuf.append(startDateStr);
			retStrBuf.append("</td>");

			/* Add execution time. */
			retStrBuf.append("<td>");
			retStrBuf.append(executeTimeStr);
			retStrBuf.append("</td>");

			// Get parameter list.
			Object paramObjArr[] = testResult.getParameters();
			String cardNumber = null;
			for (Object paramObj : paramObjArr) {
				try {
					cardNumber = (String) paramObj;
					paramStr += (String) paramObj;
//				paramStr += " ";
					/* Add parameter. */
					retStrBuf.append("<td>");
					retStrBuf.append(paramStr);
					retStrBuf.append("</td>");
				} catch (Exception e) {
//					Map<String, String> paramMap = (Map<String, String>)paramObj;
//					for(Map.Entry<String, String> entry : paramMap.entrySet()) {
//						String paramStrMap = "";
//						paramStrMap += entry.getKey();
////						paramStrMap += " ";
//						/* Add parameter. */
//						retStrBuf.append("<td>");
//						retStrBuf.append(paramStrMap);
//						retStrBuf.append("</td>");
//					}

				}
			}


			List<Object> actualList = (List<Object>) testResult.getTestContext().getAttribute("actualList"+cardNumber);
			List<Object> expectedList = (List<Object>) testResult.getTestContext().getAttribute("expectedList"+cardNumber);
			List<Object> resultList = (List<Object>) testResult.getTestContext().getAttribute("resultList"+cardNumber);

			try {
			for (int i=0; i < actualList.size(); i++) {
				String paramStrMap = "";
				try {
				paramStrMap += (String) actualList.get(i) + " (" +expectedList.get(i)+ ")";
				/* Add parameter. */
				if(resultList.get(i).equals("failed")) {
				retStrBuf.append("<td bgcolor="+blue+">");
				}else {
					retStrBuf.append("<td>");
				}
				retStrBuf.append(paramStrMap);
				retStrBuf.append("</td>");
				}catch (Exception e) {
					
				}
			}
			}catch (Exception e) {
				
			}

//			/* Add reporter message. */
//			retStrBuf.append("<td>");
//			retStrBuf.append(reporterMessage);
//			retStrBuf.append("</td>");

			/* Add exception message. */
			retStrBuf.append("<td>");
			retStrBuf.append(exceptionMessage);
			retStrBuf.append("</td>");

			retStrBuf.append("</tr>");

		}

		return retStrBuf.toString();
	}

	/* Convert a string array elements to a string. */
	private String stringArrayToString(String strArr[]) {
		StringBuffer retStrBuf = new StringBuffer();
		if (strArr != null) {
			for (String str : strArr) {
				retStrBuf.append(str);
				retStrBuf.append(" ");
			}
		}
		return retStrBuf.toString();
	}

}
