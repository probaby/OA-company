<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>login</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="htmleaf-container">  
    <div class="wrapper">  
      <div class="container">  
        <h1>Welcome to OAHB</h1>  
  
        <form class="form" action="userAction_login.action" id="form" method="post">  
          <input type="text" name="user.userName" placeholder="Username">  
          <input type="password" name="user.userPassword" placeholder="Password">
         <c:if test="${errMess!=''}">
        <div class="div1" style="color:red">
        ${errMess}
        </div>
        </c:if>  
          <button type="button" id="login-button">Login</button>
          <button type="button" id="register-button" onclick="{location.href='userAction_toRegist.action'}">Register</button>   
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
      },2000);
      
      //$('#formB').submit();
    });  
  </script>  

</body>
</html>