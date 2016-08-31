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

public   class BrowserCommon {

	 FirefoxProfile profile;
	 public WebDriver driver; 
	 public LoginUserEntity user;
	public BrowserCommon()
	{
		System.setProperty(BrowserCommonEntity.Firefox.PRO_BIN_NAME, BrowserCommonEntity.Firefox.PRO_BIN_VALUE);
	    FirefoxProfile profile=new FirefoxProfile();
	    driver=new FirefoxDriver(profile);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    user=new LoginUserEntity();
	    LoadPage();
	}
	
	public void LoadPage()
	{
		driver.navigate().to(BrowserCommonEntity.URL_LOAD_PAGE);
	}
	
	public WebElement findElementByName(String vName)
	{
		return driver.findElement(By.name(vName));
	}
	
	public WebElement findElementById(String vId)
	{
		return driver.findElement(By.id(vId));
	}
	
	public WebElement findElementByCss(String vCss)
	{
		return driver.findElement(By.cssSelector(vCss));
	}
	
	
	public void displaySetNoneToBlok(String eleName)
	{
		String js="document.getElementById('"+eleName+"').style.display='block';";
		((JavascriptExecutor)driver).executeScript(js);  
	}
}
