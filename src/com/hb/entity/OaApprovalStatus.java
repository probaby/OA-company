package com.hb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OaApprovalStatus")
public class OaApprovalStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int appStatusId;

	private int approvalId;

	private int approvalStep;

	private int userId;

	public int getAppStatusId() {
		return appStatusId;
	}

	public void setAppStatusId(int appStatusId) {
		this.appStatusId = appStatusId;
	}

	public int getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}

	public int getApprovalStep() {
		return approvalStep;
	}

	public void setApprovalStep(int approvalStep) {
		this.approvalStep = approvalStep;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public OaApprovalStatus() {
		super();
	}
	
	
	
	
	
}
