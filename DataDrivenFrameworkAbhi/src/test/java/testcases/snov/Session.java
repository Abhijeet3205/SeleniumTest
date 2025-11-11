package testcases.snov;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import keywords.ApplicationKeywords;
import testbase.BaseTest;

public class Session extends BaseTest{
	
	@Test
	public void doLogin(ITestContext context) 
	{
		System.out.println("Logging In");
//		ApplicationKeywords app = new ApplicationKeywords();
//		app.openBrowser("Chrome");
//		app.navigate("");
//		app = (ApplicationKeywords) context.getAttribute("app");
		test.log(Status.INFO, "Logging in");
		app.openBrowser("Chrome");
	}
	
	@Test
	public void doLogout() 
	{
//		ApplicationKeywords app = new ApplicationKeywords();
		System.out.println("Logging Out");
//		app.click("logout");
	}

}
