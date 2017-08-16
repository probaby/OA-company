package com.hb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="oauser")
public class OaUser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userid")
	private int userId;
	@Column(name="userName")
	private String userName;
	@Column(name="userPassword")
	private String userPassword;
	@Column(name="gender")
	private String gender;
	@Column(name="departmentId")
	private String departmentId;
	@Column(name="post")
	private String post;
	@Column(name="phone")
	private String phone;
	@Column(name="address")
	private String address;
	
	private String limitt;
	
	
	
	
	
	








	public String getLimitt() {
		return limitt;
	}







	public void setLimitt(String limitt) {
		this.limitt = limitt;
	}







	public int getUserId() {
		return userId;
	}







	public void setUserId(int userId) {
		this.userId = userId;
	}







	public String getUserName() {
		return userName;
	}







	public void setUserName(String userName) {
		this.userName = userName;
	}







	public String getUserPassword() {
		return userPassword;
	}







	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}







	public String getGender() {
		return gender;
	}







	public void setGender(String gender) {
		this.gender = gender;
	}







	public String getDepartmentId() {
		return departmentId;
	}







	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}







	public String getPost() {
		return post;
	}







	public void setPost(String post) {
		this.post = post;
	}







	public String getPhone() {
		return phone;
	}







	public void setPhone(String phone) {
		this.phone = phone;
	}







	public String getAddress() {
		return address;
	}







	public void setAddress(String address) {
		this.address = address;
	}







	public OaUser() {
		super();
	}







	public OaUser(int userId, String userName, String userPassword, String gender, String departmentId, String post,
			String phone, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.gender = gender;
		this.departmentId = departmentId;
		this.post = post;
		this.phone = phone;
		this.address = address;
	}







	@Override
	public String toString() {
		return "OaUser [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", gender="
				+ gender + ", departmentId=" + departmentId + ", post=" + post + ", phone=" + phone + ", address="
				+ address + "]";
	}







	
	
}
