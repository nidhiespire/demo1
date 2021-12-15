package tests;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import org.testng.annotations.Test;

import TestCases.TestBaseClass;
import espire.utilities.ConfigReader;

public class TC_HomeSolPS_002 extends TestBaseClass


{
	
ConfigReader config=new ConfigReader();
	
	//@Parameters("browser")
	@Test(enabled=true)
	public void HOMEPS() throws InterruptedException, HeadlessException, IOException, AWTException
	{
	
		homeSolution.home h=new homeSolution.home();
		h.clickhomesolution();
		espire.utilities.Screenshotcapture.captureAsImage(driver,"homeso2");
		
		
		
		
	  
	}
	
}
	