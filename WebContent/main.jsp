<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">





	function SetCwinHeight(s){
		alert(s);
		alert("aa");
		alert("1111");
		
		
		var ifr_ch = document.getElementById("child");
		var ifr_hb = document.getElementById("huangbiao");
		alert(ifr_ch.attachEvent());
		if(ifr_ch.attachEvent&&ifr_hb.attachEvent){
			alert("aaa");
			ifr_ch.attachEvent("onload", function(){
				ifr_hb.attachEvent("onload", function(){
					
					
					ifr_hb.height =  ifr_hb.contentWindow.document.documentElement.scrollHeight;
	            });
				
				ifr_ch.height =  ifr_ch.contentWindow.document.documentElement.scrollHeight;
            });
            return;
        }else{
        	ifr_hb.onload = function(){
        		
        		ifr_ch.onload = function(){
        			ifr_ch.height = ifr_ch.contentDocument.body.scrollHeight;
                };
               
                ifr_hb.height = ifr_hb.contentDocument.body.scrollHeight;
            };
            return;                 
        }     
		

	}

</script>
<!-- add styles -->
<link href="css/styleMain.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/styleHead.css" media="screen" type="text/css" />
  <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>

    <link rel="stylesheet" href="css/styleRight.css" type="text/css" />
</head>
<body style="height:100%;width:100%">


<div style="height: 100%">
<div  style="top:0;height:120px;width:100%;color:#FFFFFF;background: #1abc9c;">
  <div class="loader font1" style="width:100%;top:10px;margin:0px 0 0px 25%;padding-top:15px">
	  <span class="span2">公</span>
	  <span class="span3">司</span>
	  <span class="span4">那</span>
	  <span class="span5">么</span>
	  <span class="span6">大</span>
	  </div>
 	<div class="loader font4" style="width:100%;margin:-25px 0 0px 45%">
 	 	
 	 	<span class="span5">-</span>
 	 	<span>你</span>
 	 	<span class="span2">需</span>
  		<span class="span3">要</span>
 		<span class="span4">OA</span>
 		<span class="span5">来</span>
 		<span class="span6">看</span>
 		<span class="span7">家</span>
	</div>
</div>



<div class="container" style="height:100%;overflow:hidden;width: 100%">
	<ul class="menu">
		<li><a href="company.jsp" target="huangbiao">主页</a></li>
		<li><a href="#">个人事务</a>
			<ul class="submenu">
				<li><a href="userAction_sign.action" target="huangbiao">签到</a></li>
				<li><a href="userAction_getMySign.action" target="huangbiao">签到记录</a></li>
				<li><a href="userAction_userList.action" target="huangbiao">个人信息</a></li>
				<li><a href="userAction_modif.action" target="huangbiao">修改信息</a></li>
				</ul>
		</li>
		<li><a href="#">文件审批</a>
			<ul class="submenu">
				<li><a href="userAction_getApprovalName.action" target="huangbiao">申请审批</a></li>
				<li><a href="addapprove.jsp" target="huangbiao">添加模板</a></li>
				<li><a href="userAction_getMyApp.action" target="huangbiao">查看进度</a></li>
				<li><a href="userAction_getAppDeal.action" target="huangbiao">待处理</a></li>
				</ul>
		</li>
		<li><a href="#">综合办公</a>
			<ul class="submenu">
				
				<li><a href="userAction_getAllSign.action" target="huangbiao">考勤排名</a></li>
			</ul>
		</li>


		<li><a href="#">系统管理</a>
			<ul class="submenu">
			<c:if test="${user.limitt==1}">
			
				<li><a href="userAction_getAppro.action"  target="huangbiao">模板管理</a></li>
				<li><a href="userAction_getDepart.action"  target="huangbiao">部门管理</a></li>
				<li><a href="userAction_getAllUser.action"  target="huangbiao">用户管理</a></li>
				
			
			</c:if>
			
			
			<c:if test="${user.limitt==0}">
			
				<li><a href="javascript:void(0);" onclick="return window.alert('对不起，您没有访问权限')" target="huangbiao">模板管理</a></li>
				<li><a href="javascript:void(0);"  onclick="return window.alert('对不起，您没有访问权限')" target="huangbiao">部门管理</a></li>
				<li><a href="javascript:void(0);"  onclick="return window.alert('对不起，您没有访问权限')" target="huangbiao">用户管理</a></li>
				
			
			</c:if>
			
			
				
			</ul>
		
		</li>
		<li><a href="#">帮助与建议</a>
			<ul class="submenu">
				<li><a href="about.jsp" target="huangbiao">说明</a></li>
				<li><a href="help.jsp" target="huangbiao">建议</a></li>
			</ul>			
		</li>
		<li>
		<a href="login.jsp">注销登录</a>
		</li>
	</ul>

	<div style="height: 100%;background: #e0e0e0">

	
		<div class="sas" style="height: 100%;background: #e0e0e0">
			<iframe id="huangbiao" onload="this.height=huangbiao.document.body.scrollHeight" scrolling="no" frameborder="no" border="0" style="width: 100%;" src="company.jsp" name="huangbiao"></iframe>
		</div> 
		<div class="sis" style="height: 100%;background: #e0e0e0">

		
		<iframe id="child" onload="this.height=child.document.body.scrollHeight" scrolling="no" frameborder="0" border="0" style="width: 100%;" src="right.jsp" name="child"></iframe>
  

  
		</div>

		


	</div>
</div>
</div>




</body>
</html>