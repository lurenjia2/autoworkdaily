package com.excel.read;

public class WorkRecord {

	private static WorkRecordList list;
	private static  WorkRecord record;
	
	private WorkRecord()
	{
		
	}
	
	public  static  WorkRecordList instance()
	{
		if(record==null)
		{
			record=new WorkRecord();
			list=new WorkRecordList(); 
		}
		return list;
	}
}
