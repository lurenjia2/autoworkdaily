package com.test;
 
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.entity.BrowserCommonEntity;
import  com.excel.read.*;

public class WorkRecordListTest {
	
	@BeforeTest
	public void setUp()
	{
    	
	}
	
	@AfterTest
	public void tearDown()
	{
		
	}
	
	@Test
	public void runExcelTest() throws IndexOutOfBoundsException, BiffException, IOException
	{
		 WorkRecordList record=new WorkRecordList();
         record.GetData();
       for(int i=0;i<  record.recordlist.size();i++)
       {
    	   record.recordlist.get(i).print();
       }
       
	}
}
