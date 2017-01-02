package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapters.CarDriverAdapter;
import adapters.PlacesAdapter;
import adapters.UserAdapter;
import models.CarDriver;
import models.Places;
import models.User;


public class ShowDriverInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     			AccessControl ac=new AccessControl();
     			UserMenu um=new UserMenu();
     			if(ac.get_role(request)!=null) {
     				if(ac.get_role(request).equals("administrator")) {
     					if(request.getParameterMap().containsKey("action")) {
     						if(request.getParameter("action").equals("change_status")) {
     							CarDriverAdapter cda=new CarDriverAdapter();
                        		CarDriver cardriver=new CarDriver();
                        		cardriver.setId(Integer.parseInt(request.getParameter("id")));
                        		cardriver.setStatus(request.getParameter("value"));
                        		cda.update_status(cardriver);
                        		PrintWriter out = response.getWriter();
        						out.println("Updated Driver Status");
     						}
     						else {
     							
     						}
     					}
     					else {
     						String menu=um.menu(ac.get_role(request).toString());
     						request.setAttribute("menu", menu);
     						
     						CarDriverAdapter cda=new CarDriverAdapter();
     						ArrayList<CarDriver> driverlist = new ArrayList<CarDriver>();
     						driverlist=cda.getAll();
     						request.setAttribute("driverlist", driverlist);
     						
     						
     						
     						RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/show_driver_info.jsp");
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
		
		
	}

}
