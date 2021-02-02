package com.example.model;

import com.google.gson.annotations.SerializedName;

public class ResponseToken {

	@SerializedName("token")
	private String token;

	public String getToken(){
		return token;
	}
}