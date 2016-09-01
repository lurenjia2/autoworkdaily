package com.excel.common;

import java.io.File;
import java.io.IOException;

import com.entity.Html.BrowserHtml;
import com.log.Log;

import jxl.*;  
import jxl.read.biff.BiffException;

public class CWorkbook  {
 
static	CWorkbook Cbook;
static 	Workbook book ; 
	private CWorkbook()  
	{ 
		Get();
	}
	
  
	public static CWorkbook Instance()
	{
		if(Cbook==null)
		{
			Cbook=new CWorkbook();
			Cbook.Get();
		}
		return Cbook;
	}
	private   void  Get() 
	{
		 try{ 
	    book =Workbook.getWorkbook(new File(BrowserHtml.PATH_WORK_DAILY));  
		 }catch(Exception e)
		 {
			 Log.info(e.toString());
			  
		 }
	}
//	
//	
//	public static Workbook Get(String filename) 
//	{
//		 try{
//	    book =Workbook.getWorkbook(new File(filename)); 
//		return book;
//		 }catch(Exception e)
//		 {
//			 Log.info(e.toString());
//			 return null;
//		 }
//	}
	  
	
	public static  Sheet getSheet(int i)  
	{ 
		try { 
		 return  book.getSheet(i); 
		}catch(Exception e) {
			 Log.info(e.toString()); 
			 return null;
		}  
	}
	
	public static  Sheet getSheet(String sheetname)  
	{
		try { 
			 return  book.getSheet(sheetname); 
			}catch(Exception e) {
				 Log.info(e.toString()); 
				 return null;
			}  
	}
	
	public static  Sheet getSheet()  
	{
		try { 
			 return  book.getSheet(0); 
			}catch(Exception e) {
				 Log.info(e.toString()); 
				 return null;
			}  
	}
	
	
	
	
}
