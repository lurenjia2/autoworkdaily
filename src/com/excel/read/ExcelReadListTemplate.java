package com.excel.read;

import jxl.*;
import jxl.read.biff.BiffException; 

import java.io.File;
import java.io.IOException;
import java.util.List;

import  com.entity.*;
import com.excel.common.*;
import com.log.*;

public abstract class ExcelReadListTemplate<T> {

	 
	public List<T>  recordlist;
	
	public ExcelReadListTemplate() { } 
	
	public abstract T  L(int i);
	public abstract  void GetData() throws IndexOutOfBoundsException, BiffException, IOException;
	
	
	public int size()
	{
		return recordlist.size();
	}
	 
	
	public boolean isEmpty()
	{
		if(recordlist==null || recordlist.size()==0) return true;
		return false;
	}
	
	 
}
