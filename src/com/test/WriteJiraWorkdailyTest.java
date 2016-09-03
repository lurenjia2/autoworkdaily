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
import com.entity.Html.*; 
import com.excel.read.*; 
import com.log.Log;


public class WriteJiraWorkdailyTest {
     //BrowserCommon browser;
   //   WorkRecordList record;
      LoginUser user;
      
      @BeforeTest
      public void setUp()
      {
    	  Browser.Instance().LoadPage(BrowserHtml.URL_JIRA); 
    	//  record=new WorkRecordList();
    	  user=new LoginUser(JiraHtml.Login.JUS_JIRA_USER_TYPE);
      }
      
      @AfterTest
      public void tearDown()
      {
    	  
      }
      
      @Test(priority=1)
      public void JiraWorkLoadTest() throws InterruptedException
      {
    	  login(); 
    	  for(int i=0;i<WorkRecord.instance().size();i++)
    	  {
    		  goToTaskItem(i);
    		  newWorkLoadSubmit(i); 
    		  Thread.sleep(2000);
    	  }
    	
      }
      
      
      public void login()
      {
    	  WebElement eleName=Browser.Instance().findElementByName(JiraHtml.Login.TXT_N_USER_NAME);
    	  WebElement elePass=Browser.Instance().findElementByName(JiraHtml.Login.TXT_N_PASSWORD);
    	  WebElement eleSubmit=Browser.Instance().findElementById(JiraHtml.Login.BTN_I_SUBMIT);
    	  eleName.sendKeys(user.record.username);
    	  elePass.sendKeys(user.record.password);
    	  eleSubmit.click(); 
      }
      
      
      public void goToTaskItem(int i)
      {
    	  String url=BrowserHtml.URL_JIRA_TO_TASK+WorkRecord.instance().L(i).jiraTaskItem;
    	  Browser.Instance().LoadPage(url); 
    	  
    	  WebElement eleMore=Browser.Instance().findElementById(JiraHtml.LINK_I_MORE_OPRATOR);
    	  eleMore.click();
    	  WebElement eleNew=Browser.Instance().findElementById(JiraHtml.LINK_I_NEW_EDIT);
    	  eleNew.click();
      }
      
      public void newWorkLoadSubmit(int i)
  	  {
        WebElement eleWorkDate=Browser.Instance().findElementById(JiraHtml.NewDaily.TXT_I_WORK_DATE);
  		WebElement eleHours=Browser.Instance().findElementById(JiraHtml.NewDaily.TXT_I_HOURS);
  	//	WebElement eleWorkContent=Browser.Instance().findElementByClass(JiraHtml.NewDaily.TXT_A_WORK_CONTENT);
  		WebElement eleWorkContent=Browser.Instance().findElementByXPath(JiraHtml.NewDaily.TXT_X_WORK_CONTENT);
   	    WebElement eleSubmit=Browser.Instance().findElementById(JiraHtml.NewDaily.BTN_I_NEW_SUBMIT); 
  		
  	    
  		eleHours.clear();
  		eleHours.sendKeys(WorkRecord.instance().L(i).workHours);  
  		Log.print("newWorkLoadSubmit1");
  		eleWorkDate.clear();
  		Log.print("newWorkLoadSubmit11");
  		eleWorkDate.sendKeys(WorkRecord.instance().L(i).jiraWorkDate);  
  		Log.print("newWorkLoadSubmit2"); 
  		 eleWorkContent.clear();
  		Log.print("newWorkLoadSubmit21");
  		eleWorkContent.sendKeys(WorkRecord.instance().L(i).workContent+WorkRecord.instance().L(i).workResult); 
  		Log.print("newWorkLoadSubmit3");
  		eleSubmit.click();
  		Log.print("newWorkLoadSubmit4");
  	  }
}
