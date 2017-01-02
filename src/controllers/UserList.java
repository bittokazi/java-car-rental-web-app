package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapters.InvoiceAdapter;
import adapters.UserAdapter;
import models.Invoice;
import models.User;

public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);

					UserAdapter ua=new UserAdapter();
					ArrayList<User> users = new ArrayList<User>();
					users=ua.select_users("SELECT * FROM user ORDER BY id DESC");
					request.setAttribute("users", users);


				RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/userlist.jsp");
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
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);
				
				if(request.getParameterMap().containsKey("action") && request.getParameterMap().containsKey("value")) {
					if(request.getParameter("action").equals("search") && !request.getParameter("value").equals("")) {
						UserAdapter ua=new UserAdapter();
						ArrayList<User> users = new ArrayList<User>();
						users=ua.select_users("SELECT * FROM user WHERE "+request.getParameter("type")+" LIKE'%"+request.getParameter("value")+"%' ORDER BY id DESC");
						request.setAttribute("users", users);


						RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/userlist.jsp");
						disp.forward(request, response);
						
						
						return;
					}
				}
					UserAdapter ua=new UserAdapter();
					ArrayList<User> users = new ArrayList<User>();

					request.setAttribute("users", users);


				RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/userlist.jsp");
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

}
