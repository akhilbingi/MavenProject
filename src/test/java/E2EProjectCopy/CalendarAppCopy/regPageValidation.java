package E2EProjectCopy.CalendarAppCopy;
import org.openqa.selenium.By;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;	
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import util.TestUtil;

/**
 * @author AKHIL BINGI
 *
 */

public class regPageValidation extends mainClass {
	public WebDriver driver;

	@Test(dataProvider = "getdata")
	public void invokingRegistrationPage(String emailid, String Name, String Username, String password, String URL)
			throws IOException, InterruptedException

	{
		driver = initialize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		invokeURLfromtextfile i = new invokeURLfromtextfile();
		driver.get(i.geturl());
		registrationPage reg = new registrationPage(driver);
		reg.EnterRegPage().click();
		Thread.sleep(2000L);
		reg.getEmail().sendKeys(emailid);
		reg.getName().sendKeys(Name);
		reg.getUsernme().sendKeys(Username);
		reg.getpassword().sendKeys(password);
		reg.getAvatar().sendKeys(URL);
		reg.Submit().click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// cust_url is the url of the login user at that instance
		String cust_url = i.geturl() + "calendar/" + Username;
		System.out.println(cust_url);
		String curl_url = driver.getCurrentUrl();
		System.out.println(curl_url);

		// according to functonality the user is logging after functionality so
		// validating the url
		Assert.assertTrue(cust_url.equalsIgnoreCase(curl_url),
				"The test case failed as the user could not be able to register");
	}

	@DataProvider
	public Object[][] getdata() {

		Object[][] data = TestUtil.getTestData("reg");
		return data;

	}

	@AfterTest
	public void aftertest() {
		driver.close();
	}

}
