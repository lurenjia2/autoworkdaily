package com.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.entity.*;

public   class BrowserCommon {

	 FirefoxProfile profile;
	 public WebDriver driver; 
	 public WorkUserProfile user;
	public BrowserCommon()
	{
		System.setProperty("webdriver.firefox.bin", "G:\\workspace\\firefox-sdk\\bin\\firefox.exe");
	    FirefoxProfile profile=new FirefoxProfile();
	    driver=new FirefoxDriver(profile);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    SetWorkUserProfile();
	}
	
	
	
	public void  SetWorkUserProfile()
	{
		user=new WorkUserProfile();
		user.username="cozhongzhiheng";
		user.password="pass1234";
		
		user.elementPassword="password";
		user.elementUsername="loginname";
		user.btnSubmit="to-recover";
		
	}
}
