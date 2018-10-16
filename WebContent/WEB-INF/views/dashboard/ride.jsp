<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ride</title>
<style>.tb { border: 1px solid black; padding: 10px; }</style>
<script src="/js/jquery-1.11.3.js"></script>


<style>
      #right-panel {
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
        display: none;
      }

      #right-panel select, #right-panel input {
        font-size: 15px;
      }

      #right-panel select {
        width: 100%;
      }

      #right-panel i {
        font-size: 12px;
      }
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
        float: left;
        width: 70%;
        height: 100%;
            left: 15%;
      }
      #right-panel {
        margin: 20px;
        border-width: 2px;
        width: 20%;
        height: 400px;
        float: left;
        text-align: left;
        padding-top: 0;
      }
      #directions-panel {
        margin-top: 10px;
        background-color: #FFEE77;
        padding: 10px;
      }
    </style>


</head>
<body>
<center> 
<ul style='padding:15px; border:3px solid black; font-size:30px; color:black; background:white;'>
Lets Ride
</ul>
<ul style='padding:3px; border:1px solid black; font-size:12px; color:white; background:black;'>
${sessionScope.username} | ${sessionScope.role}
</ul>
	${menu}
	<table class='tb' id="tb">
		<tr><th><p>From: &nbsp; &nbsp; <select id="from">
			<c:forEach items="${placelist}" var="place">
				<option value="${place.name}, Dhaka">${place.name}</option>
			</c:forEach>
		</select></p>
		<p>To: &nbsp; &nbsp; <select id="to">
			<c:forEach items="${placelist}" var="place">
				<option value="${place.name}, Dhaka">${place.name}</option>
			</c:forEach>
		</select></p></th></tr>
		<tr><th>
		<p><button onclick="get_info()">Check</button></p>
		</th></tr>
	</table>
	<div style="display: none; padding:20px; border:1px solid black; width:50%;" id="show_info">
		<p id="distance"></p>
		<p id="time"></p>
		<p id="base_value"></p>
		<p id="millage_value"></p>
		<p id="cost"></p>
		<p id="pn"><button onclick="open_info_panel();">Proceed</button></p>
	</div>	
	<div style="display: none; padding:20px; border:1px solid black; width:50%;" id="msg">
		Error
	</div>
	<div style="display: none; padding:20px; border:1px solid black; width:50%;" id="info_panel">
		<p>Name: &nbsp; &nbsp;<input type="text" id="name" /></p>
		<p>Cell: &nbsp; &nbsp;<input type="text" id="cell" /></p>
		<p>Email: &nbsp; &nbsp;<input type="text" id="email" /></p>
		<p>Address: &nbsp; &nbsp;<input type="text" id="address" /></p>
		<p>Date: &nbsp; &nbsp;<input type="text" id="date" /></p>
		<p>Time: &nbsp; &nbsp;<input type="text" id="time_1" /></p>
		<p>Car Type : &nbsp; &nbsp; &nbsp; <select name="car_type" id="car_type">
 								      <option value="SUV">SUV</option>
                                     <option value="Super Saloon">Super Sallon</option>
                                     <option value="Sedan">Sedan</option>
                                    </select></p>
		<p><button onclick="confirm_invoice();">Confirm Ride</button></p>
	</div>	
