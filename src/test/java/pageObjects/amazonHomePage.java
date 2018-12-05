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
public class amazonHomePage {

	private static Logger log = Logger.getLogger(amazonHomePage.class);
	private WebDriver driver;
	/**
	 * Elements of the Home page
	 */	
	@FindBy(xpath = "//h2[contains(.,'theText')]")
	public WebElement theElement; 
	
	
	
	
	/**
	 * PageObject Constructor
	 * @throws Throwable 
	 */
	public amazonHomePage(WebDriver driver) throws Throwable {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
