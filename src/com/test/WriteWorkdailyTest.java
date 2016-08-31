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
import com.excel.read.LoginUser;
import com.excel.read.WorkRecordList;


public class WriteWorkdailyTest {
	
	BrowserCommon browser;
	WorkRecordList record;
    LoginUser user;
	
	@BeforeTest
	public void setUp() 
	{
		browser=new BrowserCommon(); 
	    record=new WorkRecordList(); 
        user=new LoginUser();   
	}

	@AfterTest
	public void tearDown()
	{
	  // browser.quit();
	}
	
	@Test
	public void runTest() throws InterruptedException
	{
		 login();
		 print(record.size());
		 for(int i=0;i<record.size();i++)
		 { 
		 toNewEdit();
		 fixTextSubmit(i); 
		 Thread.sleep(5000);
		 }
	}
	
	
	public void  login()
	{
		WebElement eleName=browser.findElementByName(HtmlElementEntity.LoginEntity.TXT_N_USER_NAME);
		WebElement elePass=browser.findElementByName(HtmlElementEntity.LoginEntity.TXT_N_PASSWORD);
		WebElement eleSubmit=browser.findElementById(HtmlElementEntity.LoginEntity.BTN_I_SUBMIT);
		eleName.sendKeys(user.record.username);
		elePass.sendKeys(user.record.password);
		eleSubmit.click();
	}
	
	
	
	public void toNewEdit()
	{
		WebElement eleNew=browser.findElementByCss(HtmlElementEntity.BTN_C_NEW_EDIT);  
		eleNew.click();
	}
	
	
	
	public void fixTextSubmit(int i)
	{
		
		 
		
		WebElement eleWorkDate=browser.findElementByName(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_DATE);
		WebElement eleHours=browser.findElementByName(HtmlElementEntity.NewDailyEntity.TXT_N_HOURS);
		WebElement eleWorkBlock=browser.findElementById(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_BLOCK); 
		WebElement eleWorkType=browser.findElementById(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_TYPE); 
		WebElement eleWorkContent=browser.findElementByName(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_CONTENT); 
		WebElement eleWorkResult=browser.findElementByName(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_RESULT); 
		WebElement eleWorkRole=browser.findElementById(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_ROLE); 
		WebElement eleWorkCycle=browser.findElementById(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_CYCLE);
		WebElement eleSubmit=browser.findElementByCss(HtmlElementEntity.NewDailyEntity.BTN_C_NEW_SUBMIT); 
		
		eleWorkDate.clear();
		eleWorkDate.sendKeys(record.L(i).work_date);  
		eleHours.clear();
		eleHours.sendKeys(record.L(i).work_hours);  
		 
		Select selWorkBlock=new Select(eleWorkBlock); 
    	selWorkBlock.selectByVisibleText(record.L(i).work_block); 
  
    	browser.displaySetNoneToBlok(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_SUBBLOCK);
		WebElement eleWorkSubBlock=browser.findElementById(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_SUBBLOCK); 
		Select selWorkSubBlock=new Select(eleWorkSubBlock);   
		selWorkSubBlock.selectByVisibleText(record.L(i).work_sub_block); 
 
		Select selWorkType=new Select(eleWorkType); 
	    selWorkType.selectByVisibleText(record.L(i).work_type); 
	     
	    browser.displaySetNoneToBlok(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_SUBTYPE);
		WebElement eleWorkSubType=browser.findElementById(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_SUBTYPE); 
		Select selWorkSubType=new Select(eleWorkSubType); 
	 	selWorkSubType.selectByVisibleText(record.L(i).work_sub_type); 
		 
		if(record.L(i).work_type.equals(HtmlElementEntity.NewDailyEntity.JUS_NEED_WORK_FROM)) {
		 WebElement eleWorkFrom=browser.findElementById(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_FROM);   
		 Select selWorkFrom=new Select(eleWorkFrom);  
		 selWorkFrom.selectByVisibleText(record.L(i).work_from); 
		}
		
		
		eleWorkContent.clear();
		eleWorkContent.sendKeys(record.L(i).work_content);  
		eleWorkResult.clear();
		eleWorkResult.sendKeys(record.L(i).work_result); 
		 
		Select selWorkRole=new Select(eleWorkRole);
		selWorkRole.selectByVisibleText(record.L(i).work_role); 
	   
		Select selWorkCycle=new Select(eleWorkCycle);
		selWorkCycle.selectByVisibleText(record.L(i).work_cycle); 
	  
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
