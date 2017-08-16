package com.hb.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


@Controller("test")
@Scope("prototype")
public class Test extends ActionSupport{
	
	
	@Override
	public String execute() throws Exception {
		System.err.println("struts");
		return "success";
	}
}
