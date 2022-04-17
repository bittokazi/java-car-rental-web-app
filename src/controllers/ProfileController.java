package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import adapters.InvoiceAdapter;
import adapters.UserAdapter;
import models.Invoice;
import models.User;

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		Map<String, Object> rest = new HashMap<>();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("rider") || ac.get_role(request).equals("driver") || ac.get_role(request).equals("administrator")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);
				
				if(request.getParameterMap().containsKey("un")) {
					if(!request.getParameter("un").equals("")) {
						
						
						UserAdapter ua=new UserAdapter();
						User user = new User();
						user=ua.select("SELECT * FROM public.user WHERE username='"+request.getParameter("un")+"' ORDER BY id DESC");
						user.setPassword("");
						if(request.getHeader("type")!=null && request.getHeader("type").contains("rest")) {
							response.setContentType("application/json");
							PrintWriter out = response.getWriter();
							out.print(new Gson().toJson(user));
						} else {
							request.setAttribute("user", user);
							RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/profile.jsp");
							disp.forward(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
