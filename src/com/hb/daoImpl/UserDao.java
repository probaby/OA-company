package com.hb.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.hb.entity.ApplicatoinShow;
import com.hb.entity.Approval;
import com.hb.entity.Department;
import com.hb.entity.MyApplication;
import com.hb.entity.OaApplication;
import com.hb.entity.OaApplicationStatus;
import com.hb.entity.OaApproval;
import com.hb.entity.OaApprovalStatus;
import com.hb.entity.OaDepartment;
import com.hb.entity.OaSign;
import com.hb.entity.OaUser;
import com.hb.entity.SignAll;
import com.hb.entity.ApplicatoinShow;


@Component("userDao")
public class UserDao {
	
	
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//显示个人信息
	
	
	
	//修改个人信息
	
	//登录校验
	public OaUser loginCheck(OaUser user){
		
		String hql= "from OaUser as u where u.userName=? and u.userPassword=?";
		List<OaUser> list=hibernateTemplate.find(hql, new String[]{user.getUserName(),user.getUserPassword()});
		if(list.size()<1){
			return null;
			
		}
		OaUser u =list.get(0);
		System.out.println(u.toString());
		return u; 
	}

	
	//添加用户
	public boolean addUser(OaUser user){
		if(hibernateTemplate.save(user)==null){
			return false;
		};
		hibernateTemplate.flush();
		return true;
	}
	//根据id查询用户
	
	public OaUser userList(OaUser user){
		System.out.println("test dao");
		List<OaUser> list=hibernateTemplate.find("from OaUser u where u.userId=?", user.getUserId());
		System.out.println(list.size());
		return list.get(0);
	}
	
	
	
	//得到用户总页数
	public int getTotallyPage(Object obj){
		String clssName=obj.getClass().getName();
		List listsome=hibernateTemplate.find("from "+clssName);
		return listsome.size();
	}
	
	//假 显示所有的用户
	public List<OaUser> allUser(int page,int max){
		
		System.out.println("进入session测试");
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query=session.createQuery("from OaUser user order by user.userId");
		query.setFirstResult((page-1)*max);
		query.setMaxResults(max);
		System.out.println("显示所有用户测试");
		return query.list();
	}
	//真 显示所有人信息	
	public List<OaUser> reaAllUser(final int page,final int max){
		List<OaUser> list2 = (List<OaUser>)hibernateTemplate.execute(new HibernateCallback() {
         public List<OaUser> doInHibernate(Session session){    
        	 System.out.println("test1");
		     Query query = session.createQuery("from OaUser user order by user.userId");     
		     query.setFirstResult((page-1)*max);     
		     query.setMaxResults(max);     
		     List list1 = query.list();
		     
		     System.out.println("test2");
		     return list1;     
		    }     
		   });     
		   return list2;    
		
	}
	
	
	//添加签到
	
	public boolean addSign(OaSign sign){
		System.out.println("aaaaasddsd");
		String time=sign.getSignTime();
		String[] s=time.split("-");
		System.out.println(sign);
		List<OaSign> list=hibernateTemplate.find("from OaSign s where s.userId="+sign.getUserId()+" and s.signTime like '"+s[0]+"%'");
		System.out.println(list.size());
		if(list.size()>0){
			System.out.println("已存在");
			return false;
		};
		System.out.println(sign.toString());
		if(hibernateTemplate.save(sign)==null){
			System.out.println("没签到");
			return false;
		};
		

		hibernateTemplate.flush();
		return true;
	}
	
