package com.hb.entity;

public class MyApplication {
	private String allCount;
	private String appName;
	private String userName;
	private String applicationDeal;
	private String applicationStep;
	public String getAllCount() {
		return allCount;
	}
	public void setAllCount(String allCount) {
		this.allCount = allCount;
	}
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
	public String getApplicationDeal() {
		return applicationDeal;
	}
	public void setApplicationDeal(String applicationDeal) {
		this.applicationDeal = applicationDeal;
	}
	public String getApplicationStep() {
		return applicationStep;
	}
	public void setApplicationStep(String applicationStep) {
		this.applicationStep = applicationStep;
	}
	@Override
	public String toString() {
		return "MyApplication [allCount=" + allCount + ", appName=" + appName + ", userName=" + userName
				+ ", applicationDeal=" + applicationDeal + ", applicationStep=" + applicationStep + "]";
	}
	
	
}
