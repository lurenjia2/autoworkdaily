package com.entity;

  

public class WorkRecordEntity  {

	public String  work_date;
	public String  work_hours;
	public String  work_block;
	public String  work_sub_block;
	public String  work_type;
	public String  work_sub_type;
	public String  work_content;
	public String  work_result;
	public String  work_role;
	public String  work_cycle;
	public String  remark;  

	
	public void  print()
	{
		System.out.println("work_date:"+work_date
				          +",work_hours:"+work_hours
				          +",work_block:"+work_block
				          +",work_sub_block:"+work_sub_block
				          +",work_type:"+work_type
				          +",work_sub_type:"+work_sub_type
				          +",work_content:"+work_content
				          +",work_result:"+work_result
				          +",work_role:"+work_role
				          +",work_cycle:"+work_cycle
				          +",remark:"+remark
				          );
	}
}
