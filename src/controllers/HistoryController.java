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

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import adapters.InvoiceAdapter;
import models.Invoice;
import models.TripRequestNotification;
import util.DuploMap;
import util.FCMNotification;

public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		Map<String, Object> rest = new HashMap<>();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("administrator") || ac.get_role(request).equals("rider") || ac.get_role(request).equals("driver")) {
				String menu=um.menu(ac.get_role(request).toString());
				request.setAttribute("menu", menu);
				
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("delete")) {
						InvoiceAdapter ia=new InvoiceAdapter();
						
						
						Invoice invoice=new Invoice();
						InvoiceAdapter iaC=new InvoiceAdapter();
						invoice=iaC.get(Integer.parseInt(request.getParameter("id")));
						TripQueue.TripList.remove(String.valueOf(invoice.getId()));
						TripRequestNotification tripRequestNotification = new TripRequestNotification();
						tripRequestNotification.setId("9");
						tripRequestNotification.setTripId(request.getParameter("id"));
						tripRequestNotification.setNotificationTitle("Trip Cancelled");
						tripRequestNotification.setNotificationBody("Trip Cancelled");
						if(!invoice.getDriver_username().equals("")) {
							final String un = invoice.getDriver_username();
							new Runnable() {
								@Override
								public void run() {
									try {
										FCMNotification.tripCancelled(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), un);
									} catch (JsonSyntaxException | FirebaseMessagingException e) {
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
							}.run();
						}
						final String un = invoice.getRider_username();
						new Runnable() {
							@Override
							public void run() {
								try {
									FCMNotification.tripCancelled(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), un);
								} catch (JsonSyntaxException | FirebaseMessagingException e) {
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						}.run();
						
						
						
						if(ac.get_role(request).equals("administrator")) {
							ia.delete("DELETE FROM invoice WHERE id='"+request.getParameter("id")+"'");
						}
						else if(ac.get_role(request).equals("rider")) {
							ia.delete("DELETE FROM invoice WHERE id='"+request.getParameter("id")+"' AND rider_username='"+ac.get_username(request)+"'");
						}
						else if(ac.get_role(request).equals("driver")) {
							ia.delete("DELETE FROM invoice WHERE id='"+request.getParameter("id")+"' AND driver_username='"+ac.get_username(request)+"'");
						}
						if(request.getHeader("type")!=null &&  request.getHeader("type").contains("rest")) {
							response.setContentType("application/json");
							PrintWriter out = response.getWriter();
							out.print(new Gson().toJson(rest));
						} else {
							PrintWriter out = response.getWriter();
							out.println("Deleted Invoice");
						}
						return;
					}
				}
				
				
				
				
				InvoiceAdapter ia=new InvoiceAdapter();
				ArrayList<Invoice> drivelist = new ArrayList<Invoice>();
				if(ac.get_role(request).equals("rider")) {
					drivelist=ia.select_query("SELECT * FROM invoice WHERE rider_username='"+ac.get_username(request)+"' ORDER BY id DESC LIMIT 30 OFFSET 0");
				}
				else if(ac.get_role(request).equals("driver")) {
					drivelist=ia.select_query("SELECT * FROM invoice WHERE driver_username='"+ac.get_username(request)+"' ORDER BY id DESC LIMIT 30 OFFSET 0");
				}
				else if(ac.get_role(request).equals("administrator")) {
					drivelist=ia.select_query("SELECT * FROM invoice ORDER BY id DESC");
				}
				request.setAttribute("drivelist", drivelist);

				
				if(request.getHeader("type")!=null &&  request.getHeader("type").contains("rest")) {
					response.setContentType("application/json");
					PrintWriter out = response.getWriter();
					out.print(new Gson().toJson(drivelist));
				} else {
					RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/history.jsp");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}