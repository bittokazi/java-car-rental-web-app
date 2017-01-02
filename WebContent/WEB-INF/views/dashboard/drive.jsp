<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Drive</title>
<style>.tb { border: 1px solid black; padding: 10px; }</style>


<script src="../js/jquery-1.11.3.js"></script>


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
      img {
      max-width:none;
      }
      #map {
        height: 100%;
        float: left;
        width: 70%;
            left: 15%;
            max-width:none;
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
Drive
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
		<th class='tb'>Order Date</th>
		<th class='tb'>Accept</th>
		</tr>
		<c:forEach items="${drivelist}" var="drive">
			<tr>
			    <td class='tb'>${drive.id}</td>
			    <td class='tb'>${drive.name}</td>
			    <td class='tb'>${drive.email} <button onclick="view_map('${drive.from_place}, dhaka', '${drive.to_place}, dhaka');">View on Map</button></td>
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
			    <td class='tb'><a href="drive?action=accept_ride&id=${drive.id}">Accept</a></td>
			</tr>
		</c:forEach>
	</table>
</center>










<div style="background-color:rgba(0, 0, 0, 0.5); position:fixed; display:none; height:100%; width:100%; left:0; top:0;" id="gmap">


<div id="map"></div>
<button onclick="close_map();" style="z-index:1000000; float:right">Close Map</button>
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

<script>
function view_map(from, to) {
	$('#gmap').fadeIn(1000);
	document.getElementById("start").innerHTML="<option value=\""+from+"\">"+from+"</option>";
	document.getElementById("end").innerHTML="<option value=\""+to+"\">"+to+"</option>";
	$("#submit").click();
	google.maps.event.trigger(map, 'resize');
}
function close_map() {
	$('#gmap').fadeOut(1000);
}
$('#gmap').fadeOut(100);
</script>
</div>
</body>
</html>