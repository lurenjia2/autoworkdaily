package com.entity;

import com.log.Log;

public  class Entity {

	public String toString() {
		return null;
	}
	
	public void  print()
	{
		System.out.println(toString());
		Log.debug(toString());
	}
}
