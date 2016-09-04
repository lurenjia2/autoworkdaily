package com.excel.read;
 
import java.util.*;

import jxl.Sheet;
import com.entity.*;
import com.entity.Html.BrowserHtml;
import com.excel.common.CWorkbook;
import com.log.Log;

public class LoginUserMap   {

	private static LoginUserMap userMap;
	private static Map map;
	private LoginUserMap()
	{
		map=new HashMap();
		GetData();
	}
	
	
	public static Map Instance()
	{
		if(userMap==null)
		{
			userMap=new LoginUserMap();
		}
		return map;
	}
	
	
	
	private  void GetData()
	{
		Sheet sheet= CWorkbook.Instance().getSheet(BrowserHtml.SHEET_NAME_LOGIN_USER); 
		for(int i =1;i<sheet.getRows();i++)
		{ 
		    String	tempName=sheet.getCell(0, i).getContents().trim();
		    String tempUserType=sheet.getCell(2, i).getContents().trim();
		    String tempStatus=sheet.getCell(3, i).getContents().trim();
		    Log.print("tempName:"+tempName+",tempUserType:"+tempUserType+
		    		  ",tempStatus:"+tempStatus 
		    		  +",Status:"+BrowserHtml.STATUS_USABLE_SIGN);
			if(tempName!=null &&  
		       tempStatus.equals(BrowserHtml.STATUS_USABLE_SIGN)
		      )
			{ 
		    LoginUserEntity  record=new LoginUserEntity();
			record.username=tempName;
		    record.password =sheet.getCell(1, i).getContents().trim();
		    record.usertype=tempUserType;
		    record.print();
		    if(map.get(record.usertype)==null)
		    {
		       map.put(record.usertype, record);
		    }
			} 
	    }
	}
}
