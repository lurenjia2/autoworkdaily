package com.excel.read;

import jxl.*;
import jxl.read.biff.BiffException; 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import  com.entity.*;
import com.excel.common.*;


public class WorkRecordList extends ExcelReadListTemplate<WorkRecordEntity> {

	 
	//public List<WorkRecordEntity>  recordlist;
	public WorkDayList daylist;
	public WorkRecordList()
	{ 
		 recordlist=new ArrayList<WorkRecordEntity>();
		 daylist=new WorkDayList();
		 GetData();
	}
	
	public void  GetData(){
		Sheet sheet= CWorkbook.Instance().getSheet(BrowserCommonEntity.SHEET_NAME_IT_WORK_LIST);
		for(int i =1;i<sheet.getRows();i++)
		{
			if(sheet.getCell(0, i).getContents()!=null)
			{
				if(	daylist.In(sheet.getCell(0, i).getContents()))
	           {
			WorkRecordEntity entity=new WorkRecordEntity();
			entity.work_date =  sheet.getCell(0, i).getContents().trim();
			entity.work_hours =  sheet.getCell(1, i).getContents().trim();
			entity.work_block =  sheet.getCell(2, i).getContents().trim();
			entity.work_sub_block =  sheet.getCell(3, i).getContents().trim();
			entity.work_type =  sheet.getCell(4, i).getContents().trim();
			entity.work_sub_type =  sheet.getCell(5, i).getContents().trim();
			entity.work_content =  sheet.getCell(6, i).getContents().trim();
			entity.work_result =  sheet.getCell(7, i).getContents().trim();
			entity.work_role =  sheet.getCell(8, i).getContents().trim();
			entity.work_cycle =  sheet.getCell(9, i).getContents().trim();
			entity.remark =  sheet.getCell(10, i).getContents().trim();
			entity.work_from=sheet.getCell(11, i).getContents().trim();
			entity.print(); 
			recordlist.add(entity); 
		} 
			}
		}
	}

	public WorkRecordEntity  L(int i) { 
		 return recordlist.get(i);
	}
	
	
}
