package automation.project3ds;

import java.io.File;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.utils.FileUtil;

public class ExtentHtmlReporterImpl extends ExtentHtmlReporter {
	
	String filePath;

	public ExtentHtmlReporterImpl(String filePath) {
		super(filePath);
		this.filePath = filePath;
		this.createPath();
		// TODO Auto-generated constructor stub
	}
	
    private void createPath() {
        File f = new File(filePath);
        File parentFile;
        if (f.isDirectory() || FileUtil.getExtension(f).isEmpty()) {
        	parentFile = f;
        } else {
        	parentFile = f.getParentFile();
        }
        String destination = parentFile == null ? "" : parentFile.getAbsolutePath() + "/";
        File destinationFile = new File(destination);
        if (!destinationFile.exists()) {
        	destinationFile.mkdirs();
        }
    }

}
