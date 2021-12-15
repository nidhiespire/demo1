package homeSolution;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestCases.TestBaseClass;
import espire.utilities.CommonUtilities;

public class home extends TestBaseClass

{

	CommonUtilities cu=new CommonUtilities();

	public home()
	{
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="SUPPORT")
	WebElement home;
	public void clickhomesolution()
	{
		try 
		{
//			Actions a=new Actions(driver);
//			a.moveToElement(home).click().build();
			//home.click();
			CommonUtilities ut=new CommonUtilities();
			ut.javascriptClick(home, driver);
			
											
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			System.out.println("Exception"+e);
		}
		
	}
}
	
	
