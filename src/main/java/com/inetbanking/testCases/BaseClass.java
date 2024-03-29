package com.inetbanking.testCases;
import java.io.File;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	
	
	public String baseUrl = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger Logger;

	
	
	@Parameters("browser")
	
	@BeforeClass
	public void Setup(String br)
	
	{
		Logger = org.apache.log4j.Logger.getLogger("eBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		
		if(br.equals("chrome"))
		{	
		System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
		driver = new ChromeDriver();
		}
		else if (br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxpath());
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		Logger.info("URL is opened");
		
	} 
	
	
	@AfterClass
	public void tearDown ()
	{
		driver.quit();
		
	}
	
	
	public void captureScreen(WebDriver driver , String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver ; 
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screen-shot Taken");
	}
	
	public String randomString()
	{
	 String Generatedstring =	RandomStringUtils.randomAlphabetic(8);
	 return(Generatedstring);
	 
	}
	
	
}
