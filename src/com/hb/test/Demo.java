package com.hb.test;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Demo {
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");//设置日期格式
		String time=df.format(new Date());
		String[] s=time.split("-");
		System.out.println(s[0]);
		System.out.println(s[1]);
		
		System.out.println(time);// new Date()为获取当前系统时间
	}
	//得到ip
	public void getIp(){
        String ip; 
        try { 
             /**返回本地主机。*/ 
             InetAddress addr = InetAddress.getLocalHost(); 
             /**返回 IP 地址字符串（以文本表现形式）*/ 
             ip = addr.getHostAddress();  
        } catch(Exception ex) { 
            ip = ""; 
        } 
          
        System.out.println(ip);
		
	}
	
	
	
	//得到时间
	public void getDate(){
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	}
	
}
