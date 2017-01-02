<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Driver Information</title>
<style>.tb { border: 1px solid black; padding: 10px; }</style>
</head>
<body>
<center>
<ul style='padding:15px; border:3px solid black; font-size:30px; color:black; background:white;'>
Show Driver Info
</ul>
<ul style='padding:3px; border:1px solid black; font-size:12px; color:white; background:black;'>
${sessionScope.username} | ${sessionScope.role}
</ul>
${menu}
<table class='tb'>
		<tr><th class='tb'>ID</th>
		<th class='tb'>Username</th>
		<th class='tb'>Status</th>
		<th class='tb'>license Number</th>
		<th class='tb'>Car Type</th>
		<th class='tb'>Approve</th>
		<th class='tb'>Decline</th>
		<th class='tb'>View Profile</th>
			<c:forEach items="${driverlist}" var="driver">
			<tr>
			    <td class='tb'>${driver.id}</td>
			    <td class='tb'>${driver.username}</td>
			    <td class='tb'>${driver.status}</td>
			    <td class='tb'>${driver.license_id}</td>
			    <td class='tb'>${driver.car_type}</td>
			    <td class='tb'><a href="showdriverinfo?action=change_status&id=${driver.id}&value=accept">Accept</a></td>
			    <td class='tb'><a href="showdriverinfo?action=change_status&id=${driver.id}&value=decline">Decline</a></td>
			    <td class='tb'><a href="profile?un=${driver.username}&value=decline">View Profile</a></td>
			 </tr>
		</c:forEach>
	</table>
	

</center>
</body>
</html>