package com.excel.read;

import jxl.*;
import jxl.read.biff.BiffException; 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import  com.entity.*;
import com.excel.common.*;


public class WorkRecordList extends ExcelReadTemplate<WorkRecordEntity> {

	 
	public List<WorkRecordEntity>  recordlist;
	
	public WorkRecordList(String v_path)
	{
		 super.setPath(v_path);
		 recordlist=new ArrayList<WorkRecordEntity>();
	}
	
	public void  GetData() throws IndexOutOfBoundsException, BiffException, IOException{
		Sheet sheet=getSheet();
		for(int i =1;i<sheet.getRows();i++)
		{
			WorkRecordEntity entity=new WorkRecordEntity();
			entity.work_date =  sheet.getCell(0, i).getContents();
			entity.work_hours =  sheet.getCell(1, i).getContents();
			entity.work_block =  sheet.getCell(2, i).getContents();
			entity.work_sub_block =  sheet.getCell(3, i).getContents();
			entity.work_type =  sheet.getCell(4, i).getContents();
			entity.work_sub_type =  sheet.getCell(5, i).getContents();
			entity.work_content =  sheet.getCell(6, i).getContents();
			entity.work_result =  sheet.getCell(7, i).getContents();
			entity.work_role =  sheet.getCell(8, i).getContents();
			entity.work_cycle =  sheet.getCell(9, i).getContents();
			entity.remark =  sheet.getCell(10, i).getContents();
			recordlist.add(entity);
		}
	}
	
}
