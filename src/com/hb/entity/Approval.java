package com.hb.entity;

public class Approval {
	private String approvalvalue;
	private String count;
	public String getApprovalvalue() {
		return approvalvalue;
	}
	public void setApprovalvalue(String approvalvalue) {
		this.approvalvalue = approvalvalue;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Approval [approvalvalue=" + approvalvalue + ", count=" + count + "]";
	}
	
}
