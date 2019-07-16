package automation.project3ds;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.testng.annotations.Test;

import automation.project3ds.Driver.Browser;

public class aSample {
	
	@Test
	public void sample() throws Exception {
		Driver driver = new Driver();
		driver.get("https://www.paymentwall.com/");
	}

}
