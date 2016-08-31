package com.excel.read;

import java.io.IOException;
import java.util.List;

import jxl.Sheet;
import jxl.read.biff.BiffException;

import com.entity.BrowserCommonEntity;
import com.entity.LoginUserEntity;
import com.excel.common.CWorkbook;
import com.log.*;

public abstract class ExcelReadTemplate<T> {
 
	public  T record;
	  
	public ExcelReadTemplate() {   
		 
	} 
	  
	 
	public abstract  void GetData() throws IndexOutOfBoundsException, BiffException, IOException;
	
}
