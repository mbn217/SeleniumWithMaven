package smokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utilities.SeleniumUtils;

public class TC_01_Login_To_AmazonHomePage extends TestBase {

	private final String EXPECTED_LOGIN_PAGE_HEADER = "the header here";


	@Test
	public void loginToPRATsDashboard() {

		test.getTest().setDescription("This Test case to verify that user is able to login to PRATs dashboard");
		// Step 2
		Assert.assertEquals(amazonHomePage.verify_Amazon_LoginPage_Header_Label(), EXPECTED_LOGIN_PAGE_HEADER);
		
		test.log(LogStatus.PASS, "Test [ Login To Amazon dashboard ] PASSED");
	}
}
