package com.excel.read;

import jxl.*;
import jxl.read.biff.BiffException; 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import  com.entity.*;
import com.excel.common.*;
import com.log.Log;


public class LoginUser extends ExcelReadTemplate<LoginUserEntity>  {
 
	
	public LoginUser()
	{
		record=new LoginUserEntity();
		GetData();
	}
	
	
	public void GetData() {
	 
		Sheet sheet= CWorkbook.Instance().getSheet(BrowserCommonEntity.SHEET_NAME_LOGIN_USER); 
		for(int i =1;i<sheet.getRows();i++)
		{ 
			if(sheet.getCell(0, i).getContents()!=null)
			{ 
			record.username=sheet.getCell(0, i).getContents().trim();
		    record.password =  sheet.getCell(1, i).getContents().trim();
		    record.print();
			} 
	    }
		 
	}
	 
 
	}
