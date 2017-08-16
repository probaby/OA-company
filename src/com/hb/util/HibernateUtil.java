package com.hb.util;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory=null;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public static Session getSession(){
		
		return sessionFactory.openSession();
		
	}  
	
}
