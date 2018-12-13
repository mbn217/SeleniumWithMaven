package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utilities.SeleniumUtils;

public class TC_01_Login_To_AmazonHomePage extends TestBase {
	final boolean amazonPrimeIcon = true;

	@Test
	public void loginToAmazon() {

		test.getTest().setDescription("This Test case to verify that user is able to login to Amazon dashboard");
		// Step 2
		Assert.assertEquals(amazonHomePage.verify_Amazon_LoginPage_Header_Label(), amazonPrimeIcon);
		test.log(LogStatus.PASS, "Test [ Login To Amazon dashboard ] PASSED");
	}
}
