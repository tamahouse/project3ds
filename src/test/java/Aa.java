import org.testng.annotations.Test;

import automation.project3ds.Wallapi;

public class Aa {

	@Test
	public void meo() throws Exception {
		String cl_id = "65577431";
		Boolean isConverted = Wallapi.getIsConverted(cl_id);
	}
}
