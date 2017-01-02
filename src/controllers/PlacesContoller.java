package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapters.PlacesAdapter;
import models.Places;

public class PlacesContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator")) {
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("edit_place")) {
						String menu=um.menu(ac.get_role(request).toString());
						request.setAttribute("menu", menu);
						
						Places place=new Places();
						PlacesAdapter pa=new PlacesAdapter();
						place=pa.get(Integer.parseInt(request.getParameter("id")));
						
						request.setAttribute("place", place);
						RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/edit_places.jsp");
						disp.forward(request, response);
					}
					else if(request.getParameter("action").equals("delete_place")) {
						Places place=new Places();
						PlacesAdapter pa=new PlacesAdapter();
						place=pa.get(Integer.parseInt(request.getParameter("id")));
						pa.delete(place.getId());
						
						PrintWriter out = response.getWriter();
						out.println("Deleted Place Successfully");
						
					}
					else {
						String menu=um.menu(ac.get_role(request).toString());
						request.setAttribute("menu", menu);
						
						PlacesAdapter pa=new PlacesAdapter();
						ArrayList<Places> placelist = new ArrayList<Places>();
						placelist=pa.getAll();
						request.setAttribute("placelist", placelist);
						
						RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/add_places.jsp");
						disp.forward(request, response);
					}
				}
				else {
					String menu=um.menu(ac.get_role(request).toString());
					request.setAttribute("menu", menu);
					
					PlacesAdapter pa=new PlacesAdapter();
					ArrayList<Places> placelist = new ArrayList<Places>();
					placelist=pa.getAll();
					request.setAttribute("placelist", placelist);
					
					RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/add_places.jsp");
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
				if(request.getParameter("action").equals("add_place_action")) {
					if(request.getParameter("name").equals("")) {
						PrintWriter out = response.getWriter();
						out.println("Empty Fields");
					}
					else {
						Places place=new Places();
						place.setName(request.getParameter("name"));
						PlacesAdapter pa=new PlacesAdapter();
						pa.insert(place);
						PrintWriter out = response.getWriter();
						out.println("Added Places");
					}
				}
				else if(request.getParameter("action").equals("edit_place_action")) {
					if(request.getParameter("name").equals("")) {
						PrintWriter out = response.getWriter();
						out.println("Empty Fields");
					}
					else {
						Places place=new Places();
						place.setName(request.getParameter("name"));
						place.setId(Integer.parseInt(request.getParameter("id")));
						PlacesAdapter pa=new PlacesAdapter();
						pa.update(place);
						PrintWriter out = response.getWriter();
						out.println("Edited Place Successfully");
					}
				}
				else {
					String menu=um.menu(ac.get_role(request).toString());
					request.setAttribute("menu", menu);
					RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/add_places.jsp");
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
