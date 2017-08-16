<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>

<style>ul li a{text-decoration:none;}</style>	

<style type="text/css">
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  font-weight: 300;
}

body {
  font-family: 'Source Sans Pro', sans-serif;
  color: white;
  font-weight: 300;
}
.something{
	-webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  outline: 0;
  border: 1px solid rgba(255, 255, 255, 0.4);
  background-color: white;
  width: 300px;
  border-radius: 3px;
  padding: 10px 15px;
  margin: 0 auto 10px auto;
  display: block;
  text-align: center;
  font-size: 18px;
  color: black;
  -webkit-transition-duration: 0.25s;
          transition-duration: 0.25s;
  font-weight: 300;
}
.wrapper {
  background: #e0e0e0;
  top: 50%;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;

}


.container {
  padding: 80px 0;
  height: 100%;
  text-align: center;
}
.container h1 {
  font-size: 40px;
  font-weight: 200;
}
form {

font-family: 'Microsoft YaHei';
font-size:20px;
  padding: 20px 0;
  z-index: 2;
}
form input {
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  border: 1px solid rgba(255, 255, 255, 0.4);
 

  border-radius: 3px;
  padding: 10px 15px;
  margin: 10px auto 10px auto;

  text-align: center;
  font-size: 18px;
	background-color: white;
  width: 300px;
  color: #53e3a6;

  font-weight: 300;
}
form input:hover {
  background-color: rgba(255, 255, 255, 0.4);
}
form input:focus {
  background-color: white;
  width: 300px;
  color: #53e3a6;
}
form button {
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  outline: 0;
  background-color: white;
  border: 0;
  padding: 15px 15px;
  margin:11px auto auto auto;
  color: black;
  border-radius: 3px;
  width: 150px;
  cursor: black;
  font-size: 18px;
}
form button:hover {
  background-color: #f5f7f9;
}
.div1{
  text-align: center;

}
</style>


</script>
</head>
<body>
	<div class="htmleaf-container" style="overflow:hidden;">  
    <div class="wrapper">  
      <div class="container" style="font-family: 'Microsoft YaHei';color: black";font-size: 25px;">  

        <h1>添加部门</h1>  

		
  
        <form  class="form" action="userAction_addDepar.action" id="form" method="post">  
  	   
        <div class="div1"><input type="text" placeholder="部门名称" style="color:black;" name="nor" required/>
        <c:if test="${errMess!=''}">
        <div class="div1" style="color:red">
        ${errMess}
        </div>
        </c:if>

		</div>
   
			<input style="color:black;text-align: center;" type="submit" value="提交"/>  

         
             
        </form>  

      </div>  
  
    

  </div>
</div>
 

</body>
</html>