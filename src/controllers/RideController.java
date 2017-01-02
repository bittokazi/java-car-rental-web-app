package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adapters.CostAdapter;
import adapters.PlacesAdapter;
import models.Cost;
import models.Places;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class RideController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("rider")) {
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("query")) {
						
	                    
					}
					else {
						String menu=um.menu(ac.get_role(request).toString());
 						request.setAttribute("menu", menu);
 						
 						PlacesAdapter pa=new PlacesAdapter();
						ArrayList<Places> placelist = new ArrayList<Places>();
						placelist=pa.getAll();
						request.setAttribute("placelist", placelist);
 						
						RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/ride.jsp");
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
						
					RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/dashboard/ride.jsp");
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
		AccessControl ac=new AccessControl();
		UserMenu um=new UserMenu();
		if(ac.get_role(request)!=null) {
			if(ac.get_role(request).equals("rider")) {
				if(request.getParameterMap().containsKey("action")) {
					if(request.getParameter("action").equals("query")) {
						if(request.getParameterMap().containsKey("from") && request.getParameterMap().containsKey("to")) {
							String from_place=request.getParameter("from");
							String to_place=request.getParameter("to");
							HttpClient client1 = new DefaultHttpClient();
		                    HttpGet request1 = new HttpGet("https://maps.googleapis.com/maps/api/directions/json?origin="+URLEncoder.encode(from_place, "UTF-8")+"&destination="+URLEncoder.encode(to_place, "UTF-8")+"&key=AIzaSyCtl2RNlJcBkt34dAqmN1h45Un-xHP9VnQ");
		                    // Get the response
		                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
		                    String bodyHtml = client1.execute(request1, responseHandler);
		                    
		                    try {
								JSONObject obj = new JSONObject(bodyHtml);
								JSONArray routes = obj.getJSONArray("routes");
								JSONObject obj1 = routes.getJSONObject(0);
								JSONArray legs = obj1.getJSONArray("legs");
								JSONObject obj2 = legs.getJSONObject(0);
								JSONObject distance = obj2.getJSONObject("distance");
								JSONObject duration = obj2.getJSONObject("duration");
								
								Cost cost = new Cost();
								
								CostAdapter ca = new CostAdapter();
								
								double total_cost=0;
								cost = ca.select("SELECT * FROM cost WHERE type='base_value' ORDER BY id DESC LIMIT 0,1");
								total_cost=Double.parseDouble(cost.getCost());
								String base_value=cost.getCost();
								
								cost = ca.select("SELECT * FROM cost WHERE type='milage_value' ORDER BY id DESC LIMIT 0,1");
								total_cost=total_cost+Double.parseDouble(distance.getString("text").split(" ")[0])*Double.parseDouble(cost.getCost());
								String millage_value=cost.getCost();
								
								PrintWriter out = response.getWriter();
								out.println(distance.getString("text").split(" ")[0]+"-"+duration.getString("text")+"-"+total_cost+"-"+base_value+"-"+millage_value);
							} catch (JSONException e) {
								PrintWriter out = response.getWriter();
								out.println("error");
								e.printStackTrace();
							}
		                    
						}
	                    
					}
					else {
						this.doGet(request, response);
					}
				}
				else {
					this.doGet(request, response);
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

}
