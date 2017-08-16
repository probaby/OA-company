package com.hb.bizImpl;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.hb.daoImpl.UserDao;
import com.hb.entity.ApplicatoinShow;
import com.hb.entity.Approval;
import com.hb.entity.MyApplication;
import com.hb.entity.OaApplication;
import com.hb.entity.OaApplicationStatus;
import com.hb.entity.OaApproval;
import com.hb.entity.OaApprovalStatus;
import com.hb.entity.OaDepartment;
import com.hb.entity.OaSign;
import com.hb.entity.OaUser;
import com.opensymphony.xwork2.ActionContext;
@Component("userBizImpl")
public class UserBizImpl {

	public UserBizImpl() {
		
	}

	
	UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
	
	public int getTotally(Object obj){
		return userDao.getTotallyPage(obj);
		
	}
	
	/**
	 * 显示所有的用户
	 */
	
	public List<OaUser> getAllUser(int page){
		List<OaUser> list=userDao.reaAllUser(page,5);
		for(int i=0;i<list.size();i++){
			String pp2=list.get(i).getDepartmentId();
			
			String pp=userDao.getDeName(Integer.parseInt(pp2));
			list.get(i).setDepartmentId(pp);
		}
		return list;
		
	}
	/**
	 * 检查登录
	 */
	public OaUser loginCheck(OaUser user){
		return userDao.loginCheck(user);
	}
	
	
	/**
	 * 增加用户
	 */
	public boolean addUser(OaUser user){
		if(userDao.checkUser(user)){
			userDao.addUser(user);
		}else{
			return false;
		}
		
		return true;
	} 
	
	
	//显示单个用户信息
	public OaUser getUser(OaUser user){
		return userDao.userList(user);
	}
	/**
	 * 添加签到
	 */
	
	public boolean addSign(int uid){
		OaSign signUser=new OaSign();
		signUser.setUserId(uid);

		
        String ip; 
        try { 
             /**返回本地主机。*/ 
             InetAddress addr = InetAddress.getLocalHost(); 
             /**返回 IP 地址字符串（以文本表现形式）*/ 
             ip = addr.getHostAddress();  
        } catch(Exception ex) { 
            ip = ""; 
        } 
          
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");//设置日期格式
		String time=df.format(new Date());
		


		signUser.setSignIp(ip);
		signUser.setSignTime(time);
		return userDao.addSign(signUser);

	}
	/**
	 * 查看个人签到信息
	 */
	public List getMySign(int page,int max,int uid){
		return userDao.getMySign(page, max, uid);
	}
	
	/**
	 * 查看个人签到总次数
	 */
	public int getMySignCount(int uid){
		return userDao.getMySignCount(uid);
	}
	
	
	
	/**
	 * 查看签到排名总数
	 */
	public int getAllSignCount(){
		return userDao.getAllSignCount();
	}
	
	/**
	 * 查看总签到
	 */
	public List getAllSign(int page,int max){
		
		return userDao.getAllSign(page,max);
	}
	/**
	 * 添加模板,判断是否存在用户，不存在返回错误信息
	 */
	public String addApprova(OaApproval approval,String[] options){
		boolean bool=true;
		
		if(userDao.checkAppro(approval)){
			
		}else{
			return "审批名已存在";
		}
		int approvalId=userDao.addApproval(approval);
		if(approvalId>0){
			
			for (int i = 0; i < options.length; i++) {
				OaApprovalStatus approvalStatus=new OaApprovalStatus();
				int u=userDao.getUseId(options[i]);
				if(u<1){
					System.out.println(approvalId);
					userDao.delApproId(approvalId);
					return "用户不存在，请重新输入";
				}
				approvalStatus.setApprovalStep(i+1);
				approvalStatus.setUserId(u);
				approvalStatus.setApprovalId(approvalId);
				
				if(bool){
					bool=userDao.addOaApprovalStatus(approvalStatus);
				}else{
					
					return "输入错误，请重新输入";
				}
				
			}
			return "yes";
		}else{
			return "添加失败";
		}
	}
	/**
	 * 查询所有部门
	 */
	public List<OaDepartment> getAllDepartment(){
		
		return userDao.getAllDepartment();
	}
	/**
	 * 查询所有模块
	 */
	public List<OaApproval> getAllApproval(){
		return userDao.getAllApproval();
		
	}
	
	
	/**
	 * 存放文件的名字和用户id,并得到其id
	 */
	public int saveFileName(OaApplication application){
		return userDao.saveFiled(application);
	}
	
