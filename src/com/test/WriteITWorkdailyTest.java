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
import com.excel.read.WorkRecord;
import com.excel.read.WorkRecordList;


public class WriteITWorkdailyTest {
	
//	BrowserCommon browser;
//	WorkRecordList record;
    LoginUser user;
	
	@BeforeTest
	public void setUp() 
	{
		Browser.Instance().LoadPage(BrowserHtml.URL_IT_WORK_LOAD); 
	  //  record=new WorkRecordList(); 
        user=new LoginUser(ITHtml.Login.JUS_IT_USER_TYPE);   
	}

	@AfterTest
	public void tearDown()
	{
	  // Browser.Instance().quit();
	}
	
	@Test(priority=2)
	public void itWorkLoadTest() throws InterruptedException
	{
		 login();
		 print(WorkRecord.instance().size());
		 for(int i=0;i<WorkRecord.instance().size();i++)
		 { 
		 goToNewEdit();
		 newWorkLoadSubmit(i); 
		 Thread.sleep(2000);
		 }
	}
	
	
	public void  login()
	{
		WebElement eleName=Browser.Instance().findElementByName(ITHtml.Login.TXT_N_USER_NAME);
		WebElement elePass=Browser.Instance().findElementByName(ITHtml.Login.TXT_N_PASSWORD);
		WebElement eleSubmit=Browser.Instance().findElementById(ITHtml.Login.BTN_I_SUBMIT);
		eleName.sendKeys(user.record.username);
		elePass.sendKeys(user.record.password);
		eleSubmit.click();
	}
	
	
	
	public void goToNewEdit()
	{
		WebElement eleNew=Browser.Instance().findElementByCss(ITHtml.BTN_C_NEW_EDIT);  
		eleNew.click();
	}
	
	
	
	public void newWorkLoadSubmit(int i)
	{
		
		 
		
		WebElement eleWorkDate=Browser.Instance().findElementByName(ITHtml.NewDaily.TXT_N_WORK_DATE);
		WebElement eleHours=Browser.Instance().findElementByName(ITHtml.NewDaily.TXT_N_HOURS);
		WebElement eleWorkBlock=Browser.Instance().findElementById(ITHtml.NewDaily.SEL_I_WORK_BLOCK); 
		WebElement eleWorkType=Browser.Instance().findElementById(ITHtml.NewDaily.SEL_I_WORK_TYPE); 
		WebElement eleWorkContent=Browser.Instance().findElementByName(ITHtml.NewDaily.TXT_N_WORK_CONTENT); 
		WebElement eleWorkResult=Browser.Instance().findElementByName(ITHtml.NewDaily.TXT_N_WORK_RESULT); 
		WebElement eleWorkRole=Browser.Instance().findElementById(ITHtml.NewDaily.SEL_I_WORK_ROLE); 
		WebElement eleWorkCycle=Browser.Instance().findElementById(ITHtml.NewDaily.SEL_I_WORK_CYCLE);
		WebElement eleSubmit=Browser.Instance().findElementByCss(ITHtml.NewDaily.BTN_C_NEW_SUBMIT); 
		
		eleWorkDate.clear();
		eleWorkDate.sendKeys(WorkRecord.instance().L(i).workDate);  
		eleHours.clear();
		eleHours.sendKeys(WorkRecord.instance().L(i).workHours);  
		 
		Select selWorkBlock=new Select(eleWorkBlock); 
    	selWorkBlock.selectByVisibleText(WorkRecord.instance().L(i).workBlock); 
  
    	Browser.Instance().displaySetNoneToBlok(ITHtml.NewDaily.SEL_I_WORK_SUBBLOCK);
		WebElement eleWorkSubBlock=Browser.Instance().findElementById(ITHtml.NewDaily.SEL_I_WORK_SUBBLOCK); 
		Select selWorkSubBlock=new Select(eleWorkSubBlock);   
		selWorkSubBlock.selectByVisibleText(WorkRecord.instance().L(i).workSubBlock); 
 
		Select selWorkType=new Select(eleWorkType); 
	    selWorkType.selectByVisibleText(WorkRecord.instance().L(i).workType); 
	     
	    Browser.Instance().displaySetNoneToBlok(ITHtml.NewDaily.SEL_I_WORK_SUBTYPE);
		WebElement eleWorkSubType=Browser.Instance().findElementById(ITHtml.NewDaily.SEL_I_WORK_SUBTYPE); 
		Select selWorkSubType=new Select(eleWorkSubType); 
	 	selWorkSubType.selectByVisibleText(WorkRecord.instance().L(i).workSubType); 
		 
		if(WorkRecord.instance().L(i).workType.equals(ITHtml.NewDaily.JUS_NEED_WORK_FROM)) {
		 WebElement eleWorkFrom=Browser.Instance().findElementById(ITHtml.NewDaily.SEL_I_WORK_FROM);   
		 Select selWorkFrom=new Select(eleWorkFrom);  
		 selWorkFrom.selectByVisibleText(WorkRecord.instance().L(i).workFrom); 
		}
		
		
		eleWorkContent.clear();
		eleWorkContent.sendKeys(WorkRecord.instance().L(i).workContent);  
		eleWorkResult.clear();
		eleWorkResult.sendKeys(WorkRecord.instance().L(i).workResult); 
		 
		Select selWorkRole=new Select(eleWorkRole);
		selWorkRole.selectByVisibleText(WorkRecord.instance().L(i).workRole); 
	   
		Select selWorkCycle=new Select(eleWorkCycle);
		selWorkCycle.selectByVisibleText(WorkRecord.instance().L(i).workCycle); 
	  
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
