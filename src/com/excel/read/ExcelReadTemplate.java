package com.excel.read;

import java.io.IOException;
import java.util.List;

import jxl.Sheet;
import jxl.read.biff.BiffException;

import com.excel.common.CWorkbook;

public abstract class ExcelReadTemplate<T> {
	public CWorkbook book ;
	public  T record;
	 
	 
	public ExcelReadTemplate()
	{
		 
	} 
	
	public void  setPath(String v_path)  
	{
		book=new CWorkbook(v_path);
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
	
	public abstract  void GetData() throws IndexOutOfBoundsException, BiffException, IOException;
	
}
