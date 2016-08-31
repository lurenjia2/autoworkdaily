package com.excel.read;

import jxl.*;
import jxl.read.biff.BiffException; 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import  com.entity.*;
import com.excel.common.*;


public class LoginUser extends ExcelReadTemplate<LoginUserEntity>  {
 
	
	public LoginUser()
	{
		super.setPath(BrowserCommonEntity.PATH_WORK_DAILY);
		super.record=new LoginUserEntity();
	}
	
	
	public void GetData() throws IndexOutOfBoundsException, BiffException, IOException {
	
		Sheet sheet=getSheet(BrowserCommonEntity.SHEET_NAME_LOGIN_USER);
		for(int i =1;i<sheet.getRows();i++)
		{
			record.username=sheet.getCell(0, i).getContents();
		    record.password =  sheet.getCell(1, i).getContents();
	    }
	}
	}
