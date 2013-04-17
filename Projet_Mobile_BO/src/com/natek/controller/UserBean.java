package com.natek.controller;

public class UserBean {

	private String username;
	private String password;
	
	public UserBean(){
		
	}

	public UserBean(String test) {
		super();
		this.username = test;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String test) {
		this.username = test;
		System.out.println(test);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		System.out.println(password);
	}
	
	
	public String login(){
		System.out.println("login");
		if(username.equals("stan")&&password.equals("stan")){
			System.out.println("ok");
			return "login";
		}
		else{
			return "false";
		}
	}
}
