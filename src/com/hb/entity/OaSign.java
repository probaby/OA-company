package com.hb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oasign")
public class OaSign {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="signId")
	private int signId;
	@Column(name="userId")
	private int userId;
	@Column(name="signIp")
	private String signIp;
	@Column(name="signTime")
	private String signTime;



	public int getSignId() {
		return signId;
	}



	public void setSignId(int signId) {
		this.signId = signId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getSignIp() {
		return signIp;
	}



	public void setSignIp(String signIp) {
		this.signIp = signIp;
	}



	public String getSignTime() {
		return signTime;
	}



	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}



	@Override
	public String toString() {
		return "OaSign [signId=" + signId + ", userId=" + userId + ", signIp=" + signIp + ", signTime=" + signTime
				+ "]";
	}
	
	
	
}
