package com.hb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oameet")
public class OaMeet {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="meetId")
	private int meetId;

	private int userId;

	private String meetRoom;

	private String meetTime;

	public int getMeetId() {
		return meetId;
	}

	public void setMeetId(int meetId) {
		this.meetId = meetId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMeetRoom() {
		return meetRoom;
	}

	public void setMeetRoom(String meetRoom) {
		this.meetRoom = meetRoom;
	}

	public String getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(String meetTime) {
		this.meetTime = meetTime;
	}

	public OaMeet() {
		super();
	}

	
	
	
}
