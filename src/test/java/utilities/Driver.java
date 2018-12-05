package utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author mbn217
 * @Date 04/15/2018
 * @Purpose This is Driver class that will initialize the browser based on the
 *          entry in the configuration file
 * @Usage Methods in this class are static , so you can just use class name with
 *        method name concatenated by "." example: Driver.getDriver
 */
public class Driver {
	private static WebDriver driver;
	private static Logger log = Logger.getLogger(Driver.class);

	/**
	 * @author mbn217
	 * @Date 04/15/2018
	 * @Purpose it will open the browser based on the entry in the configuration
	 *          file
	 * @return driver sessionID
	 * @throws Throwable 
	 */

	public static WebDriver getDriver(String browser) throws Throwable {
		// Condition to check if the driver have been initialized before or not
		String driverType = browser==null? ConfigurationReader.getProperty("browser") : browser;
		// if browser has value, use browser
		// else use the value from the configuration file
		if (driver == null ) {
			log.info("Checking for a broswer to open");
			// create webdriver based on the value of browser parameter
			switch (driverType.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", ConfigurationReader.getProperty("chrome.driver.path"));
				// WebDriverManager.chromedriver().setup();
			    ChromeOptions options = new ChromeOptions();
			    options.setExperimentalOption("useAutomationExtension", false);
			    //disable automation info bar
			    options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);
				break;


			case "firefox":
				System.setProperty("webdriver.gecko.driver", ConfigurationReader.getProperty("firefox.driver.path"));
				// WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Opened firefox browser");
				break;
				
			case "remotedriver":
				String test_url ="http://jenkinsURL:4444/wd/hub";     
				URL url=new URL(test_url);
				DesiredCapabilities cap=DesiredCapabilities.chrome(); 
				cap.setPlatform(Platform.WIN8); 
				driver=new RemoteWebDriver(url, cap); 
				log.info("Opened Remote browser");
				break;

			case "firefoxheadless":
				System.setProperty("webdriver.gecko.driver", ConfigurationReader.getProperty("firefox.driver.path"));
				// WebDriverManager.firefoxdriver().setup();
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setBinary(firefoxBinary);
				driver = new FirefoxDriver(firefoxOptions);
				log.info("Opened firefox browser in headless mode");
				break;
				
			case "ie":
				System.setProperty("webdriver.ie.driver", ConfigurationReader.getProperty("ie.driver.path"));
				// WebDriverManager.iedriver().setup();
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability("requireWindowFocus", true);
				driver = new InternetExplorerDriver(capabilities);
				log.info("Opened IE browser");
				break;
				
			default:
				System.setProperty("webdriver.gecko.driver", ConfigurationReader.getProperty("firefox.driver.path"));
				driver = new FirefoxDriver();
				log.info("No browser specified, Selecting Firefox as Default browser");
				break;
			}
			driver.manage().window().maximize();
			log.info("Browser maximized");

		}

		return driver;
	}

	/**
	 * @author mbn217
	 * @Date 04/15/2018
	 * @Purpose it will close the browser
	 * @return NA
	 * @throws InterruptedException
	 */
	public static void closeDriver() throws InterruptedException {
		if (driver != null) {
			driver.quit();
			Thread.sleep(2000);
			driver = null;
			log.info("The Browser was closed successfully");
		}
	}
	
	
	
	
	public static WebDriver runRemoteDriver(String browser) {
		log.info("Running Remote Webdriver...");
		String hubUrl = "http://hostname:portnumber/wd/hub"; // will run on one node only (specific)
		DesiredCapabilities capabilities = null;
		System.out.println("Starting " + browser + " on grid");

		// Creating driver
		switch (browser) {
		case "chrome":
			log.info("Starting " + browser + " on grid");
			capabilities=DesiredCapabilities.chrome(); 
			capabilities.setPlatform(Platform.WIN8); 
			break;

		case "firefox":
			log.info("Starting " + browser + " on grid");
			capabilities=DesiredCapabilities.firefox(); 
			capabilities.setPlatform(Platform.WIN8); 
			break;
		}

		try {
			driver = (new RemoteWebDriver(new URL(hubUrl), capabilities));
		} catch (MalformedURLException e) {
			log.error("Somthing went wrong ---> "+ e.getMessage());
		}

			return driver;
	}
	
	
}
