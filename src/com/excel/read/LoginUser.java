package com.excel.read;

import jxl.*;
import jxl.read.biff.BiffException; 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import  com.entity.*;
import com.entity.Html.BrowserHtml; 
import com.entity.Html.ITHtml;
import com.excel.common.*;
import com.log.Log;


public class LoginUser extends ExcelReadTemplate<LoginUserEntity>  {
 
	
	public LoginUser(String vUserType)
	{
		record=new LoginUserEntity();
		GetData(vUserType);
	}
	
	
	public void GetData(String vUserType) {
	 
		Sheet sheet= CWorkbook.Instance().getSheet(BrowserHtml.SHEET_NAME_LOGIN_USER); 
		for(int i =1;i<sheet.getRows();i++)
		{ 
		    String	tempName=sheet.getCell(0, i).getContents().trim();
		    String tempUserType=sheet.getCell(2, i).getContents().trim();
		    String tempStatus=sheet.getCell(3, i).getContents().trim();
		    Log.print("tempName:"+tempName+",tempUserType:"+tempUserType+
		    		  ",tempStatus:"+tempStatus+",vUserType:"+vUserType
		    		  +",Status:"+BrowserHtml.STATUS_USABLE_SIGN);
			if(tempName!=null && 
		       tempUserType.equals(vUserType) &&
		       tempStatus.equals(BrowserHtml.STATUS_USABLE_SIGN)
		      )
			{ 
				 
			record.username=tempName;
		    record.password =sheet.getCell(1, i).getContents().trim();
		    record.usertype=tempUserType;
		    record.print();
			} 
	    }
		 
	}
	 
 
	}
