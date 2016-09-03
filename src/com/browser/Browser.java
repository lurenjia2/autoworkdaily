package com.browser;

public class Browser {
 
	private static Browser browser;
	private static BrowserCommon common;
	private Browser()
	{
		
	}
	
	public static BrowserCommon Instance()
	{
		if(browser==null)
		{   browser=new Browser();
			common=new BrowserCommon();
		}
		return common;
	}
}
