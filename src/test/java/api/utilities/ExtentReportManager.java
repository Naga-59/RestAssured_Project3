package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.*;
import java.util.*;


public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkreports;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context) {
		//Standard time format
		SimpleDateFormat sf=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dd=new Date();
		String timeStamp_2=sf.format(dd);
		
		//report name
		repName="Test-Report-"+timeStamp_2+".html";
		
		sparkreports=new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkreports.config().setDocumentTitle("Swag Automation Report");
		sparkreports.config().setReportName("Nagaraj");
		sparkreports.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreports);
		extent.setSystemInfo("Application", "Demo_Automation");
		extent.setSystemInfo("Module", "Demo_Login");
		extent.setSystemInfo("Operting_System", System.getProperty("os.name"));
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Module", "Demo_Login");
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
