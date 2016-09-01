package com.excel.read;

import jxl.*;
import jxl.read.biff.BiffException; 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import  com.entity.*;
import com.entity.Html.BrowserHtml;
import com.excel.common.*;
import com.log.Log;

public class WorkDayList extends ExcelReadListTemplate<WorkDayEntity> {

	public WorkDayList()
	{ 
		 recordlist=new ArrayList<WorkDayEntity>();
		 GetData();
	}
	
	public void  GetData(){
		Sheet sheet= CWorkbook.Instance().getSheet(BrowserHtml.SHEET_NAME_WORK_DAY);
		for(int i =1;i<sheet.getRows();i++)
		{
			if(sheet.getCell(0, i).getContents()!=null)
			{
				WorkDayEntity entity=new WorkDayEntity();
			    entity.day =  sheet.getCell(0, i).getContents().trim(); 
			    recordlist.add(entity);
		    	entity.print();
			}
		}
	}

	public WorkDayEntity  L(int i) { 
		 return recordlist.get(i);
	}
	
	public boolean  In(String s)
	{
		for(int i=0;i<recordlist.size();i++)
		{ 
			if(recordlist.get(i).day.equals(s))
			{
				return true;
			}
		}
		return false;
		 
	}
}