</center>









 <div id="map"></div>
    <div id="right-panel">
    <div>
    <b>Start:</b>
    <select id="start">
      
    </select>
    <br>
    <b>Waypoints:</b> <br>
    <i>(Ctrl+Click or Cmd+Click for multiple selection)</i> <br>
    <select multiple id="waypoints">

    </select>
    <br>
    <b>End:</b>
    <select id="end">
     	
    </select>
    <br>
      <input type="submit" id="submit">
    </div>
    <div id="directions-panel"></div>
    </div>
    <script>
      function initMap() {
        var directionsService = new google.maps.DirectionsService;
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 6,
          center: {lat: 41.85, lng: -87.65}
        });
        directionsDisplay.setMap(map);

        document.getElementById('submit').addEventListener('click', function() {
          calculateAndDisplayRoute(directionsService, directionsDisplay);
        });
      }

      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        var waypts = [];
        var checkboxArray = document.getElementById('waypoints');
        for (var i = 0; i < checkboxArray.length; i++) {
          if (checkboxArray.options[i].selected) {
            waypts.push({
              location: checkboxArray[i].value,
              stopover: true
            });
          }
        }

        directionsService.route({
          origin: document.getElementById('start').value,
          destination: document.getElementById('end').value,
          waypoints: waypts,
          optimizeWaypoints: true,
          travelMode: 'DRIVING'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            var summaryPanel = document.getElementById('directions-panel');
            summaryPanel.innerHTML = '';
            // For each route, display summary information.
            for (var i = 0; i < route.legs.length; i++) {
              var routeSegment = i + 1;
              summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment +
                  '</b><br>';
              summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
              summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
              summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
            }
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKzaiss5emcY1uGPaFIKFAiGFDUQ8uNwM&callback=initMap">
    </script>













<div id="msg_main" style=" display:none; 10px; color:white; width:100%; position:fixed;  bottom:30px;">
		<div id="msg_float" style="text-align:center; padding: 10px; color:white; width:220px; margin:0 auto; position:relative; z-index:100000; background:black;">
			Action
		</div>
	</div>

<script type="text/javascript">
function open_info_panel() {
	document.getElementById("pn").style.display="none";
	document.getElementById("info_panel").style.display="block";
}
var tc="";
var d="";
function get_info() {
	$("#map").fadeOut(700);
	$("#msg_main").fadeIn(700);
	$("#msg_float").text("Checking...");
	document.getElementById("pn").style.display="block";
	document.getElementById("show_info").style.display="none";
	document.getElementById("msg").style.display="none";
	document.getElementById("info_panel").style.display="none";
	var from=document.getElementById("from").value;
	var to=document.getElementById("to").value;
	
	document.getElementById("start").innerHTML="<option value=\""+from+"\">"+from+"</option>";
	document.getElementById("end").innerHTML="<option value=\""+to+"\">"+to+"</option>";
	$.ajax({
				type: "POST",
				url: "ride?action=query",
				data: { 
			        'from': from, 
			        'to': to
			    },
				success: function(result){
        			if(result=="error") {
        				$("#msg_float").text("Error Occured");
    					st=setTimeout(function () {
    					$("#msg_main").fadeOut(700);
    					},1500);
        				document.getElementById("show_info").style.display="none";
        				document.getElementById("msg").style.display="block";
        				
        				
        			}
        			if(result=="") {
        				$("#msg_float").text("Error Occured");
    					st=setTimeout(function () {
    					$("#msg_main").fadeOut(700);
    					},1500);
        				document.getElementById("show_info").style.display="none";
        				document.getElementById("msg").style.display="block";
        			}
        			else {
        				$("#map").fadeIn(700);
        				$("#msg_float").text("Successfully Retrived Result");
    					st=setTimeout(function () {
    					$("#msg_main").fadeOut(700);
    					},1500);
        				document.getElementById("show_info").style.display="block";
        				document.getElementById("msg").style.display="none";
        				document.getElementById("distance").innerHTML="Distance: "+result.split("-")[0]+" km";
        				document.getElementById("time").innerHTML="Time: "+result.split("-")[1];
        				document.getElementById("cost").innerHTML="Total Payable: "+result.split("-")[2];
        				tc=result.split("-")[2];
        				d=result.split("-")[0];
        				document.getElementById("base_value").innerHTML="Base Charge: "+result.split("-")[3];
        				document.getElementById("millage_value").innerHTML="Milage Charge: "+result.split("-")[4]+"/km";
        				$("#submit").click();
        				google.maps.event.trigger(map, 'resize');
        			}
    			},
				error: function() {
					$("#msg_float").text("Error Occured");
					st=setTimeout(function () {
					$("#msg_main").fadeOut(700);
					},1500);
    				document.getElementById("show_info").style.display="none";
    				document.getElementById("msg").style.display="block";
				}
			});
}
$("#map").fadeOut(700);

function confirm_invoice() {
	$("#msg_main").fadeIn(700);
	$("#msg_float").text("Placing Your Request...");
	
	var name=document.getElementById("name").value;
	var address=document.getElementById("address").value;
	var date=document.getElementById("date").value;
	var time=document.getElementById("time_1").value;
	var car_type=document.getElementById("car_type").value;
	var from=document.getElementById("from").value;
	var to=document.getElementById("to").value;
	var email=document.getElementById("email").value;
	var cell=document.getElementById("cell").value;
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	
	var cd=dd+"/"+mm+"/"+yyyy;
	if(name=="" || address=="" || date=="" || time=="" || car_type=="" || cell=="" || email=="") {
		$("#msg_float").text("Empty Fields");
		st=setTimeout(function () {
		$("#msg_main").fadeOut(700);
		},1500);
		return true;
	}
	
	
	$.ajax({
		type: "POST",
		url: "invoice?action=add_invoice",
		data: { 
	        'from': from, 
	        'to': to,
	        'name': name,
	        'address': address,
	        'date': date,
	        'time': time,
	        'type': car_type,
	        'cd': cd,
	        'd': d,
	        'tc': tc,
	        'cell': cell,
	        'email': email
	    },
		success: function(result){
			if(result=="ok") {
				$("#msg_float").text("Your Request is Queued");
				st=setTimeout(function () {
				$("#msg_main").fadeOut(700);
				},1500);
				document.getElementById("show_info").style.display="none";
				document.getElementById("info_panel").style.display="none";
				document.getElementById("tb").style.display="none";
				document.getElementById("msg").style.display="block";
				document.getElementById("msg").innerHTML="Your Request Is Queued Successfully. You will Be Notified.";
				$("#map").fadeOut(700);
			}

			else {
				$("#msg_float").text("Error Occured");
				st=setTimeout(function () {
				$("#msg_main").fadeOut(700);
				},1500);
			}
		},
		error: function() {
			$("#msg_float").text("Error Occured");
			st=setTimeout(function () {
			$("#msg_main").fadeOut(700);
			},1500);
		}
	});
}
</script>
</body>
</html>