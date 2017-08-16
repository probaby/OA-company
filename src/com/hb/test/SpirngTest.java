package com.hb.test;


import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//声明一个bean

public class SpirngTest {
	
	private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	@Test
	public void testBean() throws Exception{
		Test test=(Test)ac.getBean("test");
		System.out.println(test);

	}
	@Test
	public void testFactory() throws Exception{
		SessionFactory session=(SessionFactory)ac.getBean("sessionFactory");
		
	}
}
