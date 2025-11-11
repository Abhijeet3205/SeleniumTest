package testbase;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import keywords.ApplicationKeywords;
import reports.ExtentManager;
import runner.DataUtil;
import runner.ReadingXLS;

public class BaseTest {
	
	public ApplicationKeywords app;
	public int i ;
	public ExtentReports rep;
	public ExtentTest test;
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context) throws NumberFormatException, IOException, ParseException 
	{
		System.out.println("----------------Before Test--------------------");
		//what is the path to data json/excel
		//what is the data flag
		//what is the iteration number
		//read the data and keep it in a map
		String dataflag = context.getCurrentXmlTest().getParameter("dataflag");
		String datafilePath = context.getCurrentXmlTest().getParameter("datafilePath");
		String iteration = context.getCurrentXmlTest().getParameter("iteration");
		String sheetName = context.getCurrentXmlTest().getParameter("suitename");
		
//		JSONObject data = new DataUtil().getTestData(datafilePath, dataflag, Integer.parseInt(iteration));
		JSONObject data = new ReadingXLS().getTestData(sheetName, dataflag, Integer.parseInt(iteration)+1,datafilePath);
		
		
		String runmode = (String) data.get("runmode");
		context.setAttribute("data", data);
		
		
		
		
		app = new ApplicationKeywords(); // 1 app keyword object for entire test
		context.setAttribute("app", app);
		
		rep = ExtentManager.getReports();
		test=rep.createTest(context.getCurrentXmlTest().getName());
		test.log(Status.INFO, "Starting Test "+context.getCurrentXmlTest().getName());
		context.setAttribute("report", rep);
		context.setAttribute("test", test);
		if(runmode.equals("N")) 
		{
			test.log(Status.SKIP, "Skipping Test as Data Runmode is N");
			throw new SkipException("Skipping Test as Data Runmode is N");
		}
		app.setReport(test);
		
//		context.setAttribute("report", rep);
//		context.setAttribute("test", test);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) 
	{
		System.out.println("=============Before Method=============");
		app = (ApplicationKeywords) context.getAttribute("app");
		rep = (ExtentReports) context.getAttribute("report");
		test =(ExtentTest) context.getAttribute("test");
	}
	
	@AfterTest(alwaysRun = true)
	public void quit(ITestContext context) 
	{
		System.out.println("=============After Test=============");
		app= (ApplicationKeywords) context.getAttribute("app");
		if(app!=null) 
		{
			app.quit();
		}
		rep= (ExtentReports) context.getAttribute("report");
		if(rep!=null) 
		{
			rep.flush();
		}
	}
	
	
}
