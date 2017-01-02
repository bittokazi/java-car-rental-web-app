<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile - ${user.username}</title>
<style>.tb { border: 1px solid black; padding: 10px; }</style>
</head>
<body>
<center>
<ul style='padding:15px; border:3px solid black; font-size:30px; color:black; background:white;'>
Profile
</ul>
<ul style='padding:3px; border:1px solid black; font-size:12px; color:white; background:black;'>
${sessionScope.username} | ${sessionScope.role}
</ul>
	${menu}
	<table class='tb'>
		<tr>
		<th class='tb'>Image</th>
		<th class='tb'>Name</th>
		<th class='tb'>Username</th>
		<th class='tb'>Email</th>
		<th class='tb'>Cell</th>
		<th class='tb'>Role</th>
		<th class='tb'>Date Of Birth</th>
		<th class='tb'>Gender</th>
		</tr>
			<tr>
			 	<td class='tb'><img src="../uploads/${user.username}.jpg" height="200px" width="300px" /></td>
			    <td class='tb'>${user.name}</td>
			    <td class='tb'>${user.username}</td>
			    <td class='tb'>${user.email}</td>
			    <td class='tb'>${user.cell}</td>
			    <c:choose>
				    	<c:when test="${user.role=='rider'}">
				        	<td class='tb'>Rider</td>
				    	</c:when>
				    	<c:when test="${user.role=='driver'}">
				        	<td class='tb'>Driver</td>
				    	</c:when>
				    	<c:when test="${user.role=='administrator'}">
				        	<td class='tb'>Administrator</td>
				    	</c:when>
				</c:choose>
			    <td class='tb'>${user.dob}</td>
			    <td class='tb'>${user.gender}</td>
			</tr>
	</table>
</center>
</body>
</html>