	/**
	 * 
	 * 将申请存入applicationstatus
	 */
	public String saveFiled(OaApplication application,OaApproval approval){
		System.out.println("savefiled 入口");
		OaApplicationStatus applicationStatus=new OaApplicationStatus();
		System.out.println(application.toString());
		if(userDao.checkApp(application)){
			
		}else{
			return "申请名已存在";
		}
		int appId =userDao.saveFiled(application);
		applicationStatus.setAppId(appId);
		applicationStatus.setApplicationStep(1);
		applicationStatus.setApplicationDeal(1);
		applicationStatus.setApprovalId(userDao.getApprovalId(approval));
		System.out.println(applicationStatus.toString());
		
		int a=(Integer)userDao.saveApplication(applicationStatus);
		return "yes";
	}
	/**
	 * 传入一个user 查处其申请的id，
	 * 通过申请id查出步骤 模板id
	 * 通过 查处uid
	 */
	public List<OaApprovalStatus> getApplicationDeal(OaUser user){
		List<OaApplication> list= userDao.getAppId(user);
		ArrayList<OaApprovalStatus> listRe=new ArrayList();
		list.size();
		for (int i = 0; i < list.size(); i++) {
			listRe.add(userDao.getDealUser(userDao.getAppStep(list.get(i))));
		}
		return listRe;
	}
	/**
	 * 查看自己当前待处理
	 */
	public List<ApplicatoinShow> getAppDeal(int id,int page){
		return userDao.getApplicationDeal(id,page,5);
	}
	public List<MyApplication> getMyApp(int id,int page){
		return userDao.getMyApplication(id,page,5);
	}
	
	/**
	 * 删除用户
	 */
	public void delUser(String userName){
		
		userDao.delUser(userName);
	}
	/**
	 * 删除部门
	 * @return
	 */
	public void deldepartment(String departmentName){
		userDao.getAllDe(departmentName);
		
	}
	/**
	 * 删除模板
	 * @return
	 */
	public void delappro(String approId){
		
		userDao.delAppro(approId);
	}
	/**
	 * 根据
	 */
	public List getDepartment(int page){
		return userDao.getDepartment(page,5);
		
	}
	/**
	 * 根据页数返回
	 * @param page
	 * @return
	 */
	public List getApplication(int page){
		return userDao.getApplication(page,5);
		
	}
	/**
	 * 修改用户信息
	 */
	public void modifMine(OaUser ou){
		userDao.modifMine(ou);
	}
	/**
	 * 确认申请
	 */
	public void ok(String appName){
		
		List<Approval> list=userDao.ok(appName);
		Approval ap=(Approval)list.get(0);
		System.out.println(ap);
		System.out.println(appName);
		int a=Integer.parseInt(ap.getCount());
		int b=Integer.parseInt(ap.getApprovalvalue());

		OaApplication os=(OaApplication)(userDao.getApp(appName).get(0));
		System.out.println(os);
			if(a<b){
				System.out.println("2");
				
				userDao.okApp(os,a+1);
			}else{
				System.out.println("3");
				userDao.noApp(os,3);
			}
		
		
	}
	
	public void no(String appName){
		userDao.noApp(((OaApplication)(userDao.getApp(appName).get(0))),2);
	}
	/**
	 * 增加部门
	 */
	public String addDepar(OaDepartment dep){
		if(userDao.checkdep(dep)){
			System.out.println("增加部门中");
			userDao.adddep(dep);
			return "yes";
		}else{
			
			return "部门名已存在";
		}
		
	}
}
