package E2EProjectCopy.CalendarAppCopy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * @author AKHIL BINGI
 *
 */
public class Listeners extends mainClass implements ITestListener {

	ExtentReports extent = reportsGen.getReports();
	ExtentTest Test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {

		// get the method name which has been just started
		Test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(Test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "Test is passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;
		String testmethod = result.getMethod().getMethodName();

		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());

		} catch (Exception e) {

		}
		try {
			// when there is failure of a method in a class it takes a screenshot where the
			// failure occured
			extentTest.get().addScreenCaptureFromPath(TakingScreenshot(testmethod, driver),
					result.getMethod().getMethodName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();

	}

}