	//查看自己的所有签到
	//次数
	public int getMySignCount(int uid){

		List<OaUser> list=hibernateTemplate.find("from OaSign u where u.userId=?", uid);
		System.out.println(list.size());
		return list.size();
	}
	public List getMySign(final int page,final int max,final int uid){
		List<OaSign> list2 = (List<OaSign>)hibernateTemplate.execute(new HibernateCallback() {
	         public List<OaSign> doInHibernate(Session session){    

			     Query query = session.createQuery("from OaSign u where u.userId="+uid);     
			     query.setFirstResult((page-1)*max);     
			     query.setMaxResults(max);     
			     List list1 = query.list();
			     System.err.println("mysign"+list1.size());

			     return list1;     
			    }     
			   });     
			   return list2; 
	}
	

	
	//查看所有人的签到排名
	//次数
	public int getAllSignCount(){

		String hql="select count(s.userId) as b,s.userId from OaSign s Group by s.userId order by b desc";
		List list =hibernateTemplate.find(hql);
		System.out.println("得到所有人签名的测试");
		System.err.println(list.toString());
		return list.size();
	}
	public List<SignAll> getAllSign(final int page,final int max){
		List list2 = (List<SignAll>)hibernateTemplate.execute(new HibernateCallback() {
	         public List doInHibernate(Session session){    
	        	 System.out.println("test1");
			     Query query = session.createSQLQuery("select count(s.userId) as count,oauser.userName as some from OaSign s  LEFT join oauser on s.userId=oauser.userid Group by s.userId order by count desc LIMIT "+((page-1)*max)+","+(page*max)+";");     
    
			     List<SignAll> list1 = query.list();
			     List<SignAll> list3 = new ArrayList();
			     System.out.println("test2");
			     Iterator it=list1.iterator();
			        for(;it.hasNext();){  
			            //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
			            Object[] objects = (Object[]) it.next();
			            SignAll sa=new SignAll();
			            sa.setCount(objects[0]+"");
			            sa.setSome(objects[1]+""); 
			            list3.add(sa);
			            
			        } 
			    // System.out.println(sal.toString());
			     return list3;     
			    }     
			   });     
			   return list2; 
	}
	
	/**
	 * 添加审批模板并返回模板id
	 * @param approval
	 * @return
	 */
	public int addApproval(OaApproval approval){
		System.out.println("test approval");
		System.out.println(approval.getApprovalValue());
		if(hibernateTemplate.find("from OaApproval a where a.approvalValue=?",approval.getApprovalValue())==null){
			System.out.println("添加模板测试");
			return 0;
		}else{
			System.out.println("无相同");
			int i=(Integer)hibernateTemplate.save(approval);
			return i;
			
		}
		
		
	}
	/**
	 * 根据用户名查id
	 */
	public int getUseId(String userName){
			List<OaUser> list=hibernateTemplate.find("from OaUser u where u.userName=?", userName);
			if(list.size()<1){
				return 0;
			}
			return list.get(0).getUserId();
	}
	/**
	 * 将模板信息存入oaapprovalstatus
	 */
	public boolean addOaApprovalStatus(OaApprovalStatus approvalstatus){
		int i=(Integer)hibernateTemplate.save(approvalstatus);
		return i<1?false:true;
	}
	/**
	 * 查询出所有模板
	 */
	public List<OaApproval> getAllApproval(){
		System.out.println("查询出所有模板");
		List<OaApproval> list=hibernateTemplate.find("from OaApproval");
		System.out.println("查出来了");
		return list;
	}
	/**
	 * 查询出所有部门
	 */
	public List<OaDepartment> getAllDepartment(){
		List<OaDepartment> list=hibernateTemplate.find("from OaDepartment");
		return list;
	}
	
