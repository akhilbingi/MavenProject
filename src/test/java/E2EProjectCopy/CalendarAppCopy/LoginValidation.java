package E2EProjectCopy.CalendarAppCopy;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author AKHIL BINGI
 *
 */
public class LoginValidation extends mainClass {

	public WebDriver driver;

	@DataProvider
	public Object[][] getdatal() throws EncryptedDocumentException, IOException {

		Object[][] data = new Object[2][2];
		data[0][0] = "akhilbingi32";
		data[0][1] = "Matrix@321456";
		data[1][0] = "z11";
		data[1][1] = "Matrix@321456";
		return data;

	}

	@Test(dataProvider = "getdatal")
	public void invokingLoginPage(String Username, String password) throws IOException, InterruptedException

	{

		driver = initialize();
		invokeURLfromtextfile i = new invokeURLfromtextfile();
		driver.get(i.geturl());
		loginPage log = new loginPage(driver);
		log.enterLoginPage().click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		log.lemail().sendKeys(Username);
		log.lpassword().sendKeys(password);
		log.lbutton().click();
		String date = log.getCurrDate().getText();
		String my = log.getcurrMonYear().getText();
		String Umy = my.toUpperCase();
		System.out.println("Date  is " + date);
		System.out.println("Date  is " + Umy);
		LocalDate currentdate = LocalDate.now();
		System.out.println("Current date: " + currentdate);
		int currentDay = currentdate.getDayOfMonth();
		String cd = String.valueOf(currentDay);
		System.out.println("Current day: " + currentDay);
		Month currentMonth = currentdate.getMonth();
		String Mon = String.valueOf(currentMonth);
		System.out.println("Current month: " + currentMonth);
		int currentYear = currentdate.getYear();
		System.out.println("Current month: " + currentYear);
		String year = String.valueOf(currentYear);
		String monYear = Mon + ", " + year;
		System.out.println(monYear);

		// Testing funtionality number 1
		// verifying the current date and todays date are matching
		Assert.assertTrue(date.equalsIgnoreCase(cd));
		Assert.assertTrue(Umy.equalsIgnoreCase(monYear));
		log.getToDoList().sendKeys("Create Jira user stories");
		log.addToDo().click();

	}

	@AfterTest
	public void aftertest() {

		driver.close();
	}

}
