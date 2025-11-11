package keywords;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords {
	
	public ApplicationKeywords() 
	{
		String path = System.getProperty("user.dir")+"//src//test//resources//env.properties";
		prop = new Properties();
		envProp = new Properties();
		try 
		{
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);
			String environment = prop.getProperty("env")+".properties";
			path = System.getProperty("user.dir")+"//src//test//resources//"+environment;
			fs = new FileInputStream(path);
			envProp.load(fs);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(envProp.getProperty("url"));
		System.out.println(envProp.getProperty("username"));
		softAssert = new SoftAssert();
	}
	public void login() 
	{

	}

	public void selectDateFromCalendar() 
	{
	}

	public void verifyDealadded() 
	{
		
	}
	
	public void setReport(ExtentTest test) 
	{
		this.test=test;
	}

}
