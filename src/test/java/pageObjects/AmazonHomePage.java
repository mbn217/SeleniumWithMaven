package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;
import utilities.JavaUtils;


/**
 * @author mbn217
 * @Date 09/05/2018
 * @Purpose the purpose
 */
public class AmazonHomePage {

	private static Logger log = Logger.getLogger(AmazonHomePage.class);
	private WebDriver driver;
	/**
	 * Elements of the Home page
	 */	
	@FindBy(xpath = "//a[@class='nav-logo-link' and @aria-label='Amazon']")
	public WebElement amazonPrimeIcon; 
	
	
	//+++++++++++++++++++++++++//
	
	public boolean verify_Amazon_LoginPage_Header_Label(){
		boolean isFound = true;
		log.info("Verifying [amazon Prime] icon is displayed properly or NOT");
		try {
			boolean actualLabel = amazonPrimeIcon.isDisplayed();
			log.info("The Element is FOUND");
			return isFound;
		} catch (NoSuchElementException e) {
			log.error("The Element is not FOUND --> " + e.getMessage());
			return isFound = false;
		} catch (Exception e) {
			log.error("Something went Wrong --> " + e.getMessage());
			return isFound = false;
		}
	}
	
	/**
	 * PageObject Constructor
	 * @throws Throwable 
	 */
	public AmazonHomePage(WebDriver driver) throws Throwable {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
