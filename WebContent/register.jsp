<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/stylesRegister.css">
<style>ul li a{text-decoration:none;}</style>
</head>
<body>


	<div class="htmleaf-container" style="overflow:auto;">  
    <div class="wrapper">  
      <div class="container">  
        <h1>Welcome to OAHB</h1>  
  
        <form class="form" action="userAction_register.action" id="form" method="get">  
          <input type="text" name="user.userName" placeholder="Username" required>
                   <c:if test="${errMess!=''}">
        <div class="div1" style="color:red">
        ${errMess}
        </div>
        </c:if>    
          <input type="password" name="user.userPassword" placeholder="Password" required>
          <input type="password" name="nor" placeholder="remind Password" required>
         <select class="something"name="user.gender" style="background-color:#74cbb5;color:white">
		<option selected="selected" style="text-align:center;color:white" value="男">男</option>
		<option  style="text-align:center;" value="女">女</option>
		</select>
		<select class="something" name="user.departmentId" style="background-color:#74cbb5;color:white">
		    <c:forEach items="${list}" var="list" varStatus="vst">
					<option style="text-align:center" value="${list.departmentId}">${list.departmentName}</option>
	
            </c:forEach>
		
		</select>
		<input type="text" name="user.phone" placeholder="phone number" required>
		<input type="text" name="user.post" placeholder="post" required>
        	
        	<input type="text" name="user.address" placeholder="address" required>



          <button type="button" id="login-button">submit</button>
          <a href="login.jsp">返回登录</a>
          
             
        </form>  
      </div>  
  
      <ul class="bg-bubbles" >  
        <li>办</li>  
        <li>公</li>  
        <li>自</li>  
        <li>动</li>  
        <li>化</li>  
        <li>系</li>  
        <li>统</li>  
          
      </ul>  
    </div>  
  </div>  

  <script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>  
  <script>  
    $('#login-button').click(function (event) {  
      event.preventDefault();  
      $('form').fadeOut(500);  
      $('.wrapper').addClass('form-success');  
      setTimeout(function(){
    	  $('#form').submit(); 
      },3000);
      
      //$('#formB').submit();
    });  
  </script>
 

</body>
</html>