package com.hb.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="oaapproval")
public class OaApproval {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int approvalId;

	private String approvalValue;
	private int userId;
	
	
	public int getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}
	public String getApprovalValue() {
		return approvalValue;
	}
	public void setApprovalValue(String approvalValue) {
		this.approvalValue = approvalValue;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	


	
	
}
