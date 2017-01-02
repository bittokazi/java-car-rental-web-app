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
import models.Invoice;

public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator") || ac.get_role(request).equals("rider") || ac.get_role(request).equals("driver")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);
				
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("delete")) {
						InvoiceAdapter ia=new InvoiceAdapter();
						
						if(ac.get_role(request).equals("administrator")) {
							ia.delete("DELETE FROM invoice WHERE id='"+request.getParameter("id")+"'");
						}
						else if(ac.get_role(request).equals("rider")) {
							ia.delete("DELETE FROM invoice WHERE id='"+request.getParameter("id")+"' AND rider_username='"+ac.get_username(request)+"'");
						}
						else if(ac.get_role(request).equals("driver")) {
							ia.delete("DELETE FROM invoice WHERE id='"+request.getParameter("id")+"' AND driver_username='"+ac.get_username(request)+"'");
						}
						PrintWriter out = response.getWriter();
						out.println("Deleted Invoice");
						return;
					}
				}
				InvoiceAdapter ia=new InvoiceAdapter();
				ArrayList<Invoice> drivelist = new ArrayList<Invoice>();
				if(ac.get_role(request).equals("rider")) {
					drivelist=ia.select_query("SELECT * FROM invoice WHERE rider_username='"+ac.get_username(request)+"' ORDER BY id DESC");
				}
				else if(ac.get_role(request).equals("driver")) {
					drivelist=ia.select_query("SELECT * FROM invoice WHERE driver_username='"+ac.get_username(request)+"' ORDER BY id DESC");
				}
				else if(ac.get_role(request).equals("administrator")) {
					drivelist=ia.select_query("SELECT * FROM invoice ORDER BY id DESC");
				}
				request.setAttribute("drivelist", drivelist);

				RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/history.jsp");
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

	}

}