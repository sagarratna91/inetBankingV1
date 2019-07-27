 package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;
import com.inetbanking.utilities.XLUtils;

import junit.framework.Assert;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginDDT(String user , String pwd) throws InterruptedException{
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		Logger.info("User-name is provided");
		lp.setPassword(pwd);
		Logger.info("Pass-word is provided");
		lp.clickSubmit();
		Thread.sleep(3000);
		
		if(isAlertPresent() == true)
		{
		
			driver.switchTo().alert().accept(); // Close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			
			Logger.warn("Login FAILED");
		}
		else
		{
			Assert.assertTrue(true);
			Logger.warn("Login PASSED");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); // Close alert
			driver.switchTo().defaultContent();
			
		}
		
	}
	
	public boolean isAlertPresent() // this is user defined method created to check alert is present or NOT 
	{
		try {
	   driver.switchTo().alert();
	   return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	
	@DataProvider(name="LoginData")
	String[][]  getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/main/java/com/inetbanking/testData/LoginData.xlsx";
	
				
				int rownum= XLUtils.getRowCount(path, "Sheet1");
				int cocount = XLUtils.getCellCount(path,"Sheet1",1);
				
				String logindata[][] = new String[rownum][cocount];
				
				for(int i=1;i<=rownum;i++)
				{
					for(int j=0;j<cocount;j++)
					{
						logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1",i,j);
						
					}
						
				}
			return logindata;	
				
	}
}
