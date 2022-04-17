package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.DriverOnline;
import models.Location;

public class DriverOnlineUpdaterController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		Map<String, Object> rest = new HashMap<>();
		if(ac.get_role(request)!=null) {
			Location location = new Gson().fromJson(request.getParameter("location"), Location.class);
			DriverOnline driverOnline = new DriverOnline();
			driverOnline.setLatitude(location.getLatitude());
			driverOnline.setLongitude(location.getLongitude());
			driverOnline.setTime(System.currentTimeMillis()+300000);
			DriverOnlineController.DriverOnline.put(ac.get_username(request), driverOnline);
					
		} else {
			if(request.getHeader("type")!=null &&  request.getHeader("type").contains("rest")) {
				response.setStatus(401);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				rest.put("success", false);
				rest.put("message", "Username or Password Do not match");
				out.print(new Gson().toJson(rest));
			} else {
				response.sendRedirect("login?msg=nomatch");
			}
		}
	}
}
