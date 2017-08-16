package com.hb.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.hb.bizImpl.UserBizImpl;
import com.hb.daoImpl.UserDao;
import com.hb.entity.Approval;
import com.hb.entity.Department;
import com.hb.entity.OaApplication;
import com.hb.entity.OaApproval;
import com.hb.entity.OaDepartment;
import com.hb.entity.OaUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



public class UserAction extends ActionSupport{
	
	





	private String nor;
	private String des;
	private String upload;
	//用于删除的工具属性
	private String deltools;
	//用于删除时的计数器
	private String count;
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDeltools() {
		return deltools;
	}
	public void setDeltools(String deltools) {
		this.deltools = deltools;
	}
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getNor() {
		return nor;
	}
	public void setNor(String nor) {
		this.nor = nor;
	}

	//当前页
	private int page=0;
	//返回总页数
	private int totally;
	//返回错误消息
	private String errMess;
	//返回对象列表
	private List list;
	
	//Spring 注入
	private UserBizImpl userBizImpl;
	//从前台得到对象，返回前台
	private OaUser user;
	//接受审批的 名字，并到数据库生成id
	private OaApproval approval;
	//接受前台传过来的opions数组
	private String[] options;
	//部门列表
	private OaDepartment department;
	
	private OaApplication appli;
	

	
	public OaApplication getAppli() {
		return appli;
	}
	public void setAppli(OaApplication appli) {
		this.appli = appli;
	}
	public String[] getOptions() {
		return options;
	}
	public OaDepartment getDepartment() {
		return department;
	}
	public void setDepartment(OaDepartment department) {
		this.department = department;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public OaApproval getApproval() {
		return approval;
	}
	public void setApproval(OaApproval approval) {
		this.approval = approval;
	}
	public String getErrMess() {
		return errMess;
	}
	public void setErrMess(String errMess) {
		this.errMess = errMess;
	}
	public int getTotally() {
		return totally;
	}
	public void setTotally(int totally) {
		this.totally = totally;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}




	
	public void setUser(OaUser user) {
		this.user = user;
	}
	public OaUser getUser() {
		return user;
	}
	
	
	public UserBizImpl getUserBizImpl() {
		return userBizImpl;
	}
	@Resource(name="userBizImpl")
	public void setUserBizImpl(UserBizImpl userBizImpl) {
		this.userBizImpl = userBizImpl;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
	
	/**
	 * 登录校验
	 * @return
	 */
	public String login(){
		
		OaUser userSession=userBizImpl.loginCheck(user);
		if(userSession==null){
			errMess="用户名或密码错误";
			return "login";
		}
        ActionContext.getContext().getSession().put("userSession",  
                userSession);
        user=userSession;
		return userSession.getUserId()!=0?"loginsuccess":"error";
	}
	
	/**
	 * 增加用户
	 * @return
	 */
	public String register(){
		user.setLimitt("0");
		boolean b=true;
		try {
			if(user.getPhone().length()!=11){
				errMess="电话号码必须为11位";
				b=false;
			}
			System.out.println();
			String is=user.getPhone();
			String is1=is.substring(0,5);
			String is2=is.substring(6,10);
			System.out.println(Integer.parseInt(is1));
			Integer.parseInt(is2);
		} catch (Exception e) {
			errMess="电话号码必须为数字";
			b=false;
		}
		if(!nor.equals(user.getUserPassword())){
			errMess="两次密码不一致";
			b=false;
		}
		if(!b){
			return "register";
		}
		boolean a=userBizImpl.addUser(user);
		
		if(a){
			return "registersuccess";
			
		}
		
		errMess="用户名已存在";
		return "register";
	}

	/**
	 * 显示单个用户信息
	 * @return
	 */
	public String userList(){
		System.out.println("test action");
		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		System.out.println(user.toString());
		if(user==null){
			return "error";
		}
		return "usersuccess";
	}
	/**
	 * 得到用户信息用于修改
	 * @return
	 */
	public String modif(){
		System.out.println("test action");
		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		System.out.println(user.toString());
		list=userBizImpl.getAllDepartment();
		System.out.println(list);
		if(user==null){
			errMess+="您未登录";
			return "error";
		}

		return "modif";
	}
		
	public String modifMine(){
		errMess="";
		OaUser os=(OaUser)ActionContext.getContext().getSession().get("userSession");
		System.out.println(os);
		System.out.println(user);
		System.out.println(deltools);
		if(deltools.equals(os.getUserPassword())){
			
			if(user.getUserPassword().equals(des)){

				userBizImpl.modifMine(user);
			}else{
				errMess+="两次密码不一致";
			}
			
		}else{
			errMess+="旧密码错误";
		}
		if(!errMess.equals("")){
			return "modiferror";
		}
		return "modiOk";
	}
	
	/**
	 * 得到所有的用户
	 * @return
	 */
	public String getAllUser(){
		if(this.getPage()<=0){
			page=1;
		}
		totally=userBizImpl.getTotally(new OaUser());
		int pageMax=totally/5;
		if(totally%5>0){
			pageMax++;
		}
		if(page>pageMax){
			errMess="内容显示完毕，无下页";
			page=pageMax;
		}
		list=userBizImpl.getAllUser(page);
		if(list.size()>0){
			return "allUser";
			
		}
		return "error";
	}
	
	
	
	//签到
	public String sign(){

		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		System.out.println(user.toString());
		if(userBizImpl.addSign(user.getUserId())){
			errMess="您已签到无需继续签到";
		}
		return "signsuccess";
	}
	/**
	 * 查看自己的所有签到
	 */
	public String getMySign(){
		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		int i=user.getUserId();
		if(this.getPage()<=0){
			page=1;
		}
		totally=userBizImpl.getMySignCount(i);
		int pageMax=totally/5;
		if(totally%5>0){
			pageMax++;
		}
		if(page>pageMax){
			errMess="内容显示完毕，无下页";
			page=pageMax;
		}
		System.out.println("这个是我的id"+i);
		list=userBizImpl.getMySign(page, 5, i);
		if(list.size()>0){
			return "getMySignSuccess";
			
		}
		return "error";
	}
	/**
	 * 查看所有签到排名
	 */
	public String getAllSign(){
		if(this.getPage()<=0){
			errMess="这是第一页";
			page=1;
		}
		totally=userBizImpl.getAllSignCount();
		int pageMax=totally/5;
		if(totally%5>0){
			pageMax++;
		}
		if(page>pageMax){
			errMess="内容显示完毕，无下页";
			page=pageMax;
		}
		
		list=userBizImpl.getAllSign(page, 5);
		
		if(list.size()>0){
			return "getAllSignSuccess";
			
		}
		return "error";
	}
	//查询审批的名字是否存在，存在不能创建，不存在创建之后生成id：
	public String addApproval(){
		OaUser i=(OaUser)ActionContext.getContext().getSession().get("userSession");
		System.out.println("error before");
		System.out.println(options[0]);
		System.out.println(approval.getApprovalValue());
		approval.setUserId(i.getUserId());
		String show=userBizImpl.addApprova(approval, options);
		if(show.equals("yes")){
			return "approvalSuccess";
		}
		errMess=show;
		return "approvala";  
	}
	/**
	 * 查询出所有模板的名称
	 */
	public String getApprovalName(){
		System.out.println("进入查询模板");
		list=userBizImpl.getAllApproval();
		return "getApprovalNamesuccess";
		
	}
	/**
	 *跳转到注册页面生成部门列表
	 * @return
	 */
	public String toRegist(){
		System.out.println("进入toregist");
		list=userBizImpl.getAllDepartment();
		return "toRegistsuccess";
	}
	/**
	 * 上传
	 * @return
	 * @throws Exception
	 */
	/*public String bigUpload()throws Exception{
		System.out.println("eenenene");
		InputStream in = new FileInputStream(upload);
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"/upload")
				+ "\\" + this.uploadFileName;
		System.out.println(this.uploadFileName);
		System.out.println("filePath: " + filePath);

		fifi=this.uploadFileName;
		
		
		 OutputStream out = new FileOutputStream(filePath);
		 
		 int n = 0;
		 
		 while((n=in.read())!= -1){
			 out.write(n);
		 }
		 
		 out.close();
		 in.close();
		 

		return "uploadsuccess";
	}*/
	
	/**
	 * 下载
	 * @return
	 */
/*	public InputStream Download(){
		
		InputStream in = ServletActionContext.getServletContext()
		                    .getResourceAsStream("upload\\"+name);
		
		return in;
	}*/
	
	
	

	
	/**
	 * 将申请存入数据库
	 */
	
	
	public String saveApplication(){
		OaApplication application=new OaApplication();
		System.out.println("进入测上次");
		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		System.out.println(user.toString()); 
		System.out.println(nor);
		application.setAppName(nor);
		application.setFileAddress(des);
		application.setUserId(user.getUserId());
		System.out.println(application.toString());
		int i=user.getUserId();

		approval.setUserId(i);
		String b=userBizImpl.saveFiled(application, approval);
		if(b.equals("yes")){
			return "saveappsuc";
		}
		errMess=b;
		return "approvalSuccess";
		
	}
	/**
	 * 查处个人申请进度；
	 */
	public  String applicationDeal(){
		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		//OaUser use=(OaUser)ActionContext.getContext().getSession("userSession");
		list=userBizImpl.getApplicationDeal(user);
		return "myapplication";
	}
	/**
	 * 通过用户Id查看申请的进度
	 */
	public String getAppDeal(){
		if(this.getPage()<=0){
			page=1;
		}
		totally=userBizImpl.getTotally(new OaDepartment());
		int pageMax=totally/5;
		if(totally%5>0){
			pageMax++;
		}
		if(page>pageMax){
			errMess="内容显示完毕，无下页";
			page=pageMax;
		}
		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		list=userBizImpl.getAppDeal(user.getUserId(),page);
		System.out.println("返回测试"+list);
		return "appDeal";
		
	}
	/**
	 * 得到我的申请信息
	 * @return
	 */
	public String getMyApp(){
		if(this.getPage()<=0){
			page=1;
		}
		totally=userBizImpl.getTotally(new OaApplication());
		int pageMax=totally/5;
		if(totally%5>0){
			pageMax++;
		}
		if(page>pageMax){
			errMess="内容显示完毕，无下页";
			page=pageMax;
		}
		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		list=userBizImpl.getMyApp(user.getUserId(),page);
		System.out.println("返回测试"+list);
		return "Myapp";
		
	}
	/**
	 * 初始化密码
	 */
	public String initPass(){
		user=(OaUser)ActionContext.getContext().getSession().get("userSession");
		user.setUserPassword("123");
		return "initPass";
	}
	/**
	 * 删除用户
	 */
	public String delUser(){
		userBizImpl.delUser(deltools);
		return "delUser";
	}
	/**
	 * 删除部门
	 * @return
	 */
	public String deldepartment(){
		userBizImpl.deldepartment(deltools);
		return "delDepart";
	}
	/**
	 * 删除模板
	 * @return
	 */
	public String delappro(){
		System.out.println("删除模板"+deltools);
		userBizImpl.delappro(deltools);

		return "delappro";
	}
	/**
	 * 管理部门
	 * @return
	 */
	public String getDepart(){
		if(this.getPage()<=0){
			page=1;
		}
		totally=userBizImpl.getTotally(new OaDepartment());
		int pageMax=totally/5;
		if(totally%5>0){
			pageMax++;
		}
		if(page>pageMax){
			errMess="内容显示完毕，无下页";
			page=pageMax;
		}
		list=userBizImpl.getDepartment(page);
		if(list.size()>0){
			return "depart";
			
		}
		return "error";
	}
	
	/**
	 * 管理模板
	 * @return
	 */
	public String getAppro(){
		if(this.getPage()<=0){
			page=1;
		}
		totally=userBizImpl.getTotally(new OaApproval());
		int pageMax=totally/5;
		if(totally%5>0){
			pageMax++;
		}
		if(page>pageMax){
			errMess="内容显示完毕，无下页";
			page=pageMax;
		}
		list=userBizImpl.getApplication(page);
		if(list.size()>0){
			return "appro";
			
		}
		return "error";
	}
	/**
	 * 确认申请操作
	 */
	public String ok(){
		userBizImpl.ok(deltools);
		return "oksucc";
	}
	public String no(){
		userBizImpl.no(deltools);
		return "oksucc";
	}
	
	/**
	 * 增加部门
	 */
	public String addDepar(){
		errMess="";
		OaDepartment oa=new OaDepartment();
		oa.setDepartmentName(nor);
		String e=userBizImpl.addDepar(oa);
		System.out.println(e);
		if(e.equals("yes")){

		}else{
			errMess=e;
			return "addDeparFault";
		}
		return "addDepar";
		
	}
	
	}
