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
		 Thread.sleep(5000);
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
		
		
//		WebElement eleWorkBlock=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.SEL_N_WORK_BLOCK)); 
//		WebElement eleWorkSubBlock=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.SEL_N_WORK_SUBBLOCK));
   //	WebElement eleWorkType=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.SEL_N_WORK_TYPE)); 
//		WebElement eleWorkSubType=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.SEL_N_WORK_SUBTYPE));
//		WebElement eleWorkFrom=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.SEL_N_WORK_TYPE)); 
 	//	WebElement eleWorkRole=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.SEL_N_WORK_ROLE));		
//		WebElement eleWorkCycle=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.SEL_N_WORK_CYCLE));
		
		
		WebElement eleWorkDate=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_DATE));
		WebElement eleHours=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_HOURS));
		WebElement eleWorkBlock=browser.driver.findElement(By.id(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_BLOCK)); 
		WebElement eleWorkType=browser.driver.findElement(By.id(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_TYPE)); 
		
		
		WebElement eleWorkContent=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_CONTENT)); 
		WebElement eleWorkResult=browser.driver.findElement(By.name(HtmlElementEntity.NewDailyEntity.TXT_N_WORK_RESULT)); 
		WebElement eleWorkRole=browser.driver.findElement(By.id(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_ROLE)); 
		WebElement eleWorkCycle=browser.driver.findElement(By.id(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_CYCLE));
		WebElement eleSubmit=browser.driver.findElement(By.cssSelector(HtmlElementEntity.NewDailyEntity.BTN_C_NEW_SUBMIT)); 
		eleWorkDate.clear();
		eleWorkDate.sendKeys(record.recordlist.get(i).work_date); 
		
		
		eleHours.clear();
		eleHours.sendKeys(record.recordlist.get(i).work_hours);  
		
	
	
  
		Select selWorkBlock=new Select(eleWorkBlock); 
    	selWorkBlock.selectByVisibleText(record.recordlist.get(i).work_block); 
 
    	
		String js="document.getElementById('select_subplate_0').style.display='block';";
		 ((JavascriptExecutor)browser.driver).executeScript(js);  
		WebElement eleWorkSubBlock=browser.driver.findElement(By.id(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_SUBBLOCK)); 
		Select selWorkSubBlock=new Select(eleWorkSubBlock);   
		selWorkSubBlock.selectByVisibleText(record.recordlist.get(i).work_sub_block); 

	 
		Select selWorkType=new Select(eleWorkType); 
	    selWorkType.selectByVisibleText(record.recordlist.get(i).work_type); 
	    
	 
	    js="document.getElementById('select_workType_sub_0').style.display='block';";
		 ((JavascriptExecutor)browser.driver).executeScript(js); 
		WebElement eleWorkSubType=browser.driver.findElement(By.id(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_SUBTYPE)); 
		Select selWorkSubType=new Select(eleWorkSubType); 
	 	selWorkSubType.selectByVisibleText(record.recordlist.get(i).work_sub_type); 
		
	 	print(record.recordlist.get(i).work_block);
	 	print(HtmlElementEntity.NewDailyEntity.JUS_NEED_WORK_FROM);
		if(record.recordlist.get(i).work_type.equals(HtmlElementEntity.NewDailyEntity.JUS_NEED_WORK_FROM)) {
		 WebElement eleWorkFrom=browser.driver.findElement(By.id(HtmlElementEntity.NewDailyEntity.SEL_I_WORK_FROM));   
		 Select selWorkFrom=new Select(eleWorkFrom);  
		 selWorkFrom.selectByVisibleText(record.recordlist.get(i).work_from); 
		 print(1111);
		}
		
		eleWorkContent.clear();
		eleWorkContent.sendKeys(record.recordlist.get(i).work_content); 
		
		eleWorkResult.clear();
		eleWorkResult.sendKeys(record.recordlist.get(i).work_result); 
		 
		Select selWorkRole=new Select(eleWorkRole);
		selWorkRole.selectByVisibleText(record.recordlist.get(i).work_role); 
	   
		Select selWorkCycle=new Select(eleWorkCycle);
		selWorkCycle.selectByVisibleText(record.recordlist.get(i).work_cycle); 
	  
    //	eleSubmit.click();
		
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
	
	public void print(String s)
	{
		System.out.println("Print:"+ s+"\n");
	}
}
