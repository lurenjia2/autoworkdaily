package com.excel.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelRead {

	
	public static Workbook   readByPath(String v_path) throws BiffException, IOException
	{
		Workbook book = Workbook.getWorkbook(new File(v_path));
		return book;
	}
}
