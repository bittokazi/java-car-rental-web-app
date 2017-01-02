<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to Dashboard</title>
</head>
<body>
	<div style="margin:0 auto; width:466px; position:relative; text-align:center;">
		<h2 style="margin-top:100px;">LOGIN</h2>
		<div id="main" style="margin-top:30px; width:400; padding:30px; position:relative; border:3px solid black; text-align:center;">
				<form action="" method="POST">
				
				<c:choose>
				    	<c:when test="${param.msg=='nomatch'}">
				        	<p>Username Or Password do not Match</p>
				    	</c:when>
				    	<c:when test="${param.msg=='emptyf'}">
				        	<p>Empty Fields</p>
				    	</c:when>
				</c:choose>
				
				<p>Username: &nbsp; &nbsp; &nbsp; <input type="text" name="un" /></p>
				<p>password: &nbsp; &nbsp; &nbsp; <input type="password" name="pw" /></p>
				<p><input type="submit" name="submit" value="login" style="background: none; border-radius: 20px; padding: 10px; font-size: 18px;" />&nbsp; &nbsp; &nbsp;</p>
				</form>
		</div>
		<div id="main2" style="margin-top:30px; width:400; padding:20px; position:relative; color:white; border:3px solid black; text-align:center; display:none; background:green">
			<h3 id="smsg"></h3>
		</div>
	</div>
</body>
</html>