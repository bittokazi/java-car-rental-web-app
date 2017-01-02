package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapters.CostAdapter;
import models.Cost;


public class CostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator")) {
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("edit_cost")) {
						String menu=um.menu(ac.get_role(request).toString());
						request.setAttribute("menu", menu);
						
						Cost cost = new Cost();
						
						CostAdapter ca = new CostAdapter();
						
						cost =ca.get(Integer.parseInt(request.getParameter("id")));
						
						request.setAttribute("cost", cost);
						RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/edit_cost.jsp");
						disp.forward(request, response);
					}
					else if(request.getParameter("action").equals("delete_cost")) {
						
						
                         Cost cost = new Cost();
						
						CostAdapter ca = new CostAdapter();
						
						cost =ca.get(Integer.parseInt(request.getParameter("id")));
						
						ca.delete(cost.getId());
					
						
						PrintWriter out = response.getWriter();
						out.println("Deleted Cost Successfully");
						
					}
					else {
						String menu=um.menu(ac.get_role(request).toString());
						request.setAttribute("menu", menu);
						
						CostAdapter ca = new CostAdapter();

						ArrayList<Cost> costlist = new ArrayList<Cost>();
						costlist=ca.getAll();
						request.setAttribute("costlist", costlist);
						
						RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/add_cost.jsp");
						disp.forward(request, response);
					}
				}
				else {
					String menu=um.menu(ac.get_role(request).toString());
					request.setAttribute("menu", menu);
					
					CostAdapter ca = new CostAdapter();

					ArrayList<Cost> costlist = new ArrayList<Cost>();
					costlist=ca.getAll();
					request.setAttribute("costlist", costlist);
					
					RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/add_cost.jsp");
					disp.forward(request, response);
				}
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
				if(request.getParameter("action").equals("add_cost_action")) {
					if(request.getParameter("name").equals("") || request.getParameter("type").equals("") || request.getParameter("cost").equals("")) {
						PrintWriter out = response.getWriter();
						out.println("Empty Fields");
					}
					else {
						
						
						Cost cost = new Cost();
						
						cost.setName(request.getParameter("name"));
						cost.setCost(request.getParameter("cost"));
						cost.setType(request.getParameter("type"));
						
						CostAdapter ca=new CostAdapter();
						ca.insert(cost);
						PrintWriter out = response.getWriter();
						out.println("Added Cost");
					}
				}
				else if(request.getParameter("action").equals("edit_cost_action")) {
					if(request.getParameter("name").equals("") || request.getParameter("type").equals("") || request.getParameter("cost").equals("")) {
						PrintWriter out = response.getWriter();
						out.println("Empty Fields");
					}
					else {

						Cost cost = new Cost();
					    cost.setName(request.getParameter("name"));
						cost.setType(request.getParameter("type"));
						cost.setCost(request.getParameter("cost"));
						cost.setId(Integer.parseInt(request.getParameter("id")));
						CostAdapter ca=new CostAdapter();
						ca.update(cost);
						PrintWriter out = response.getWriter();
						out.println("Edited Cost Successfully");
					}
				}
				else {
					String menu=um.menu(ac.get_role(request).toString());
					request.setAttribute("menu", menu);
					RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/add_cost.jsp");
					disp.forward(request, response);
				}
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
