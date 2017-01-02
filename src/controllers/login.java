package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		if(ac.get_role(request)!=null) {
			response.sendRedirect("main");
			return;
		}
		else {
			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/login.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		if(request.getParameterMap().containsKey("un") && request.getParameterMap().containsKey("pw")) {
			if(!request.getParameter("un").equals("") && !request.getParameter("pw").equals("")) {
				
			}
			else {
				response.sendRedirect("login?msg=emptyf");
				return;
			}
		}
		else {
			response.sendRedirect("login?msg=emptyf");
			return;
		}
		if(ac.login_check(request, request.getParameter("un"), request.getParameter("pw"))) {
			response.sendRedirect("main");
		}
		else {
			response.sendRedirect("login?msg=nomatch");
		}
	}

}
