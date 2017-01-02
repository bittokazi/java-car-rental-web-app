package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapters.InvoiceAdapter;
import models.Invoice;

public class InvoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator") || ac.get_role(request).equals("rider") || ac.get_role(request).equals("driver")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);
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
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator") || ac.get_role(request).equals("rider") || ac.get_role(request).equals("driver")) {
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("add_invoice")) {
						InvoiceAdapter ia=new InvoiceAdapter();
						Invoice invoice=new Invoice();
						invoice.setUid("");
						invoice.setType(request.getParameter("type"));
						invoice.setRider_username(ac.get_username(request));
						invoice.setDriver_username("");
						invoice.setFrom_place(request.getParameter("from"));
						invoice.setTo_place(request.getParameter("to"));
						invoice.setPayment_status("pending");
						invoice.setDelivery_status("pending");
						invoice.setDistance(request.getParameter("d"));
						invoice.setCost(request.getParameter("tc"));
						invoice.setAddress(request.getParameter("address"));
						invoice.setDate(request.getParameter("date"));
						invoice.setTime(request.getParameter("time"));
						invoice.setCreate_date(request.getParameter("cd"));
						invoice.setName(request.getParameter("name"));
						invoice.setCell(request.getParameter("cell"));
						invoice.setEmail(request.getParameter("email"));
						ia.insert(invoice);
						PrintWriter out = response.getWriter();
						out.print("ok");
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
