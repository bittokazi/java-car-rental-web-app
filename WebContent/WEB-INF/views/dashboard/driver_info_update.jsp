<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Car Driver Info</title>
<style>.tb { border: 1px solid black; padding: 10px; }</style>
</head>
<body>
<center>
<ul style='padding:15px; border:3px solid black; font-size:30px; color:black; background:white;'>
Driver Information Update
</ul>
<ul style='padding:3px; border:1px solid black; font-size:12px; color:white; background:black;'>
${sessionScope.username} | ${sessionScope.role}
</ul>
	${menu}
	<form style='display: table;' action='driverinfoupdate?action=update&id=${driver.username}' method="post" class="tb">
		<p>Car Type : &nbsp; &nbsp; &nbsp; <select name="car_type" id="car_type">
 								      <option value="SUV">SUV</option>
                                     <option value="Super Saloon">Super Sallon</option>
                                     <option value="Sedan">Sedan</option>
                                    </select></p>
		<p><input type="submit" value="Submit" /></p>
	</form>
</center>
</body>
</html>