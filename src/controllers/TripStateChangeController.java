package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import adapters.InvoiceAdapter;
import models.Invoice;
import models.TripRequestNotification;
import util.DuploMap;
import util.FCMNotification;
import util.GooglePlacesServices;

public class TripStateChangeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		String status = request.getParameter("action");
		
		response.setContentType("application/json");
		Map<String, Object> rest = new HashMap<>();
		
		Invoice invoice=new Invoice();
		InvoiceAdapter ia=new InvoiceAdapter();
		invoice=ia.get(Integer.parseInt(request.getParameter("tripId")));
		
		if(status.contains("accept") && !invoice.getDriver_username().equals("")) {
			if(request.getHeader("type")!=null &&  request.getHeader("type").contains("rest")) {
				response.setStatus(422);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				rest.put("success", false);
				rest.put("message", "Ride Already Accepted");
				out.print(new Gson().toJson(rest));
			} else {
				response.sendRedirect("login?msg=nomatch");
			}
			return;
		}
		
		
		if(status.contains("accept")) {
			ia.accept_ride(invoice, ac.get_username(request));
			TripRequestNotification tripRequestNotification = generateNoti(invoice);
			tripRequestNotification.setDriverId(ac.get_username(request));
			tripRequestNotification.setId("1");
			try {
				FCMNotification.sendRideAcceptnotification(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), invoice.getRider_username());
			} catch (JsonSyntaxException | FirebaseMessagingException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(rest));
		} else if(status.contains("onTheWay")) {
			TripRequestNotification tripRequestNotification = generateNoti(invoice);
			tripRequestNotification.setId("2");
			try {
				FCMNotification.sendOnTheWay(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), invoice.getRider_username());
			} catch (JsonSyntaxException | FirebaseMessagingException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(rest));
		} else if(status.contains("WayToDelivery")) {
			TripRequestNotification tripRequestNotification = generateNoti(invoice);
			tripRequestNotification.setId("3");
			try {
				FCMNotification.sendOnTheWayToDelivery(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), invoice.getRider_username());
			} catch (JsonSyntaxException | FirebaseMessagingException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(rest));
		} else if(status.contains("tripEnded")) {
			TripRequestNotification tripRequestNotification = generateNoti(invoice);
			tripRequestNotification.setId("4");
			try {
				FCMNotification.tripEnded(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), invoice.getRider_username());
			} catch (JsonSyntaxException | FirebaseMessagingException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(rest));
		} else if(status.contains("location")) {
			TripRequestNotification tripRequestNotification = generateNoti(invoice);
			tripRequestNotification.setId("8");
			tripRequestNotification.setLocation(request.getParameter("location"));
			tripRequestNotification.setRotation(request.getParameter("rotation"));
			tripRequestNotification.setBearing(request.getParameter("bearing"));
			try {
				FCMNotification.sendLocation(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), invoice.getRider_username());
			} catch (JsonSyntaxException | FirebaseMessagingException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(rest));
		} else if(status.contains("payment")) {
			TripRequestNotification tripRequestNotification = generateNoti(invoice);
			tripRequestNotification.setId("7");
			tripRequestNotification.setMedium(request.getParameter("medium"));
			tripRequestNotification.setAmount(invoice.getCost());
			tripRequestNotification.setCustomerId(invoice.getRider_username());
			try {
				FCMNotification.sendPayment(getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), invoice.getDriver_username());
			} catch (JsonSyntaxException | FirebaseMessagingException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(rest));
		} else if(status.contains("driverRating")) {
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(rest));
		}
	}
	
	private TripRequestNotification generateNoti(Invoice invoice) throws ClientProtocolException, IOException {
		TripRequestNotification tripRequestNotification = new GooglePlacesServices().getFromAndTo(invoice.getFrom_place(), invoice.getTo_place());
		tripRequestNotification.setTripId(String.valueOf(invoice.getId()));
		tripRequestNotification.setDriverId(invoice.getDriver_username());
		tripRequestNotification.setCustomerId(invoice.getRider_username());
		tripRequestNotification.setTotal_fare(invoice.getCost());
		return tripRequestNotification;
	}
}
