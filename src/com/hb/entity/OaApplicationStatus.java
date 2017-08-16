package com.hb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class OaApplicationStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int applicationId;

	private int appId;

	private int approvalId;

	private int applicationStep;
	private int applicationDeal;
	
	
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}
	public int getApplicationStep() {
		return applicationStep;
	}
	public void setApplicationStep(int applicationStep) {
		this.applicationStep = applicationStep;
	}
	public int getApplicationDeal() {
		return applicationDeal;
	}
	public void setApplicationDeal(int applicationDeal) {
		this.applicationDeal = applicationDeal;
	}
	public OaApplicationStatus() {
		super();
	}
	@Override
	public String toString() {
		return "OaApplicationStatus [applicationId=" + applicationId + ", appId=" + appId + ", approvalId=" + approvalId
				+ ", applicationStep=" + applicationStep + ", applicationDeal=" + applicationDeal + "]";
	}
	
	
	
}
