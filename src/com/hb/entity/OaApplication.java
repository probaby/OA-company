package com.hb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class OaApplication {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int appId;
	private String appName;
	private String fileAddress;
	private int userId;	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public OaApplication() {

	}

	public OaApplication(int appId, String appName, String fileAddress, int userId) {
		super();
		this.appId = appId;
		this.appName = appName;
		this.fileAddress = fileAddress;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "OaApplication [appId=" + appId + ", appName=" + appName + ", fileAddress=" + fileAddress + ", userId="
				+ userId + "]";
	}
	
	
	
	
	
}
