package com.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.entity.*;
import com.entity.Html.BrowserHtml;
import com.excel.read.LoginUser;
import com.log.Log;

public   class BrowserCommon {

	    BrowserCommon browser;
	    FirefoxProfile profile;
	    WebDriver driver; 
	 
   public   BrowserCommon()
	{
		System.setProperty(BrowserHtml.Firefox.PRO_BIN_NAME, BrowserHtml.Firefox.PRO_BIN_VALUE);
	    FirefoxProfile profile=new FirefoxProfile();
	    driver=new FirefoxDriver(profile);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	    
	}
   
   
   
	
   
    
	public   void LoadPage(String Url)
	{
		Log.info("     LoadPage:"+Url);
		driver.navigate().to(Url);
	}
	
	
	public   void quit()
	{
		 driver.quit();
	}
	
	 
	
	public   WebElement findElementByName(String vName)
	{
		Log.info("     findElementByName:"+vName);
		return driver.findElement(By.name(vName));
	}
	
	public   WebElement findElementById(String vId)
	{
		Log.info("     findElementById:"+vId);
		return driver.findElement(By.id(vId));
	}
	
	public   WebElement findElementByCss(String vCss)
	{
		Log.info("     findElementByCss:"+vCss);
		return driver.findElement(By.cssSelector(vCss));
	}
	
	public    WebElement findElementByClass(String vClass)
	{
		Log.info("     findElementByClass:"+vClass);
		return driver.findElement(By.className(vClass));
	}
	
	public   WebElement findElementByXPath(String vXPath)
	{
		Log.info("     findElementByXPath:"+vXPath);
		return driver.findElement(By.xpath(vXPath));
	}
	
	public   void displaySetNoneToBlok(String eleName)
	{
		Log.info("     displaySetNoneToBlok:"+eleName);
		String js="document.getElementById('"+eleName+"').style.display='block';";
		((JavascriptExecutor)driver).executeScript(js);  
	}
}
