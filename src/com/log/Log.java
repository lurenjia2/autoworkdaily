package com.log;

import org.apache.log4j.Logger;

public class Log {

	public static Logger logger=Logger.getRootLogger();
	
	public static void debug(String s)
	{
		logger.debug(s);
	}
	
	public static void warn(String s)
	{
		logger.warn(s);
	}
	public static void error(String s)
	{
		logger.error(s);
	}
	public static void info(String s)
	{
		logger.info(s);
	}
	public static void fatal(String s)
	{
		logger.fatal(s);
	}
	
	
	
	public static void print(String s)
	{
		System.out.println(s);
	}
}
