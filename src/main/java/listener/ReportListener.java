package listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportListener implements ITestListener {
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	String repname;

	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date());
		String repname = "testReport" + timestamp + ".html";
		spark = new ExtentSparkReporter(".//test-output//Extentreports//"+repname);
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("user", "oos");

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.log(Status.PASS, "test passed");

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "test failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "test skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

public void onFinish(ITestContext context) {
	extent.flush();

}
}
