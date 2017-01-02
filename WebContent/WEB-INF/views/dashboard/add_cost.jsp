<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Cost Information</title>
<style>.tb { border: 1px solid black; padding: 10px; }</style>
</head>
<body>
<center>
<ul style='padding:15px; border:3px solid black; font-size:30px; color:black; background:white;'>
Add Cost Information
</ul>
<ul style='padding:3px; border:1px solid black; font-size:12px; color:white; background:black;'>
${sessionScope.username} | ${sessionScope.role}
</ul>
	${menu}
	<form style='display: table;' action='?action=add_cost_action' method="post" class="tb">
	       <p>Type: <input name="type" type="text" /></p>
	      <p>Cost: <input name="cost" type="text" /></p>
		  <p>Name: <input name="name" type="text" /></p>
		<p><input type="submit" value="Submit" /></p>
	</form>
	<table class='tb'>
		<tr><th class='tb'>ID</th>
		<th class='tb'>Type</th>
		<th class='tb'>Cost</th>
		<th class='tb'>Name</th>
		<th class='tb'>Edit</th>
		<th class='tb'>Delete</th></tr>
		<c:forEach items="${costlist}" var="cost">
			<tr>
			    <td class='tb'>${cost.id}</td>
			    <td class='tb'>${cost.name}</td>
			    <td class='tb'>${cost.type}</td>
			    <td class='tb'>${cost.cost}</td>
			    <td class='tb'><a href="cost?action=edit_cost&id=${cost.id}">Edit</a></td>
			    <td class='tb'><a href="cost?action=delete_cost&id=${cost.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</center>
</body>
</html>