package controllers;

public class UserMenu {
	public String menu(String role) {
		if(role.equals("administrator")) {
			String m="<ul style='padding:15px; border:1px solid black; font-size:22px; color:white; background:black;'>";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"main\">Home</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"places\">Add Places</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"cost\">Cost Info</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"showdriverinfo\">Drivers</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"history\">History</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"updateprofile\">Update Profile</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"userlist\">User List</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"logout\">Logout</a>&nbsp; | &nbsp;";
			m=m+"</ul>";
			return m;
		}
		else if(role.equals("driver")) {
			String m="<ul style='padding:15px; border:1px solid black; font-size:22px; color:white; background:black;'>";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"main\">Home</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"drive\">Drive</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"schedule\">Schedule</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"history\">History</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"driverinfoupdate\">Update Car</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"updateprofile\">Update Profile</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"logout\">Logout</a>&nbsp; | &nbsp;";
			m=m+"</ul>";
			return m;
		}
		else if(role.equals("rider")) {
			String m="<ul style='padding:15px; border:1px solid black; font-size:22px; color:white; background:black;'>";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"main\">Home</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"ride\">Ride</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"schedule\">Schedule</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"pendingride\">Pending Ride</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"history\">History</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"updateprofile\">Update Profile</a>&nbsp; | &nbsp;";
			m=m+"<a style='text-decoration:none; color:white; font-size:22px;' href=\"logout\">Logout</a>&nbsp; | &nbsp;";
			m=m+"</ul>";
			return m;
		}
		else {
			String m="Access Denied";
			return m;
		}
	}
}

