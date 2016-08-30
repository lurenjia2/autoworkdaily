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

import com.browser.*;
import  com.entity.*;
import com.excel.read.WorkRecordList;


public class WriteWorkdailyTest {
	
	BrowserCommon browser;
	WorkRecordList record;
	
	
	@BeforeTest
	public void setUp() throws IndexOutOfBoundsException, BiffException, IOException
	{
		browser=new BrowserCommon();
		browser.driver.navigate().to("http://it-workload.gf.com.cn");
		
		//初始化工作日志数据
		 String  s ="G:\\workspace\\AutoWrokDaily\\workdaily.xls";
         record=new WorkRecordList(s);
         record.GetData();
	}

	@AfterTest
	public void tearDown()
	{
	  // browser.driver.quit();
	}
	
	@Test
	public void runTest() throws InterruptedException
	{
		 login();
		 print(record.recordlist.size());
		 for(int i=0;i<record.recordlist.size();i++)
		 {
		 toNewEdit();
		 fixTextSubmit(i);
		 Thread.sleep(5);
		 }
	}
	
	
	public void  login()
	{
		WebElement eleName=browser.driver.findElement(By.name(browser.user.elementUsername));
		WebElement elePass=browser.driver.findElement(By.name(browser.user.elementPassword));
		WebElement eleSubmit=browser.driver.findElement(By.id(browser.user.btnSubmit));
		eleName.sendKeys(browser.user.username);
		elePass.sendKeys(browser.user.password);
		eleSubmit.click();
	}
	
	
	
	public void toNewEdit()
	{
		WebElement eleNew=browser.driver.findElement(By.cssSelector(HtmlElementEntity.BTN_C_NEW_EDIT));  
		eleNew.click();
	}
	
	
	
	public void fixTextSubmit(int i)
	{
		WebElement eleWorkDate=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_DATE));
		eleWorkDate.clear();
		eleWorkDate.sendKeys(record.recordlist.get(i).work_date); 
		
		WebElement eleHours=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_HOURS));
		eleHours.clear();
		eleHours.sendKeys(record.recordlist.get(i).work_hours);  
		
		WebElement eleWorkBlock=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_BLOCK)); 
	//	eleWorkBlock.click(); 
		Select selWorkBlock=new Select(eleWorkBlock);
	//	selWorkBlock.selectByIndex(1);
		selWorkBlock.selectByVisibleText(record.recordlist.get(i).work_block); 
		
		WebElement eleWorkSubBlock=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_SUBBLOCK));
		eleWorkSubBlock.click();
		Select selWorkSubBlock=new Select(eleWorkSubBlock);
		selWorkSubBlock.selectByIndex(1);
		
		WebElement eleWorkType=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_TYPE)); 
		eleWorkType.click();
		Select selWorkType=new Select(eleWorkType);
	    selWorkType.selectByIndex(1); 
		
		WebElement eleWorkSubType=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_SUBTYPE));
		eleWorkSubType.click();
		Select selWorkSubType=new Select(eleWorkSubType);
		selWorkSubType.selectByIndex(21);
		
		WebElement eleWorkContent=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_CONTENT));
		eleWorkContent.clear();
		eleWorkContent.sendKeys(record.recordlist.get(i).work_content); 
		
		WebElement eleWorkResult=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_RESULT));
		eleWorkResult.clear();
		eleWorkResult.sendKeys(record.recordlist.get(i).work_result); 
		
		WebElement eleWorkRole=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_ROLE));
		eleWorkRole.click();
		Select selWorkRole=new Select(eleWorkRole);
		selWorkRole.selectByVisibleText(record.recordlist.get(i).work_role); 
	
		WebElement eleWorkCycle=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_CYCLE));
		eleWorkCycle.click(); 
		Select selWorkCycle=new Select(eleWorkCycle);
		selWorkCycle.selectByVisibleText(record.recordlist.get(i).work_cycle); 
		
		WebElement eleSubmit=browser.driver.findElement(By.cssSelector(HtmlElementEntity.NewDailyEntity.BTN_C_NEW_SUBMIT)); 
		eleSubmit.click();
		
		record.recordlist.get(i).print();
		
	
	
	
		//eleWorkBlock.sendKeys(record.recordlist.get(i).work_block);  
	//	eleWorkSubBlock.sendKeys(record.recordlist.get(i).work_sub_block);  
	//	eleWorkType.sendKeys(record.recordlist.get(i).work_type); 
	//	eleWorkSubType.sendKeys(record.recordlist.get(i).work_sub_type); 
		 
		 
	//	selWorkBlock.selectByVisibleText(record.recordlist.get(i).work_block);   
	//	selWorkSubBlock.selectByVisibleText(record.recordlist.get(i).work_sub_block); 
	//	selWorkType.selectByVisibleText(record.recordlist.get(i).work_type); 
	//	selWorkSubType.selectByVisibleText(record.recordlist.get(i).work_sub_type); 
		
		 
		
	
		
		
		
		
	//	eleWorkRole.sendKeys(record.recordlist.get(i).work_role); 
	//	eleWorkCycle.sendKeys(record.recordlist.get(i).work_cycle); 
		
		
			
		
	}
	
	
	
	
	public void print(WebElement element)
	{
		System.out.println("Print:"+element.getText()+element.getClass()+"\n");
	}
	
	public void print(int s)
	{
		System.out.println("Print:"+ s+"\n");
	}
	
}
