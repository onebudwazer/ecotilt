package fr.ecotilt.appui.model;

import java.util.Date;

public class User {

	private long	userId;
	
	private String	name;
	
	private String	password;
	
	protected Date date;
	
	public User() {
	}
	
	public User(String nameValue, String passwordValue) {
		this.name = nameValue.toLowerCase();
		this.password = passwordValue;
		this.date = new Date();
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getUserId() {
		return this.userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			if (this.name.equals(other.getName())
			 && this.password.equals(other.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "User{" + "name=" + name +
				       ", password=" + password + 
				       ", date=" + date + '}';
	}
	
	
}
