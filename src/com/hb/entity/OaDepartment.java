package com.hb.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oadepartment")
public class OaDepartment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int departmentId;

	private String departmentName;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "OaDepartment [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}

	
	
	
	
}