	/**
	 * 先查询是否存在用户id，若存在则存储，不存在返回错误信息
	 * 存放文件的名字和用户id,并得到其id
	 */
	public int saveFiled(OaApplication application){
		
		int i = (Integer)hibernateTemplate.save(application);	
		return i;
	}
	/**
	 * 存放申请的相关信息applicationstatus
	 */
	public int saveApplication(OaApplicationStatus applicationStatus){
		int i=(Integer)hibernateTemplate.save(applicationStatus);
		return i;
	}
	/**
	 * 根据模板名返回模板id
	 */
	public int getApprovalId(OaApproval approval){
		List<OaApproval> list=hibernateTemplate.find("from OaApproval a where a.approvalValue=?",approval.getApprovalValue());
		return (Integer)list.get(0).getApprovalId();
	}
	/**
	 * 根据用户id查出该用户的所有申请id
	 */
	public List<OaApplication> getAppId(OaUser user){
		List<OaApplication> list=hibernateTemplate.find("from OaApplication a where a.UserId=?",user.getUserId());
		return list;
	}
	/**
	 * 根据申请id查出当前进度和模板id
	 */
	public OaApplicationStatus getAppStep(OaApplication application){
		List<OaApplicationStatus> list=hibernateTemplate.find("from OaApplicationStatus a where a.appId=?",application.getAppId());
		return list.get(0);
	}
	/**
	 * 根据当前进度和模板id查出当前处理人
	 */
	public OaApprovalStatus getDealUser(OaApplicationStatus appli){
		List<OaApprovalStatus> list=hibernateTemplate.find("from OaApprovalStatus a where a.approvalId=? and a.approvalStep=?",new String[]{appli.getApprovalId()+"",appli.getApplicationStep()+""});
		return list.get(0);
	}
	/**
	 * 查出申请处理的相关信息
	 * 
	 */
	
	public List<ApplicatoinShow> getApplicationDeal(final int id,final int page,final int max){

 		
		List list2 = (List<ApplicatoinShow>)hibernateTemplate.execute(new HibernateCallback() {
	         public List doInHibernate(Session session){    
	        	 System.out.println("test1");

			     Query query = session.createSQLQuery("select opp.appname,ou.userName,opp.fileAddress from oaapprovalstatus as os,oaapplicationstatus as ops,oauser as ou,oaapplication as opp where os.userId="+id+" and os.approvalstep=ops.applicationstep and os.approvalid=ops.approvalid and ops.appId=opp.appid and opp.userId=ou.userId LIMIT "+((page-1)*max)+","+(page*max)+";");     
   
			     List<ApplicatoinShow> list1 = query.list();
			     List<ApplicatoinShow> list3 = new ArrayList();
			     System.out.println("test2");
			     Iterator it=list1.iterator();
			        for(;it.hasNext();){  
			            //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
			            Object[] objects = (Object[]) it.next();
			            ApplicatoinShow sa=new ApplicatoinShow();
			            sa.setAppName(objects[0]+"");
			            sa.setUserName(objects[1]+"");
			            sa.setFileAddress(objects[2]+"");

			            list3.add(sa);
			            
			        } 
			    // System.out.println(sal.toString())
			        System.out.println(list3);
			     return list3;     
			    }     
			   });     
			   return list2; 
	}
	/**
	 * 查出自己申请的相关信息
	 */
	public List<MyApplication> getMyApplication(final int id,final int page,final int max){

 		
		List list2 = (List<MyApplication>)hibernateTemplate.execute(new HibernateCallback() {
	         public List doInHibernate(Session session){    
	        	 System.out.println("test1");

			     Query query = session.createSQLQuery("select DE.s as allcount,op.appName,OU.username,ops.applicationdeal,ops.applicationstep from (select approvalid,count(userid) as s from oaapprovalstatus as s GROUP BY s.approvalid) de,oaapplication as op,oaapproval as oap,oaapplicationstatus as ops, oauser as ou ,oaapprovalstatus as opp where op.userId="+id+" and op.appid=ops.appId and ops.approvalid=opp.approvalid and de.approvalid=opp.approvalid and ops.applicationstep=opp.approvalstep and ou.userId=opp.userid and oap.approvalid=opp.approvalid LIMIT "+((page-1)*max)+","+(page*max)+";");     
   
			     List<MyApplication> list1 = query.list();
			     List<MyApplication> list3 = new ArrayList();
			     System.out.println("test2");
			     Iterator it=list1.iterator();
			        for(;it.hasNext();){  
			            //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
			            Object[] objects = (Object[]) it.next();
			            MyApplication sa=new MyApplication();
			            sa.setAllCount(objects[0]+"");
			            sa.setAppName(objects[1]+"");
			            sa.setUserName(objects[2]+"");
			            sa.setApplicationDeal(objects[3]+"");

			            sa.setApplicationStep(objects[4]+"");
			            list3.add(sa);
			            
			        } 
			    // System.out.println(sal.toString())
			        System.out.println(list3);
			     return list3;     
			    }     
			   });     
			   return list2; 
	}
	/**
	 * 修改用户信息
	 */
	
