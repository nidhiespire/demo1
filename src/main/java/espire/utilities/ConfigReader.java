package espire.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;
	
	/**
	 * Read the data from the config.properties file
	 */
	
	public ConfigReader() {
			
		try
		{
			prop = new Properties();
			//FileInputStream fis = new FileInputStream("./Configurations/config.properties");
			FileInputStream fis = new FileInputStream("./Configurations/config2.properties");
			prop.load(fis);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception is "+e.getMessage());
		} 
	}
	
	/**
	 * Get the URL data from the config.properties file.
	 * @return
	 */
	
	public String getURL()
	{
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	/**
	 * Get the PS Username from the config.properties.
	 * @return
	 */
	
	public String getUsername()
	{
		String username= prop.getProperty("username");
		return username;
	}
	
	/**
	 * Get the password from the config.properties.
	 * @return
	 */
	
	public String getPassword()
	{
		String password=prop.getProperty("password");
		System.out.println("password"+password);
		return password;
	}
	
	
	
}
