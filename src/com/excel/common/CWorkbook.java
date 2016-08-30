package com.excel.common;

import java.io.File;
import java.io.IOException;

import jxl.*;  
import jxl.read.biff.BiffException;

public class CWorkbook  {

	Workbook book ;
	String path;
	public CWorkbook(String v_path)  
	{
		//book =Workbook.getWorkbook(new File(v_path));
		path=v_path;
	}
	
	public Workbook Get() throws BiffException, IOException
	{
		if(book==null) {
		    book =Workbook.getWorkbook(new File(path));
		}
		return book;
	}
	
	
	public Sheet GetSheet(int i)
	{
		return book.getSheet(i);
	}
}
