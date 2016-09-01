package com.entity;

import com.log.Log;

  

public class JiraWorkRecordEntity  extends Entity {

	public String  workDate;
	public String  workHours;
	public String  taskItem; 
	public String  workContent; 
	
	  
	public String toString()
	{
		return "[JiraWorkRecord]  workDate:"+workDate
		          +",  workHours:"+workHours
		          +",  taskItem:"+taskItem
		          +",  workContent:"+workContent 
		          +"\n"  ;
	}
}
