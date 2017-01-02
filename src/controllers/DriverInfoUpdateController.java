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
import adapters.InvoiceAdapter;
import models.CarDriver;
import models.Invoice;


public class DriverInfoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("driver")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);
				
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("delete")) {
						
						
						
						return;
					}
				}
				CarDriverAdapter cda=new CarDriverAdapter();
				CarDriver driver= new CarDriver();
				driver=cda.select("SELECT * FROM driver_info WHERE username='"+ac.get_username(request)+"' ORDER BY id DESC");
				request.setAttribute("driver", driver);

				RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/driver_info_update.jsp");
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
			if(ac.get_role(request).equals("driver")) {
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("update")) {
						CarDriverAdapter cda=new CarDriverAdapter();
		        		CarDriver cardriver=new CarDriver();
		        		cardriver.setUsername(request.getParameter("id"));
		        		cardriver.setCar_type(request.getParameter("car_type"));
		        		cda.update_car(cardriver);
		        		PrintWriter out = response.getWriter();
						out.println("Updated Driver Status");
						return;
					}
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
