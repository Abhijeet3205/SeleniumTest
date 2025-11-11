package testcases.snov;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import keywords.ApplicationKeywords;
import testbase.BaseTest;

public class LeadManagement extends BaseTest{

	@Test
	public void createLead(ITestContext context) throws InterruptedException 
	{
		System.out.println("Creating Deal");
		i = 100;
//		ApplicationKeywords app = (ApplicationKeywords) context.getAttribute("app");
		
////		app.navigate("url");
		
//		app.reportFailure("some non critical failure", false);
//		app.assertAll();
		
		JSONObject data = (JSONObject) context.getAttribute("data");
		String currency = (String) data.get("currency");
		String value = (String) data.get("value");
		String prospectname = (String) data.get("prospectname");
		String email = (String) data.get("email");
		String prospectlist = (String) data.get("prospectlist");
		
//		app.click("usernametxtbox_xpath");
//		Thread.sleep(2000);
//		app.clearText("usernametxtbox_xpath");
//		app.type("usernametxtbox_xpath","usernamedata");
//		
//		app.click("passwordtxtbox_xpath");
//		Thread.sleep(2000);
//		app.clearText("passwordtxtbox_xpath");		
//		app.type("passwordtxtbox_xpath","passworddata");
//		
//		app.takeScreenShot();
		
//		boolean result = app.validateElementPresent("loginsubmit_xpath");
//		if(!result) 
//		{
//			// fail
//			Assert.fail();
//		}
		app.click("loginsubmit_xpath");
//		boolean result1 = app.validateText("checkLeadsText", "leadstext_xpath");
//		if(!result1) 
//		{
//			// fail
//			System.out.println("Test Case Failed - Text Did Not Matched");
//			Assert.fail();
//		}
		
		app.moveToElement("crmlink_xpath");
		app.click("deallink_xpath");
		app.click("createDealBtn_xpath");
		
		
		app.moveToElement("valueTextBox_xpath");

		app.click("valueTextBox_xpath");
		app.click("downArrowBtn_xpath");
		app.type("searchCurrency_xpath", "currencyWrite_xpath");
		app.click("selectSearchCurrency_xpath");
		//app.click("valueTextBox_xpath");
//		Thread.sleep(3000);
//		app.click("valueTextBox_xpath");
		app.type("valueNewCurrency_xpath", "valueWrite_xpath");
		app.type("prospectName_xpath", "prospectNameVal_xpath");
		app.click("prospectEmailHover_xpath");
		app.type("prospectEmailHover_xpath", "prospectEmailVal_xpath");
//		app.type("linkedInProfile_xpath", "linkedInProfileVal_xpath");
		app.click("prospectlist_xpath");
		app.type("prospectlist_xpath", "prospectlistVal_xpath");
		app.click("prospectSelectText_xpath");
//		app.click("createDealSabeBtn_xpath");
		
		//  //div[normalize-space()='Prospect1']
		//  //div[normalize-space()='Prospect list']
		
//		app.click("dealslistview_xpath");
//		boolean result2 = app.validateText("createdealbtnText", "dealText_xpath");
//		System.out.println("result2 is: "+result2);
//		if(!result2) 
//		{
//			// fail
//			System.out.println("Test Case Failed - Text Did Not Matched");
//			Assert.fail();
//		}
//		
	}
	
	
	@Test
	public void deleteLead(ITestContext context) 
	{
		System.out.println("Deleting Deal");
		app.navigate("url");
//		ApplicationKeywords app = new ApplicationKeywords();
	}
	
	@Test
	public void verifyLead() 
	{
		System.out.println("In Verify Lead");
	}
}
