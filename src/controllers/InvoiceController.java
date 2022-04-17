package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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
import adapters.UserAdapter;
import models.Invoice;
import models.TripItem;
import models.TripRequestNotification;
import models.User;
import util.DuploMap;
import util.FCMNotification;
import util.GooglePlacesServices;

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
						long tripId = Long.parseLong(String.valueOf(ia.insert(invoice)));
						
						UserAdapter ua=new UserAdapter();
						User user = new User();
						user=ua.select("SELECT * FROM public.user WHERE username='"+ac.get_username(request)+"' ORDER BY id DESC");

						TripItem tripItem = new TripItem();
						List<Double> pup = new GooglePlacesServices().getPickupLatLng(request.getParameter("from"), request.getParameter("to"));
						tripItem.setLatitude(pup.get(0));
						tripItem.setLongitude(pup.get(1));
						tripItem.setTime(System.currentTimeMillis()+900000);
						tripItem.setUserName(ac.get_username(request));
						TripQueue.TripList.put(String.valueOf(tripId), tripItem);
						
//						TripRequestNotification tripRequestNotification = new GooglePlacesServices().getFromAndTo(request.getParameter("from"), request.getParameter("to"));
//						tripRequestNotification.setId(String.valueOf(0));
//						tripRequestNotification.setTripId(String.valueOf(tripId));
//						tripRequestNotification.setCustomerId(String.valueOf(user.getUsername()));
//						tripRequestNotification.setPickUpStr(request.getParameter("from"));
//						tripRequestNotification.setDestinationStr(request.getParameter("to"));
//						
//						tripRequestNotification.setNotificationTitle("Drive Request");
//						tripRequestNotification.setNotificationBody("You Have a new Drive Request");
//						
//						System.out.println(new Gson().toJson(tripRequestNotification));
//						
//						new Runnable() {
//							
//							@Override
//							public void run() {
//								try {
//									FCMNotification.sendTnotification(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)));
//								} catch (JsonSyntaxException | FirebaseMessagingException e) {
//									e.printStackTrace();
//								} catch (IOException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//						}.run();
						
						if(request.getHeader("type")!=null &&  request.getHeader("type").contains("rest")) {
							response.setContentType("application/json");
							Map<String, Object> rest = new HashMap<>();
							PrintWriter out = response.getWriter();
							rest.put("success", true);
							rest.put("tripId", tripId);
							rest.put("message", "Trip Request Successful");
							out.print(new Gson().toJson(rest));
						} else {
							PrintWriter out = response.getWriter();
							out.print("ok");
						}
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
