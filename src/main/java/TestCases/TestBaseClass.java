package TestCases;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
//import org.apache.log4j.Logger;
import espire.utilities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import espire.utilities.CommonUtilities;
import espire.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

/**
 * This is the Base class for the framework.
 * @author HP
 *
 */
public class TestBaseClass 
{
int i;
	ConfigReader config=new ConfigReader();
	public String baseURL =config.getURL();
	public static SoftAssert assertt = new SoftAssert();
	public static WebDriver driver;
	
	boolean status = false;
	public static Logger logger=LogManager.getLogger(TestBaseClass.class);
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	
	/**
	 * Launch multiple browser(Chrome, Edge, FireFox and Safari)
	 * @param br
	 * @throws InterruptedException
	 */
	
	public void setUp(String br) throws InterruptedException
	{
	//	logger=LogManager.getLogger(TestbaseClass.class);
		System.out.println("@BeforeClass" +br);
		if(br.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			logger.info("Chrome Browser is Open");
		
		}
		else if(br.equalsIgnoreCase("FireFox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			Thread.sleep(1500);
			logger.info("FireFox Browser is Open");
		}
		else if(br.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			Thread.sleep(1500);
			logger.info("Edge is Open");
		}
		else if(br.equalsIgnoreCase("Safari"))
		{
			
			//WebDriverManager.sa
			driver=new SafariDriver();
		}
		else
		{
			ChromeOptions chp=new ChromeOptions();
			//chp.addArguments("--diable-gpu");
			//chp.addArguments("disable-feature=NetworkService");
			WebDriverManager.chromedriver().setup();
			//WebDriverManager.chromedriver().forceDownload().setup();
			
			driver=new ChromeDriver(chp);
		}
		logger.info("URL is Open");
		driver.get(baseURL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	}
	
	
	
//	@AfterMethod(alwaysRun = true)
//	public void tearDown()
//	{
//		System.out.println("@AfterClass");
//		driver.quit();
//	}
	// Taking Screen shot on test fail
    @AfterMethod
    public void screenshot(ITestResult result) throws HeadlessException, IOException, AWTException
    {
               i = i+1;
               String name = "ScreenShot";
               String x = name+String.valueOf(i);
              if(ITestResult.FAILURE == result.getStatus())
                {
                             
            	  espire.utilities.Screenshotcapture.captureAsImage(driver, x);
                }
                 }
	
	
	/*@AfterTest(alwaysRun = true)
	public void teardown()
	{
		driver.close();
	}*/
}
