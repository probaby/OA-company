package com.hb.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Downup extends ActionSupport{
	
	private File upload;
	private String uploadFileName; // 内置
	private String uploadContentType;
	private String name;
	
	
	
	
	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String upload ()throws Exception{
		
		InputStream in = new FileInputStream(upload);
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"/upload")
				+ "\\" + this.uploadFileName;
		
		System.out.println("filePath: " + filePath);
		 OutputStream out = new FileOutputStream(filePath);
		 
		 int n = 0;
		 
		 while((n=in.read())!= -1){
			 out.write(n);
		 }
		 
		 out.close();
		 in.close();
		 

		return super.execute();
	}
	
	
	public InputStream Download(){
		
		InputStream in = ServletActionContext.getServletContext()
		                    .getResourceAsStream("upload\\"+name);
		
		return in;
	}
	
	
	
}
