package automation.project3ds;

import org.testng.annotations.Test;

public class aSample {
	
	@Test
	public void sample() throws Exception {
		String refID = "65563938";
		String t_id = Pslog.getTID(refID);
	}

}
