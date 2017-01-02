package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapters.UserAdapter;
import models.User;

public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator") || ac.get_role(request).equals("rider") || ac.get_role(request).equals("driver")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);
				
				
				UserAdapter ua=new UserAdapter();
				User user = new User();
				user=ua.select("SELECT * FROM user WHERE username='"+ac.get_username(request)+"' ORDER BY id DESC");
				request.setAttribute("user", user);
				
				RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/main.jsp");
				disp.forward(request, response);
			}
			else {
				response.sendRedirect("login?msg=nomatch");
			}
		}
		else {
			response.sendRedirect("login?msg=nomatch");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
