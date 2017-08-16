package com.hb.entity;

public class ApplicatoinShow {
	private String appName;
	private String userName;
	private String fileAddress;
	
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFileAddress() {
		return fileAddress;
	}
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	@Override
	public String toString() {
		return "ApplicatoinShow [appName=" + appName + ", userName=" + userName + ", fileAddress=" + fileAddress + "]";
	}
	
}
