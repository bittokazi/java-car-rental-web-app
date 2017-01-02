<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<style>.tb { border: 1px solid black; padding: 10px; }</style>
</head>
<body>
<center>
<ul style='padding:15px; border:3px solid black; font-size:30px; color:black; background:white;'>
Dashboard Home
</ul>
<ul style='padding:3px; border:1px solid black; font-size:12px; color:white; background:black;'>
${sessionScope.username} | ${sessionScope.role}
</ul>
	${menu}

<ul style='padding:15px; border:3px solid black; font-size:22px; color:black; background:white;'>
<table class="tb">
<tr><td class="tb">Profile Picture:</td><td class="tb"><img src="../uploads/${user.image}" height="150px" width="150px" /></td></tr>
<tr><td class="tb">Your Name:</td><td class="tb">${user.name}</td></tr>
<tr><td class="tb">Your Username:</td><td class="tb">${user.username}</td></tr>
<tr><td class="tb">Your Email:</td><td class="tb">${user.email}</td></tr>
<tr><td class="tb">Your Cell:</td><td class="tb">${user.cell}</td></tr>
<tr><td class="tb">Your gender:</td><td class="tb">${user.gender}</td></tr>
<tr><td class="tb">Your Are:</td><td class="tb">${user.role}</td></tr>
</table>
</ul>
</center>
</body>
</html>