	public void modifMine(OaUser ou){
		hibernateTemplate.bulkUpdate("update OaUser set userPassword=?,post=?,phone=?,address=?,departmentId=? where userName=?", new Object[]{ou.getUserPassword(),ou.getPost(),ou.getPhone(),ou.getAddress(),ou.getDepartmentId(),ou.getUserName()});
	}
	/**
	 * 修改用户信息
	 */
	public void modif(OaUser ou){
		hibernateTemplate.update(ou);
	}
	
	/**
	 * 查询审批
	 */
	public List<OaApproval> getAllAppro(){
		List<OaApproval> list=hibernateTemplate.find("from OaApproval");
		return list;
	}
	/**
	 * 查询部门
	 */
	public List<OaDepartment> getAllDep(){
		
		List<OaDepartment> list=hibernateTemplate.find("from OaDepartment");
		return list;
	}
	/**
	 * 查询部门和部门有多少人
	 */
	public List getDepartment(final int page,final int max){

 		
		List list2 = (List<Department>)hibernateTemplate.execute(new HibernateCallback() {
	         public List doInHibernate(Session session){    
	        	 System.out.println("test1");

			     Query query = session.createSQLQuery("select departmentname,count(userId) as count from oadepartment left JOIN  oauser on oadepartment.departmentid=oauser.departmentId GROUP BY oadepartment.departmentid LIMIT "+((page-1)*max)+","+(page*max)+";");     
   
			     List<Department> list1 = query.list();
			     List<Department> list3 = new ArrayList();
			     System.out.println("test2");
			     Iterator it=list1.iterator();
			        for(;it.hasNext();){  
			            //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
			            Object[] objects = (Object[]) it.next();
			            Department sa=new Department();
			            sa.setDepartmentName(objects[0]+"");
			            sa.setCount(objects[1]+"");
			           
			            list3.add(sa);
			            
			        } 
			    // System.out.println(sal.toString())
			        System.out.println(list3);
			     return list3;     
			    }     
			   });     
			   return list2; 
	}
	
	
	
	
	/**
	 * 查询审批被多少申请使用
	 */
	public List getApplication(final int page,final int max){

 		
		List list2 = (List<Approval>)hibernateTemplate.execute(new HibernateCallback() {
	         public List doInHibernate(Session session){    
	        	 System.out.println("test1");

			     Query query = session.createSQLQuery("select approvalvalue,count(appId) as count from oaapproval left join oaapplicationstatus on oaapproval.approvalid=oaapplicationstatus.approvalid GROUP BY oaapproval.approvalid LIMIT "+((page-1)*max)+","+(page*max)+";");     
   
			     List<Approval> list1 = query.list();
			     List<Approval> list3 = new ArrayList();
			     System.out.println("test2");
			     Iterator it=list1.iterator();
			        for(;it.hasNext();){  
			            //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
			            Object[] objects = (Object[]) it.next();
			            Approval sa=new Approval();
			            sa.setApprovalvalue(objects[0]+"");
			            sa.setCount(objects[1]+"");
			           
			            list3.add(sa);
			            
			        } 
			    // System.out.println(sal.toString())
			        System.out.println(list3);
			     return list3;     
			    }     
			   });     
			   return list2; 
	}





