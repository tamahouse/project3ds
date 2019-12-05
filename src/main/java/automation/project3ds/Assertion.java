package automation.project3ds;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

public class Assertion {

	static MySoftAssertAll assertion;
	public static String BORDER_RED = "rgb(255, 39, 39) #ff2727 ";
	public static String BACKGROUND_RED = "rgba(255, 246, 246, 1)";
	public static String TEXT_RED = "rgba(255, 39, 39, 1) ";
	public static String BORDER_GREEN = "rgb(102, 199, 0) #66c700";
	public static String BACKGROUND_WHITE = "rgba(255, 255, 255, 1)";
	public static String TEXT_GREY = "rgba(51, 51, 51, 0.5)";

	public static Status status = Status.PASS;
	
	public static void assertConverted(String cl_id) throws Exception {
		Boolean isConverted = Wallapi.getIsConverted(cl_id);
		Assertion.get().assertEquals(isConverted, true,"[IsConvertSuccessful]");
		Assertion.end();
	}

	public static void end() {
		if (assertion != null) {
			String sb = assertion.assertAllWithoutThrow();
			assertion = null;
			if (sb == null) {
			} else {
				status = Status.FAIL;
				throw new AssertionError(sb);
			}
		}
	}

	public static MySoftAssertAll get() {
		if (assertion == null) {
			assertion = new MySoftAssertAll();
		}
		return assertion;
	}


	public static void isFormError(Driver driver, Element formError, String value) throws Exception {
		ExtentManager.addScreenshot(driver, "isFormError");
//		String border = formError.getCssValue("border-color");
//		String background = formError.getCssValue("background-color");
		Element text = formError.getElement(By.xpath("./ul/li"));
//		String textColor = text.getCssValue("color");
		String textValue = text.getText();

//		get().assertBeContains(border, BORDER_RED, "[Border]");
//		get().assertEquals(background, BACKGROUND_RED, "[Background]");
//		get().assertEquals(textColor, TEXT_RED, "[Text color]");
		get().assertEquals(textValue, value, "[Text value]");
	}



	public static void isError(String title, String color) {
		get().assertEquals(color, Assertion.TEXT_RED, title);
	}

	public static void isSuccess(Boolean x) {
		get().assertEquals(x, true, "[IsSuccess]");
	}

}
