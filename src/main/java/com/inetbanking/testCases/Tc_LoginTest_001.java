package com.inetbanking.testCases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;

public class Tc_LoginTest_001 extends BaseClass {

	 @Test
	 public void loginTest() throws IOException
	 {
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		Logger.info("Username is entered ");
		
		lp.setPassword(password);
		Logger.info("Password is entered");
		
		lp.clickSubmit();
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			Logger.info("Login Test is passed");
		}
		else
		{ 
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			Logger.info("Login Test is Failed");
			
		}
		
		
		 
	 }
		 
	 
	
	
}
