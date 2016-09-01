package com.entity;

import com.log.Log;

  

public class ITWorkRecordEntity  extends Entity {

	public String  workDate;
	public String  workHours;
	public String  workBlock;
	public String  workSubBlock;
	public String  workType;
	public String  workSubType;
	public String  workContent;
	public String  workResult;
	public String  workRole;
	public String  workCycle;
	public String  remark;  
   public String   workFrom;
	
	  
	public String toString()
	{
		return "[ItWorkRecord]  work_date:"+workDate
		          +",  work_hours:"+workHours
		          +",  work_block:"+workBlock
		          +",  work_sub_block:"+workSubBlock
		          +",  work_type:"+workType
		          +",  work_sub_type:"+workSubType
		          +",  work_content:"+workContent
		          +",  work_result:"+workResult
		          +",  work_role:"+workRole
		          +",  work_cycle:"+workCycle
		          +",  remark:"+remark
		          +",  work_from:"+workFrom
		          +"\n"  ;
	}
}
