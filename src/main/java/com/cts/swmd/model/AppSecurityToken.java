package com.cts.swmd.model;

import java.io.Serializable;

public class AppSecurityToken implements Serializable{
	private static final long serialVersionUID = 8091879091924046844L;
	private final String jwttoken;
	
	public AppSecurityToken(String jwttoken) {
		
		this.jwttoken = jwttoken;
	}

	public String getJwttoken() {
		return this.jwttoken;
	}
	
	
	

}
