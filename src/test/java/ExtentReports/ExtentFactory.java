package ExtentReports;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.ExtentReports;

import utilities.ConfigurationReader;

/**
 * @author mbn217
 * @purpose This is is a Report class using Extent Report library API
 * To see the report you can navigate to Extent Report folder in Eclipse project
 * and open it using firefox (IE currently not compatible)
 * @Date 03-30-2018
 * @param N/A
 * @return extent: instance of the ExtentReports class
 */
public class ExtentFactory {
	private static Logger log = Logger.getLogger(ExtentFactory.class);
	public static ExtentReports getInstance() {
		log.info("Instantiating an instance of the ExtentReports ");
		ExtentReports extent;
		
//	    String destFile=null;
//	    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
//	    destFile = "ExtentReport";
//	    String destDir = dateFormat.format(new Date()) + ".html";	
//		extent = new ExtentReports(destFile+"//" +destDir, false);
	    extent = new ExtentReports("ExtentReport\\amazon.html", false);
		extent.addSystemInfo("Selenium Version", "3.11.0").addSystemInfo("Platform", "Windows 7");	
		extent.addSystemInfo("Environment", ConfigurationReader.getProperty("environement")).addSystemInfo("Application", "Amazon");

		return extent;
	}
}