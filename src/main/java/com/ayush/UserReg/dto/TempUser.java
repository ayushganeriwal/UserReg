package com.ayush.UserReg.dto;

public class TempUser {
	private User user;
	private long creationTime;
	public TempUser(User u) {
	
		this.user = u;
		creationTime = System.currentTimeMillis();
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

}
