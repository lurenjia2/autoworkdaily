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


public class WorkRecordList extends ExcelReadListTemplate<ITWorkRecordEntity> {

	 
	//public List<WorkRecordEntity>  recordlist;
	public List<JiraWorkRecordEntity> jiraList; 
	public WorkDayList daylist;
	public WorkRecordList()
	{ 
		 recordlist=new ArrayList<ITWorkRecordEntity>();
		 jiraList=new ArrayList<JiraWorkRecordEntity>();
		 daylist=new WorkDayList();
		 GetData();
		 GetJiraData();
	}
	
	public void  GetData(){
		Sheet sheet= CWorkbook.Instance().getSheet(BrowserHtml.SHEET_NAME_IT_WORK_LIST);
		for(int i =1;i<sheet.getRows();i++)
		{
			String tempDate=sheet.getCell(0, i).getContents().trim(); 
			if(tempDate!=null)
			{
				if(	daylist.In(tempDate))
	           {
			ITWorkRecordEntity entity=new ITWorkRecordEntity();
			entity.workDate =  tempDate;
			entity.workHours =  sheet.getCell(1, i).getContents().trim();
			entity.workBlock =  sheet.getCell(2, i).getContents().trim();
			entity.workSubBlock =  sheet.getCell(3, i).getContents().trim();
			entity.workType =  sheet.getCell(4, i).getContents().trim();
			entity.workSubType =  sheet.getCell(5, i).getContents().trim();
			entity.workContent =  sheet.getCell(6, i).getContents().trim();
			entity.workResult =  sheet.getCell(7, i).getContents().trim();
			entity.workRole =  sheet.getCell(8, i).getContents().trim();
			entity.workCycle =  sheet.getCell(9, i).getContents().trim();
			entity.remark =  sheet.getCell(10, i).getContents().trim();
			entity.workFrom=sheet.getCell(11, i).getContents().trim();
			entity.print(); 
			recordlist.add(entity); 
		} 
			}
		}
	}

	
	public void  GetJiraData()
	{
		Sheet sheet=CWorkbook.Instance().getSheet(BrowserHtml.SHEET_NAME_JIRA_WORK_LIST);
		Log.print("GetJiraData1");
		for(int i=1;i<sheet.getRows();i++)
		{
			Log.print("GetJiraData2");
			String tempDate=sheet.getCell(0,i).getContents().trim();
			Log.print(tempDate);
			if(tempDate!=null)
			{
				if(daylist.In(tempDate))
				{
					JiraWorkRecordEntity entity=new JiraWorkRecordEntity();
					entity.workDate=sheet.getCell(1,i).getContents().trim();
					entity.workHours=sheet.getCell(2,i).getContents().trim();
					entity.taskItem=sheet.getCell(3,i).getContents().trim();
					entity.workContent=sheet.getCell(4,i).getContents().trim();
					entity.print();
					jiraList.add(entity);
				}
			}
		}
	}
	
	
	
	public ITWorkRecordEntity  L(int i) { 
		 return recordlist.get(i);
	}
	
	public JiraWorkRecordEntity  J(int i) { 
		 return jiraList.get(i);
	}
}
