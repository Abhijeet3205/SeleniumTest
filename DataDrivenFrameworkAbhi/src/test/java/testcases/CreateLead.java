package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import keywords.ApplicationKeywords;
import keywords.GenericKeywords;

public class CreateLead {
	
	@Test
	public void createLead() throws InterruptedException 
	{
		ApplicationKeywords app = new ApplicationKeywords();
		app.openExistingBrowser("Chrome");
		app.navigate("url");
		app.click("usernametxtbox_xpath");
		app.clearText("usernametxtbox_xpath");
		Thread.sleep(2000);
		app.click("passwordtxtbox_xpath");
		app.clearText("passwordtxtbox_xpath");
//		app.type("usernametxtbox_xpath", "usernametxtboxdata");
//		app.type("passwordtxtbox_xpath", "passwordtxtboxdata");
//		boolean result=app.validateElementPresent("//button[@class='btn btn-primary btn-primary--login']");
//		System.out.println("result in CreateLead "+result);
//		if(!result)
//		{
//			System.out.println("Test Case Failed11");
//			Assert.fail();
//		}
//		app.click("//button[@class='btn btn-primary btn-primary--login']");
	}

}
