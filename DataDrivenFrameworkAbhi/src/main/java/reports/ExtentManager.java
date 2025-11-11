package reports;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	static ExtentReports reports;
	public static String screenshotFolderPath;
	
	public static ExtentReports getReports() 
	{
		if(reports == null)
		{
			Date d = new Date();
			String path1 = d.toString().replaceAll(":", "_").replaceAll(" ", "_");
			String path = System.getProperty("user.dir");
			reports = new ExtentReports();
			ExtentSparkReporter sparkreporter = new ExtentSparkReporter(path+"\\reports\\"+path1+"\\");
			screenshotFolderPath = path+"\\reports\\"+path1+"\\" +"\\screenshots";
			File f = new File(screenshotFolderPath);
			f.mkdirs();
			sparkreporter.config().setReportName("Testing of Website");
			sparkreporter.config().setDocumentTitle("AutomationReports");
			sparkreporter.config().setTheme(Theme.DARK);
			reports.attachReporter(sparkreporter);
		}
		
		
		return reports;
	}
	


}
