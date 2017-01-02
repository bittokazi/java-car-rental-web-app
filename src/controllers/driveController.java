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
import adapters.PlacesAdapter;
import models.CarDriver;
import models.Invoice;
import models.Places;

public class driveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("driver")) {
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("accept_ride")) {
						CarDriverAdapter cda=new CarDriverAdapter();
						CarDriver driver= new CarDriver();
						driver=cda.select("SELECT * FROM driver_info WHERE username='"+ac.get_username(request)+"' ORDER BY id DESC");
						if(driver.getStatus().equals("accept")) {
							Invoice invoice=new Invoice();
							InvoiceAdapter ia=new InvoiceAdapter();
							invoice=ia.get(Integer.parseInt(request.getParameter("id")));
							ia.accept_ride(invoice, ac.get_username(request));
							
							PrintWriter out = response.getWriter();
							out.println("Accepted Ride, Rider Will Be Notified.");
						}
						else if(driver.getStatus().equals("decline")) {
							PrintWriter out = response.getWriter();
							out.println("You are not Permitted to drive.");
						}
						else {
							PrintWriter out = response.getWriter();
							out.println("You are Pending admin confirmation. you cant drive");
						}
					}
					else {
						String menu=um.menu(ac.get_role(request).toString());
 						request.setAttribute("menu", menu);
 						
 						InvoiceAdapter ia=new InvoiceAdapter();
						ArrayList<Invoice> drivelist = new ArrayList<Invoice>();
						drivelist=ia.select_query("SELECT * FROM invoice WHERE delivery_status='pending' ORDER BY id DESC");
						request.setAttribute("drivelist", drivelist);
						
 						
						RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/drive.jsp");
						disp.forward(request, response);
					}
				}
				else {
					String menu=um.menu(ac.get_role(request).toString());
						request.setAttribute("menu", menu);
						
						InvoiceAdapter ia=new InvoiceAdapter();
					ArrayList<Invoice> drivelist = new ArrayList<Invoice>();
					drivelist=ia.select_query("SELECT * FROM invoice WHERE delivery_status='pending' ORDER BY id DESC");
					request.setAttribute("drivelist", drivelist);
					
						
					RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/drive.jsp");
					disp.forward(request, response);
				}
			}
			else {
				response.sendRedirect("login");
			}
		}
		else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
