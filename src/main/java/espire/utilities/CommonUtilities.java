package espire.utilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import com.assertthat.selenium_shutterbug.utils.*;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.google.common.base.Function;

import TestCases.TestBaseClass;

public class CommonUtilities extends TestBaseClass
{
	public String failed_Message = "";
	static JavascriptExecutor js = (JavascriptExecutor)driver;

	/**
	 * Click on OK button of regular popup
	 * @param alertDriver
	 */
	
	public void acceptAlert(WebDriver alertDriver)
	{
		try
		{
			Alert alert = alertDriver.switchTo().alert();
			alert.accept();
		}
		catch(Exception ex)
		{
			logger.error("No alert Found");
		}
	}

	/**
	 * Dismiss the regular popup
	 * @param alertDriver
	 */
	
	public void dismissAlert(WebDriver alertDriver)
	{
		try
		{
			alertDriver.switchTo().alert().dismiss();
			logger.info("Alert dismiass");
		}catch(Exception ex)
		{
			logger.error("No alert Found");
		}
	}

	/**
	 * Click on element using JavascriptExecutor
	 * @param ele
	 * @param driver
	 */
	
	public void javascriptClick(WebElement ele, WebDriver driver) 
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);

	}

	/**
	 * Close irregular pop up using locator
	 * @param locator
	 * @return
	 */
	
	public static boolean ValidateAndPopUp(String locator)
	{
		boolean data=false;
		try
		{
			Thread.sleep(15000);

			WebElement ele = driver.findElement(By.xpath(locator));

			//*[@id='861c5dc3-bdbe-477f-97b2-c5b619f59cc2']
			WebDriverWait wait = new WebDriverWait(driver, 180);
			wait.until(ExpectedConditions.visibilityOf(ele));
			ele.click();
			System.out.println(true);
			return true;
		} 
		catch (Exception e)
		{
			System.out.println(false);
			return data;
		}
	}


	/**
	 * Change the driver to the current window
	 * @param driver
	 */

	public static void changewindow(WebDriver driver)
	{
		String parentwindow= driver.getWindowHandle();
		Set<String> s1 =driver.getWindowHandles();
		Iterator<String> i1 =s1.iterator();
		while(i1.hasNext())
		{
			String child_window = i1.next();
			if(!parentwindow.equalsIgnoreCase(child_window))
			{
				driver.switchTo().window(child_window);
				logger.info("Switch to Window:"+child_window);
			}
		}

	}

	/**
	 * Page down
	 * @param driver
	 */
	
	public static void pagedown(WebDriver driver)
	{
		try
		{
			js.executeScript("window.scrollBy(0,250)");
			//js.executeScript("scroll(0, 250);");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * Scroll the page till the specific element
	 * @param el
	 * @param driver
	 */
	
	public void scrollToElement(WebElement el, WebDriver driver)
	{
		js.executeScript("arguments[0].scrollIntoView(true);", el);
	}
	

	/**
	 * Scroll Down the screen using x, y coordinate
	 * @param driver
	 */
	
	public void scrollDown(WebDriver driver)
	{
		
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,10000);");
		js.executeScript("window.scrollBy(0,500)","");
	}

	
	/**
	 * Scroll up the screen using x, y coordinate.
	 * @param driver
	 */
	public void scrollUp(WebDriver driver)
	{
		js.executeScript("window.scrollBy(0,-10000);");
	}

	
	/**
	 * Wait For Element to Load
	 * @param driver
	 * @param locator
	 * @param Timeout
	 */


	public static void waitForElementtoLoad(WebDriver driver, WebElement locator, int Timeout)
	{
		new WebDriverWait(driver, Timeout).until(ExpectedConditions.visibilityOf(locator));
		//locator.sendKeys(value);
		
	}
	
	
	/**
	 * Wait until Element to be Clickable
	 * @param driver
	 * @param element
	 * @param timeout
	 */
	
	public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeout)
	{
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	/**
	 * Wait until page loaded
	 * @param driver
	 */
	
	public static void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
		//new WebDriverWait(driver, );
	}
	
	/**
	 * Fluent wait implementation
	 * @param driver
	 * @param locator
	 * @param timeOutInSeconds
	 * @param pooltimeOutInSeconds
	 */
	
	public static void fluentlyWaitForElementToLoad(WebDriver driver, WebElement locator)
	{
		// Waiting [timeOutInSeconds] seconds for an element to be present on the page, checking
		// for its presence once every [pooltimeOutInSeconds] seconds.	
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50000))
				.pollingEvery(Duration.ofMillis(2000)).ignoring(NoSuchWindowException.class);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	

	
	/**
	 * For Getting the WebElement.
	 * @param by
	 * @return
	 */
	
	public WebElement getWebElement(final By by)
	{
		try 
		{
			return driver.findElement(by);
		} 
		catch (Exception e)
		{
			return null;
			// TODO: handle exception
		}
	}

	/**
	 * For Taking Screenshot
	 * @param driver
	 * @param screenShotName
	 * @throws IOException
	 * @throws HeadlessException
	 * @throws AWTException
	 */
	
	public static void captureAsImage(WebDriver driver,String screenShotName) throws IOException, HeadlessException, AWTException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest =System.getProperty("user.dir")+"\\Screenshots\\"+screenShotName+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

	}
	
	/**
	 *Selecting the Value from the list visible text.
	 * @param visibletxt
	 */
	
	public static void selectDropDownByVisibleText(WebElement element, String visibletxt)
	{
		try 
		{
			if(visibletxt!=null)
			{
				Select select=new Select(element);
				select.selectByVisibleText(visibletxt);
				//select.selectByValue(visibletxt);;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Getting the value from element
	 * @param locator
	 * @return
	 */
	
	public boolean isdisplay(String locator)
	{
		WebElement error = driver.findElement(By.xpath(locator));
		boolean result= error.isDisplayed();
		return result;
	}
	

	/**
	 * Getting Data From the Excel
	 * @param sTestCaseID
	 * @param sheet
	 * @return
	 * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 */

	public static String[] toReadExcelData(String sTestCaseID,String sheet) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		String sData[]=null;
		try
		{
			FileInputStream fis = new FileInputStream(new File(""));
			Workbook wb = WorkbookFactory.create(fis);
			org.apache.poi.ss.usermodel.Sheet sht = wb.getSheet(sheet);

			int iRowNum= sht.getLastRowNum();


			for(int i=1; i<=iRowNum; i++)
			{
				if(sht.getRow(i).getCell(0).toString().equals(sTestCaseID))

				{
					int iCellNum=sht.getRow(i).getLastCellNum();
					sData= new String[iCellNum];

					for(int j=0; j<iCellNum; j++)
					{
						DataFormatter formatter = new DataFormatter();
						org.apache.poi.ss.usermodel.Cell cell = sht.getRow(i).getCell(j);
						sData[j] = formatter.formatCellValue(cell);

						//sData[j]=sht.getRow(i).getCell(j).getStringCellValue();
					}
					break;
				}
			}

		}
		catch(EncryptedDocumentException ex){
			ex.printStackTrace();
		}
		catch(InvalidFormatException ex){
			ex.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return sData;
	}


}
