package com.entity.Html;

public  class JiraHtml {

	//class
	public static String LINK_I_NEW_EDIT="log-work";
	public static String LINK_I_MORE_OPRATOR_DIV="opsbar-operations_more_drop";
	public static String LINK_I_MORE_OPRATOR="opsbar-operations_more";
	
	
	
	public class NewDaily
	{
		public static final String TXT_N_WORK_DATE="startDate";//name
		public static final String TXT_I_WORK_DATE="log-work-date-logged-date-picker";//id
		
		public static final String TXT_N_HOURS="timeLogged";//name
		public static final String TXT_I_HOURS="log-work-time-logged";
		
	  
		
		public static final String TXT_N_WORK_CONTENT="comment";//name
		public static final String TXT_I_WORK_CONTENT="comment";//id 
		public static final String TXT_A_WORK_CONTENT="textarea.textarea.long-field.long-field";//class 
		public static final String TXT_X_WORK_CONTENT="//div[@class='field-group']/textarea[@id='comment']";//xpath 
	  
		public static final String LINK_I_CANCLE="log-work-cancel";
        public static final String BTN_N_NEW_SUBMIT="Log";
        public static final String BTN_I_NEW_SUBMIT="log-work-submit";
	}
	
	
	public class Login
	{ 
		public static final String TXT_N_USER_NAME="os_username";
		public static final String TXT_N_PASSWORD="os_password";
		public static final String BTN_I_SUBMIT="login-form-submit";
		public static final String JUS_JIRA_USER_TYPE="jira";
	}
	
}
