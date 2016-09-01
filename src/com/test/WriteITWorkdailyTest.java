package com.test;

import java.io.IOException;
import java.sql.Driver;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import com.browser.*;
import  com.entity.*;
import com.entity.Html.BrowserHtml;
import com.entity.Html.ITHtml;
import com.excel.read.LoginUser;
import com.excel.read.WorkRecordList;


public class WriteITWorkdailyTest {
	
	BrowserCommon browser;
	WorkRecordList record;
    LoginUser user;
	
	@BeforeTest
	public void setUp() 
	{
		browser=new BrowserCommon(BrowserHtml.URL_IT_WORK_LOAD); 
	    record=new WorkRecordList(); 
        user=new LoginUser(ITHtml.Login.JUS_IT_USER_TYPE);   
	}

	@AfterTest
	public void tearDown()
	{
	  // browser.quit();
	}
	
	@Test(priority=2)
	public void itWorkLoadTest() throws InterruptedException
	{
		 login();
		 print(record.size());
		 for(int i=0;i<record.size();i++)
		 { 
		 goToNewEdit();
		 newWorkLoadSubmit(i); 
		 Thread.sleep(2000);
		 }
	}
	
	
	public void  login()
	{
		WebElement eleName=browser.findElementByName(ITHtml.Login.TXT_N_USER_NAME);
		WebElement elePass=browser.findElementByName(ITHtml.Login.TXT_N_PASSWORD);
		WebElement eleSubmit=browser.findElementById(ITHtml.Login.BTN_I_SUBMIT);
		eleName.sendKeys(user.record.username);
		elePass.sendKeys(user.record.password);
		eleSubmit.click();
	}
	
	
	
	public void goToNewEdit()
	{
		WebElement eleNew=browser.findElementByCss(ITHtml.BTN_C_NEW_EDIT);  
		eleNew.click();
	}
	
	
	
	public void newWorkLoadSubmit(int i)
	{
		
		 
		
		WebElement eleWorkDate=browser.findElementByName(ITHtml.NewDaily.TXT_N_WORK_DATE);
		WebElement eleHours=browser.findElementByName(ITHtml.NewDaily.TXT_N_HOURS);
		WebElement eleWorkBlock=browser.findElementById(ITHtml.NewDaily.SEL_I_WORK_BLOCK); 
		WebElement eleWorkType=browser.findElementById(ITHtml.NewDaily.SEL_I_WORK_TYPE); 
		WebElement eleWorkContent=browser.findElementByName(ITHtml.NewDaily.TXT_N_WORK_CONTENT); 
		WebElement eleWorkResult=browser.findElementByName(ITHtml.NewDaily.TXT_N_WORK_RESULT); 
		WebElement eleWorkRole=browser.findElementById(ITHtml.NewDaily.SEL_I_WORK_ROLE); 
		WebElement eleWorkCycle=browser.findElementById(ITHtml.NewDaily.SEL_I_WORK_CYCLE);
		WebElement eleSubmit=browser.findElementByCss(ITHtml.NewDaily.BTN_C_NEW_SUBMIT); 
		
		eleWorkDate.clear();
		eleWorkDate.sendKeys(record.L(i).workDate);  
		eleHours.clear();
		eleHours.sendKeys(record.L(i).workHours);  
		 
		Select selWorkBlock=new Select(eleWorkBlock); 
    	selWorkBlock.selectByVisibleText(record.L(i).workBlock); 
  
    	browser.displaySetNoneToBlok(ITHtml.NewDaily.SEL_I_WORK_SUBBLOCK);
		WebElement eleWorkSubBlock=browser.findElementById(ITHtml.NewDaily.SEL_I_WORK_SUBBLOCK); 
		Select selWorkSubBlock=new Select(eleWorkSubBlock);   
		selWorkSubBlock.selectByVisibleText(record.L(i).workSubBlock); 
 
		Select selWorkType=new Select(eleWorkType); 
	    selWorkType.selectByVisibleText(record.L(i).workType); 
	     
	    browser.displaySetNoneToBlok(ITHtml.NewDaily.SEL_I_WORK_SUBTYPE);
		WebElement eleWorkSubType=browser.findElementById(ITHtml.NewDaily.SEL_I_WORK_SUBTYPE); 
		Select selWorkSubType=new Select(eleWorkSubType); 
	 	selWorkSubType.selectByVisibleText(record.L(i).workSubType); 
		 
		if(record.L(i).workType.equals(ITHtml.NewDaily.JUS_NEED_WORK_FROM)) {
		 WebElement eleWorkFrom=browser.findElementById(ITHtml.NewDaily.SEL_I_WORK_FROM);   
		 Select selWorkFrom=new Select(eleWorkFrom);  
		 selWorkFrom.selectByVisibleText(record.L(i).workFrom); 
		}
		
		
		eleWorkContent.clear();
		eleWorkContent.sendKeys(record.L(i).workContent);  
		eleWorkResult.clear();
		eleWorkResult.sendKeys(record.L(i).workResult); 
		 
		Select selWorkRole=new Select(eleWorkRole);
		selWorkRole.selectByVisibleText(record.L(i).workRole); 
	   
		Select selWorkCycle=new Select(eleWorkCycle);
		selWorkCycle.selectByVisibleText(record.L(i).workCycle); 
	  
    	eleSubmit.click(); 
		
	}
	
	
	
	
	public void print(WebElement element)
	{
		System.out.println("Print:"+element.getText()+element.getClass()+"\n");
	}
	
	public void print(int s)
	{
		System.out.println("Print:"+ s+"\n");
	}
	
	public void print(String s)
	{
		System.out.println("Print:"+ s+"\n");
	}
}
