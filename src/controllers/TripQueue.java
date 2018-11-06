package controllers;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.http.client.ClientProtocolException;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import adapters.InvoiceAdapter;
import adapters.UserAdapter;
import models.DriverOnline;
import models.Invoice;
import models.TripItem;
import models.TripRequestNotification;
import models.User;
import util.DuploMap;
import util.FCMNotification;
import util.GooglePlacesServices;;

public class TripQueue implements ServletContextListener {
	
	private Thread myThread = null;
	private MyThreadClass m1 = null;
	public static ConcurrentHashMap<String, TripItem> TripList = new ConcurrentHashMap<>();
	ServletContextEvent sce;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		this.sce = sce;
		if ((myThread == null) || (!myThread.isAlive())) {
			m1=new MyThreadClass();  
            myThread = new Thread(m1);
            myThread.start();
        }
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
            m1.setStop(true);
            myThread.interrupt();
        } catch (Exception ex) {
        		ex.printStackTrace();
        }
	}
	
	class MyThreadClass implements Runnable{
		private Boolean stop = false;
		public void run(){
			System.out.println("Trip Queue Started");
	        while(!stop){
	        		try {
	                Thread.sleep(1000);
	                for (ConcurrentHashMap.Entry<String, TripItem> entry : TripQueue.TripList.entrySet()) {
	            			if(entry.getValue().getTime()<System.currentTimeMillis()) {
	            				TripQueue.TripList.remove(entry.getKey());
	            			} else {
	            				for (ConcurrentHashMap.Entry<String, DriverOnline> driver : DriverOnlineController.DriverOnline.entrySet()) {
		    	            			if(distance(driver.getValue().getLatitude(), driver.getValue().getLongitude(), entry.getValue().getLatitude(), entry.getValue().getLongitude(), 'K')<6.0) {
		    	            				if(!entry.getValue().getInformedDrivers().contains(driver.getKey())) {
		    	            					
		    	            					UserAdapter ua=new UserAdapter();
		    	            					User user1 = new User();
		    	            					user1=ua.select("SELECT * FROM public.user WHERE username='"+driver.getKey()+"' ORDER BY id DESC");
		    	            					final User user = user1;
		    	            					
		    	            					TripItem tripItem = new TripItem();
		    	            					tripItem.setInformedDrivers(entry.getValue().getInformedDrivers());
		    	            					tripItem.setLatitude(entry.getValue().getLatitude());
		    	            					tripItem.setLongitude(entry.getValue().getLongitude());
		    	            					tripItem.setTime(entry.getValue().getTime());
		    	            					tripItem.setUserName(entry.getValue().getUserName());
		    	            					TripQueue.TripList.put(entry.getKey(), tripItem);
		    	            					entry.getValue().getInformedDrivers().add(user.getUsername());
		    	            					new Runnable() {
		    	            						@Override
		    	            						public void run() {
		    	            							try {
		    	            								
			    		    	            					
			    		    	            					Invoice invoice=new Invoice();
			    		    	            					InvoiceAdapter ia=new InvoiceAdapter();
			    		    	            					invoice=ia.get(Integer.parseInt(entry.getKey()));
			    		    	            					
			    		    	            					TripRequestNotification tripRequestNotification = new GooglePlacesServices().getFromAndTo(invoice.getFrom_place(), invoice.getTo_place());
			    		    	        						tripRequestNotification.setId(String.valueOf(0));
			    		    	        						tripRequestNotification.setTripId(entry.getKey());
			    		    	        						tripRequestNotification.setCustomerId(invoice.getRider_username());
			    		    	        						tripRequestNotification.setPickUpStr(invoice.getFrom_place());
			    		    	        						tripRequestNotification.setDestinationStr(invoice.getTo_place());
			    		    	        						
			    		    	        						tripRequestNotification.setNotificationTitle("Drive Request");
			    		    	        						tripRequestNotification.setNotificationBody("You Have a new Drive Request");
			    		    	        						
			    		    	        						System.out.println(new Gson().toJson(tripRequestNotification));
			    		    	        						
			    		    	        						FCMNotification.sendDrivernotification(sce.getServletContext(), DuploMap.convert(new Gson().toJson(tripRequestNotification)), user.getFcm_token());
			    		    	            					
			    		    	        						
		    	            							} catch (JsonSyntaxException | FirebaseMessagingException e) {
		    	            								e.printStackTrace();
		    	            							} catch (IOException e) {
		    	            								// TODO Auto-generated catch block
		    	            								e.printStackTrace();
		    	            							}
		    	            							
		    	            						}
		    	            					}.run();
		    	            					
		    	        						
		    	            				}
		    	            			}
		    	                	}
	            			}
	                	}
	        		} catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	                return;
	            } catch(Exception e) {
	            		e.printStackTrace();
	            }
	        }
	    }
	    public Boolean getStop() {
	        return stop;
	    }

	    public void setStop(Boolean stop) {
	        this.stop = stop;
	    }    
	}
	private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      if (unit == 'K') {
	        dist = dist * 1.609344;
	      } else if (unit == 'N') {
	        dist = dist * 0.8684;
	        }
	      return (dist);
	    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
      return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
      return (rad * 180.0 / Math.PI);
    }
	    
}
