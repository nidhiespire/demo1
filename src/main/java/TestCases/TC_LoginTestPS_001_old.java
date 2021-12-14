package TestCases;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import LoginModule.LoginPage;
import espire.utilities.CommonUtilities;
import espire.utilities.ConfigReader;

public class TC_LoginTestPS_001_old extends TestBaseClass
{
	ConfigReader config=new ConfigReader();
	
	//@Parameters("browser")
	@Test(enabled=true)
	public void loginTestPS() throws InterruptedException
	{
		LoginPage lp = new LoginPage();
	     /*click on icon and sign*/
		
		lp.clickaccount();
		lp.sig();
		
	 
		
		/*enterusername and pass*/
		System.out.println("LoginPage");
		
		System.out.println(config.getUsername());
	
		lp.setUserName(config.getUsername());
		Thread.sleep(1000);
	
		lp.setPassword(config.getPassword());
		Thread.sleep(1000);

		lp.clickOnSubmit();
		;
	
	}
	
}
	
//@Test(enabled=true)
//	public void loginTestAO() throws InterruptedException
//	{
//		System.out.println("LoginPage");
//		LoginPage lp = new LoginPage();
//		System.out.println(config.getLusername());
//		lp.setUserName(config.getUsername());
////		Thread.sleep(2000);
////		lp.clickOnNext();
////		lp.setPassword(config.getPassword());
////		lp.clickOnSubmit();
////
////	}
//}

