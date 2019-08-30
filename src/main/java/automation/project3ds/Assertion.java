package automation.project3ds;

import org.openqa.selenium.By;

public class Assertion {
	
	static MySoftAssertAll assertion;
	public static String BORDER_RED = "rgb(255, 39, 39) #ff2727 ";
	public static String BACKGROUND_RED = "rgba(255, 246, 246, 1)";
	public static String TEXT_RED = "rgba(255, 39, 39, 1) ";
	public static String BORDER_GREEN = "rgb(102, 199, 0) #66c700";
	public static String BACKGROUND_WHITE = "rgba(255, 255, 255, 1)";
	public static String TEXT_GREY = "rgba(51, 51, 51, 0.5)";
	
	public static void end() {
		String sb = assertion.assertAllWithoutThrow();
		assertion = null;
		if(sb == null) {
		}else {
			throw new AssertionError(sb);
		}
	}
	
	public static MySoftAssertAll get() {
		if(assertion == null) {
			assertion = new MySoftAssertAll();
		}
		return assertion;
	}
	
	public static void isInvalid(Element element, String eErrorText) {
		AnnotationPage.screenShot("screenShot/click/"+ Driver.timestamp() + "isInvalid.png");
		String title = element.getText();
		String assertTitle = "[" + title + "]";
//		String border = element.getCssValue("border-color");
//		String background = element.getCssValue("background-color");
//		Element label = element.getElement(By.xpath("./*[text()][1]"));
//		String labelColor = label.getCssValue("color");
//		String eBorder = BORDER_RED ;
//		String eBackground = BACKGROUND_RED;
//		String eLabelColor = TEXT_RED;
		if(eErrorText != null) {
		Element error = element.getElement(By.xpath("./following-sibling::*[contains(@class,'brick-errors')]/*[text()]"));
//		String errorColor = error.getCssValue("color");
		String errorText = error.getText();
//		get().assertEquals(errorColor, TEXT_RED, assertTitle+"[ErrorTextColor]");
		get().assertEquals(errorText, eErrorText, assertTitle+"[ErrorTextValue]");
		}
//		get().assertBeContains(border, eBorder, assertTitle+"[BorderColor]");
//		get().assertEquals(background, eBackground, assertTitle+"[BackgroundColor]");
//		get().assertEquals(labelColor, eLabelColor, assertTitle+"[LabelColor]");
	}
	
	public static void isInvalid(Element element) {
		isInvalid(element, null);
	}
	
	public static void isFormError(Element formError, String value) {
		AnnotationPage.screenShot("screenShot/click/"+ Driver.timestamp() + "isFormError.png");
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
	
	public static void isValid(Element element) {
		AnnotationPage.screenShot("screenShot/click/"+ Driver.timestamp() + "isValid.png");
//		String title = element.getText();
//		String assertTitle = "[" + title + "]";
//		String border = element.getCssValue("border-color");
//		String background = element.getCssValue("background-color");
//		Element label = element.getElement(By.xpath("./*[text()][1]"));
//		String labelColor = label.getCssValue("color");
//		String eBorder = BORDER_GREEN ;
//		String eBackground = BACKGROUND_WHITE;
//		String eLabelColor = TEXT_GREY;
//		get().assertBeContains(border, eBorder, assertTitle+"[BorderColor]");
//		get().assertEquals(background, eBackground, assertTitle+"[BackgroundColor]");
//		get().assertEquals(labelColor, eLabelColor, assertTitle+"[LabelColor]");
	}
	
	public static void isError(String title, String color) {
		get().assertEquals(color, Assertion.TEXT_RED, title);
	}
	
	public static void isSuccess(Boolean x) {
		get().assertEquals(x, true,"[IsSuccess]");
	}
	
}
