package com.excel.read;

import jxl.*;
import jxl.read.biff.BiffException; 

import java.io.File;
import java.io.IOException;
import java.util.List;

import  com.entity.*;
import com.excel.common.*;


public abstract class ExcelReadListTemplate<T> {

	public CWorkbook book ;
	public List<T>  recordlist;
	
	public ExcelReadListTemplate()
	{
		 
	} 
	
	public abstract T  L(int i);
	public abstract  void GetData() throws IndexOutOfBoundsException, BiffException, IOException;
	
	
	
	
	public void  setPath(String v_path)  
	{
		book=new CWorkbook(v_path);
	}
	
	public boolean isEmpty()
	{
		if(recordlist==null || recordlist.size()==0) return true;
		return false;
	}
	
	
	 
	
	public Sheet getSheet(int i) throws IndexOutOfBoundsException, BiffException, IOException
	{
		 if(book==null) return null;
		 return book.Get().getSheet(i);
	}
	
	public Sheet getSheet(String sheetname) throws IndexOutOfBoundsException, BiffException, IOException
	{
		 if(book==null) return null;
		 return book.Get().getSheet(sheetname);
	}
	
	public Sheet getSheet() throws IndexOutOfBoundsException, BiffException, IOException
	{
		 if(book==null) return null;
		 return book.Get().getSheet(0);
	}
	

	
}
