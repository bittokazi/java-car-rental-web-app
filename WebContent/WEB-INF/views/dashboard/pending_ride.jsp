<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Ride</title>
<style>.tb { border: 1px solid black; padding: 5px; }</style>
</head>
<body>
<center>
<ul style='padding:15px; border:3px solid black; font-size:30px; color:black; background:white;'>
Pending Ride
</ul>
<ul style='padding:3px; border:1px solid black; font-size:12px; color:white; background:black;'>
${sessionScope.username} | ${sessionScope.role}
</ul>
	${menu}
	<table class='tb'>
		<tr>
		<th class='tb'>ID</th>
		<th class='tb'>Name</th>
		<th class='tb'>Email</th>
		<th class='tb'>Cell</th>
		<th class='tb'>Car Type</th>
		<th class='tb'>Username</th>
		<th class='tb'>From</th>
		<th class='tb'>Destination</th>
		<th class='tb'>Distance</th>
		<th class='tb'>Cost</th>
		<th class='tb'>Address</th>
		<th class='tb'>Date</th>
		<th class='tb'>Time</th>
		<th class='tb'>Create Date</th>
		<th class='tb'>Status</th>
		</tr>
		<c:forEach items="${drivelist}" var="drive">
			<tr>
			    <td class='tb'>${drive.id}</td>
			    <td class='tb'>${drive.name}</td>
			    <td class='tb'>${drive.email}</td>
			    <td class='tb'>${drive.cell}</td>
			    <td class='tb'>${drive.type}</td>
			    <td class='tb'>${drive.rider_username}</td>
			    <td class='tb'>${drive.from_place}</td>
			    <td class='tb'>${drive.to_place}</td>
			    <td class='tb'>${drive.distance}</td>
			    <td class='tb'>${drive.cost}</td>
			    <td class='tb'>${drive.address}</td>
			    <td class='tb'>${drive.date}</td>
			    <td class='tb'>${drive.time}</td>
			    <td class='tb'>${drive.create_date}</td>
			    <td class='tb'>Pending</td>
			</tr>
		</c:forEach>
	</table>
</center>
</body>
</html>