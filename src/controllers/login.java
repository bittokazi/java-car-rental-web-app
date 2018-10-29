package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

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
		Map<String, Object> rest = new HashMap<>();
		if(request.getParameterMap().containsKey("un") && request.getParameterMap().containsKey("pw")) {
			if(!request.getParameter("un").equals("") && !request.getParameter("pw").equals("")) {
				
			}
			else {
				if(request.getHeader("type")!=null && request.getHeader("type").contains("rest")) {
					response.setStatus(401);
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					rest.put("success", false);
					rest.put("message", "Empty Field");
					out.print(new Gson().toJson(rest));
				} else {
					response.sendRedirect("login?msg=emptyf");
				}
				return;
			}
		}
		else {
			if(request.getHeader("type")!=null && request.getHeader("type").contains("rest")) {
				response.setStatus(401);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				rest.put("success", false);
				rest.put("message", "Empty Field");
				out.print(new Gson().toJson(rest));
			} else {
				response.sendRedirect("login?msg=emptyf");
			}
			return;
		}
		if(ac.login_check(request, request.getParameter("un"), request.getParameter("pw"))) {
			if(request.getHeader("type")!=null && request.getHeader("type").contains("rest")) {
				HttpSession session=request.getSession();
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				rest.put("success", true);
				rest.put("message", "Login Success");
				rest.put("role", session.getAttribute("role"));
				rest.put("token", request.getSession().getId());
				out.print(new Gson().toJson(rest));
			} else {
				response.sendRedirect("main");
			}
		}
		else {
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
