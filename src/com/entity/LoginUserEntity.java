package com.entity;

import com.log.Log;

public class LoginUserEntity extends Entity {
 
	public String username;
	public String password; 
	 
	
	 
	
	public String toString()
	{
		return "[login user] username:"+username
		          +",password:"+password
		          +"\n" ;  
		
	}
}