	/**
	 * 删除用户
	 */
	public void delUser(String userName){

		hibernateTemplate.bulkUpdate("delete OaUser where userName=?", new Object[]{userName});
	}
	/**
	 * 删除部门
	 */
	public void getAllDe(String departmentName){
		hibernateTemplate.bulkUpdate("delete OaDepartment where departmentName=?", new Object[]{departmentName});
	} 
	/**
	 * 删除审批
	 */
	public void delAppro(String approId){
		hibernateTemplate.bulkUpdate("delete OaApproval where approvalValue=?", new Object[]{approId});
	}
	
	
	/**
	 * 删除审批
	 */
	public void delApproId(int approId){
		hibernateTemplate.bulkUpdate("delete OaApproval where approvalId=?", new Object[]{approId});
	}
	/**
	 * 确认申请
	 */
	public List<Approval> ok(final String appName){
		List list2 = (List<Approval>)hibernateTemplate.execute(new HibernateCallback() {
		public List doInHibernate(Session session){    
       	 

		     Query query = session.createSQLQuery("select count(op.approvalstep) as approvalvalue,aa.applicationstep as count from (select approvalid,applicationstep from oaapplicationstatus where appid=(SELECT appid from oaapplication where appname='"+appName+"')) aa,oaapprovalstatus as op where op.approvalid=aa.approvalid;");     

		     List<Approval> list1 = query.list();
		     List<Approval> list3 = new ArrayList();
		     System.out.println("test2");
		     Iterator it=list1.iterator();
		        for(;it.hasNext();){  
		            //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
		            Object[] objects = (Object[]) it.next();
		            Approval sa=new Approval();
		            sa.setApprovalvalue(objects[0]+"");
		            sa.setCount(objects[1]+"");
		           
		            list3.add(sa);
		            
		        } 
		    // System.out.println(sal.toString())
		        System.out.println(list3);
		     return list3;     
		    }     
		   });     
		   return list2; 
	
	}
	/**
	 * 根据申请名查申请id
	 */
	public List<OaApplication> getApp(String appName){
		List<OaApplication> list=hibernateTemplate.find("from OaApplication u where u.appName=?", appName);
		System.out.println(list);
		return list;
	}
	/**
	 * 申请顺延
	 * @param ou
	 */
	public void okApp(OaApplication ou,int i){
		hibernateTemplate.bulkUpdate("update OaApplicationStatus set applicationStep=? where appId=?", new Object[]{i,ou.getAppId()});
	}
	/**
	 * 设置申请通过
	 */
	public void noApp(OaApplication ou,int i){
		hibernateTemplate.bulkUpdate("update OaApplicationStatus set applicationDeal=? where appId=?", new Object[]{i,ou.getAppId()});
	}
	/**
	 * 新增部门
	 * @param user
	 * @return
	 */
	public boolean adddep(OaDepartment user){
		if(hibernateTemplate.save(user)==null){
			return false;
		};
		hibernateTemplate.flush();
		return true;
	}
	/**
	 * 查看部门名是否重复
	 */
	public boolean checkdep(OaDepartment user){
		List< OaDepartment> list=hibernateTemplate.find("from OaDepartment u where u.departmentName=?", user.getDepartmentName());
		System.out.println(list.size());
		return list.size()<1;
	}
	/**
	 * 查看用户名是否重复
	 */
	public boolean checkUser(OaUser user){
		List< OaUser> list=hibernateTemplate.find("from OaUser u where u.userName=?", user.getUserName());
		System.out.println(list.size());
		return list.size()<1;
	}
	
	
	/**
	 * 查看审批名字是否重复
	 */
	public boolean checkAppro(OaApproval user){
		List< OaApproval> list=hibernateTemplate.find("from OaApproval u where u.approvalValue=?", user.getApprovalValue());
		System.out.println(list.size());
		return list.size()<1;
	}
	
	/*
	 * 查看申请名字是否重复
	 */
	public boolean checkApp(OaApplication user){
		List<OaApplication> list=hibernateTemplate.find("from OaApplication u where u.appName=?", user.getAppName());
		System.out.println(list.size());
		return list.size()<1;
	}
	/**
	 * 根据部门id的到部门对象
	 */
	public String getDeName(int departmentId){
		List<OaDepartment> list= hibernateTemplate.find("from OaDepartment u where u.departmentId=?",departmentId);
		return list.get(0).getDepartmentName();
	}
}
