package espire.utilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import TestCases.TestBaseClass;

public class Screenshotcapture extends TestBaseClass
{

	public Screenshotcapture(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public static void captureAsImage(WebDriver driver,String screenShotName) throws IOException, HeadlessException, AWTException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest =System.getProperty("user.dir")+ "\\Screenshots\\"+screenShotName+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
	}
}



