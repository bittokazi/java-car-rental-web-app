<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<script src="js/jquery-1.11.3.js"></script>
</head>
<body>
	<div style="margin:0 auto; width:466px; position:relative; text-align:center;">
		<h2 style="margin-top:100px;">User Registration</h2>
		<div id="main" style="margin-top:30px; width:400; padding:30px; position:relative; border:3px solid black; text-align:center;">
				
				<p>Name: &nbsp; &nbsp; &nbsp; <input type="text" name="n" id="n" /></p>
				<p>Username: &nbsp; &nbsp; &nbsp; <input type="text" name="un" id="un" /></p>
				<p>Password: &nbsp; &nbsp; &nbsp; <input type="text" name="p" id="pw" /></p>
				<p>Retype Password: &nbsp; &nbsp; &nbsp; <input type="text" name="rp" id="rpw" /></p>
				<p>Date Of Birth: &nbsp; &nbsp; &nbsp; <input type="text" name="dob" id="dob" /></p>
				<p>Mobile Number: &nbsp; &nbsp; &nbsp; <input type="text" name="mn" id="mn" /></p>
				<p>Email: &nbsp; &nbsp; &nbsp; <input type="text" name="email" id="email" /></p>
				<p>User Type: &nbsp; &nbsp; &nbsp; <select onchange="view_driver_info()" id="type" name="type">
 								      <option value="rider">Rider</option>
                                     <option value="driver"
                                     <c:choose>
  					<c:when test="${driver}">
                		selected
                	</c:when>
				</c:choose>
                                     >Driver</option>
                                    </select></p>
				<p>Gender : &nbsp; &nbsp; &nbsp; <select name="gender" id="gender">
 								      <option value="male">Male</option>
                                     <option value="female">Female</option>
                                    </select></p>
                <p id="license" style="
                <c:choose>
  					<c:when test="${driver}">
                		display:block;
                	</c:when>
				    <c:otherwise>
						display:none;
					</c:otherwise>
				</c:choose>
                ">Driver Lincense Number: &nbsp; &nbsp; &nbsp; <input type="text" name="license" id="l" /></p>
                <p id="cartype" style="
                <c:choose>
  					<c:when test="${driver}">
                		display:block;
                	</c:when>
				    <c:otherwise>
						display:none;
					</c:otherwise>
				</c:choose>
                ">Car Type : &nbsp; &nbsp; &nbsp; <select name="car_type" id="car_type">
 								      <option value="SUV">SUV</option>
                                     <option value="Super Saloon">Super Sallon</option>
                                     <option value="Sedan">Sedan</option>
                                    </select></p>
                 <p>Profile Picture: &nbsp;<input type="file" name="ione" id="ione" /></p>
				<p><input onclick="confirm_reg()" type="submit" name="submit" value="Register" style="background: none; border-radius: 20px; padding: 10px; font-size: 18px;" />&nbsp; &nbsp; &nbsp;</p>
				
		</div>
		<div id="main2" style="margin-top:30px; width:400; padding:20px; position:relative; color:white; border:3px solid black; text-align:center; display:none; background:green">
			<h3 id="smsg"></h3>
		</div>
	</div>
	
	<div id="msg_main" style=" display:none; 10px; color:white; width:100%; position:fixed;  bottom:30px;">
		<div id="msg" style="text-align:center; padding: 10px; color:white; width:220px; margin:0 auto; position:relative; z-index:100000; background:black;">
			Action
		</div>
	</div>
	
<script type="text/javascript">
function view_driver_info() {
	if(document.getElementById("license").style.display=="none") {
		document.getElementById("license").style.display="block";
		document.getElementById("cartype").style.display="block";
	}
	else {
		document.getElementById("license").style.display="none";
		document.getElementById("cartype").style.display="none";
	}
}
function confirm_reg() {
	$("#msg_main").fadeIn(700);
	$("#msg").text("Creating Account");
		var xmlhttp;
		var str22
		str22=document.getElementById("ione").value;
		var formData = new FormData();
		formData.append("ione", document.getElementById("ione").files[0])
		var usn=document.getElementById("un").value;
		var upw=document.getElementById("pw").value;
		var rupw=document.getElementById("rpw").value;
		var ufn=document.getElementById("n").value;
		var gender=document.getElementById("gender").value;
		var uemail=document.getElementById("email").value;
		var uphone=document.getElementById("mn").value;
		var dob=document.getElementById("dob").value;
		var type=document.getElementById("type").value;
		var l=document.getElementById("l").value;
		var car_type=document.getElementById("car_type").value;
		var error_num=0;
		
		if (ufn=="" || usn=="" || upw=="" || rupw=="" || upw!=rupw || uemail=="" || uphone=="" || dob=="") {
			error_num++;
		}
		if(type=="driver" && l=="") {
			error_num++;
		}
		if(error_num>0) {
			$("#msg").text("Empty Fields");
			st=setTimeout(function () {
			$("#msg_main").fadeOut(700);
			},1500);
			return true;
		}
		
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				if(xmlhttp.responseText=="ok") {
					$("#msg").text("Successfully Created");
					st=setTimeout(function () {
					$("#msg_main").fadeOut(700);
					},1500);
					document.getElementById("main").innerHTML="Account Created SuccessFully. <a href='dashboard/login'>Login</a>";
				}
				else if(xmlhttp.responseText=="exist") {
					$("#msg").text("Username or Email exist");
					st=setTimeout(function () {
					$("#msg_main").fadeOut(700);
					},1500);
				}
				else if(xmlhttp.responseText=="error") {
					$("#msg").text("File Error");
					st=setTimeout(function () {
					$("#msg_main").fadeOut(700);
					},1500);
				}
				else {
					$("#msg").text("Error");
					st=setTimeout(function () {
					$("#msg_main").fadeOut(700);
					},1500);
				}
			}
	   }
	xmlhttp.open("POST","confirm_reg?un="+usn+"&pw="+upw+"&n="+ufn+"&gender="+gender+"&email="+uemail+"&phone="+uphone+"&dob="+dob+"&type="+type+"&car_type="+car_type+"&l="+l+"",true);
	xmlhttp.send(formData);
}
</script>
</body>
</html>