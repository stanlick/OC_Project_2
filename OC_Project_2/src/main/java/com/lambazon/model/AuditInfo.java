package com.lambazon.model;

import java.time.LocalDateTime;

public class AuditInfo {

	private String who;
	private LocalDateTime when = LocalDateTime.now();
	private String action;
	
	
	
	public AuditInfo(Product what, String action) {
		super();
		who = System.getProperty("user.name"); 
		this.action = action;
	}
	
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return String.format("AuditInfo [who=%s, when=%s, action=%s]", who, when, action);
	}
	
	
}
