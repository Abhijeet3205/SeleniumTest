package keywords;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;

public class GenericKeywords {
	public WebDriver driver = null;
	public Properties prop;
	public Properties envProp;
	public ExtentTest test;
	public SoftAssert softAssert;

	public void openBrowser (String browserName) {
		test.log(Status.INFO, "Opening Browser: "+browserName);
		if(browserName.equals("Chrome")) 
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			options.addArguments("ignore-certificate-errors");
			driver = new ChromeDriver(options);
		}
		else if(browserName.equals("Firefox")) 
		{
			FirefoxOptions options = new FirefoxOptions();
//			ProfilesIni allProf = new ProfilesIni();
//			FirefoxProfile prof = allProf.getProfile("qtpselenium");
			FirefoxProfile prof = new FirefoxProfile();
			prof.setPreference("dom.webnotifications.enabled", false);
			prof.setAcceptUntrustedCertificates(true);
			prof.setAssumeUntrustedCertificateIssuer(false);
			options.setProfile(prof);
			driver = new FirefoxDriver(options);
		}
		else if(browserName.equals("Edge")) 
		{
			driver = new EdgeDriver();
		}
	}
	
	public void openExistingBrowser (String browserName) {
		test.log(Status.INFO, "Opening Existing Browser: "+browserName);
		if(browserName.equals("Chrome")) 
		{
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9090");
			driver = new ChromeDriver(options);
		}
		else if(browserName.equals("Firefox")) 
		{
			FirefoxOptions options = new FirefoxOptions();
//			options.add
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("Edge")) 
		{
			driver = new EdgeDriver();
		}
	}

	public void navigate(String urlKey) 
	{
		test.log(Status.INFO, "Navigating to website: "+envProp.getProperty(urlKey));
		driver.get(envProp.getProperty(urlKey));
	}
	public void click(String locator) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getElement(locator).click();
	}
	public void clearText(String locatorKey) 
	{		
		WebElement element = getElement(locatorKey);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    element.sendKeys(Keys.CONTROL + "a"); // Select all text
	    element.sendKeys(Keys.DELETE); 
	}
	public void type(String locatorKey, String dataKey) 
	{
		if(!isElementPresent(locatorKey)) 
		{
			//report failure
		}
		if(!isElementVisible(locatorKey)) 
		{
			// report failure
		}
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getElement(locatorKey).sendKeys(envProp.getProperty(dataKey));
	}
	public void select(String locator) 
	{
		
	}
	public String getText(String locatorKey) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String text1= driver.findElement(getLocator(locatorKey)).getText();
		System.out.println("text1 is: "+text1);
		return text1;
	}
	public void moveToElement(String locatorKey) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement e=getElement(locatorKey);
		Actions ac=new Actions(driver);
		ac.moveToElement(e).perform();
	}
	
	// extract element
	public WebElement getElement(String locatorKey) 
	{
		// present
		// visibility
		if(!isElementPresent(locatorKey)) 
		{
			//report failure
		}
		if(!isElementVisible(locatorKey)) 
		{
			// report failure
		}		
		WebElement e = driver.findElement(getLocator(locatorKey));	
		return e;
	}
	
	public boolean isElementVisible(String locatorKey) 
	{
		System.out.println("isElementVisible " +locatorKey);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
			
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
	
	public boolean isElementPresent(String locatorKey) 
	{
		System.out.println("isElementPresent " +locatorKey);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try 
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));					
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception iss: "+e.getMessage());
			return false;
		}
		System.out.println("result is: "+true);
		return true;
	}
	
	public By getLocator(String locatorKey) 
	{
		By by = null;
		
		if(locatorKey.endsWith("_xpath")) 
		{
			by = By.xpath(envProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_name")) 
		{
			by = By.name(envProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_id")) 
		{
			by = By.id(envProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_css")) 
		{
			by = By.cssSelector(envProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_class")) 
		{
			by = By.className(envProp.getProperty(locatorKey));
		}
		
		return by;
	}
	
	// reporting function
	
	public void log(String msg) 
	{
		test.log(Status.INFO, msg);
	}
	
	public void reportFailure(String failureMsg, boolean stopOnFailure) 
	{
		softAssert.fail(failureMsg);
		takeScreenShot();
		test.log(Status.FAIL, failureMsg); // failure in extent reports
		softAssert.fail(failureMsg); // failure in TestNG reports
		
		if(stopOnFailure) 
		{
			assertAll();
		}
	}
	
	public void assertAll() 
	{
		softAssert.assertAll();
	}
	
	public void waitForPageToLoad() 
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i =0;
		//ajax status 
		while(i!=10) 
		{
			String state= (String) js.executeScript("return document.readyState;");
			if(state.equals("complete")) 
			{
				break;
			}
			else 
			{
				try {
					wait(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		}
		//check for jquery status
		i=0;
		while(i!=10) 
		{
			Long d = (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue()==0) 
			{
				break;
			}
			else 
			{
				try {
					wait(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void takeScreenShot(){
		Date d= new Date();
		String screenshotFile = d.toString().replaceAll(":", "_").replaceAll(" ", "_")+".png";
		// take screenshot- java object
		// save screenshot in path
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		// get the dynamic folder name
		FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+"//"+ screenshotFile));
		//test.addScreenCaptureFromPath("path of image", "xxxx");
		test.log(Status.INFO, "Screenshot: "+test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+ screenshotFile));
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

	}
	
	
	public void getElementScreenshot(WebElement  ele, String filePath)
    {
			// Get entire page screenshot
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			BufferedImage fullImg;
			try {
			fullImg = ImageIO.read(screenshot);
			// Get the location of element on the page , 100,150
			Point point = ele.getLocation();
			
			// Get width and height of the element  -50,100
			int eleWidth = ele.getSize().getWidth();
			int eleHeight = ele.getSize().getHeight();
			
			// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
			   eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, "png", screenshot);
			
			// Copy the element screenshot to disk
			File screenshotLocation = new File(filePath);
			FileUtils.copyFile(screenshot, screenshotLocation);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			
       
    }
	
	public void quit() 
	{
		driver.quit();
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
