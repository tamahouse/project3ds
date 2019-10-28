package automation.project3ds;

public class WidgetPage extends BasePage {
	
	public WidgetPage(Driver driver) {
		super(driver);
	}
	
	public WidgetMulti getMultiWidget() {
		return new WidgetMulti(driver);
	}
	
	public WidgetUni getWidgetUni() {
		return new WidgetUni(driver);
	}

	public WidgetTerminal getWidgetTerminal() {
		return new WidgetTerminal(driver);
	}
}
