package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import models.DriverOnline;

public class DriverOnlineController implements ServletContextListener {
	
	private Thread myThread = null;
	private MyThreadClass m1 = null;
	
	public static ConcurrentHashMap<String, DriverOnline> DriverOnline = new ConcurrentHashMap<>();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
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
			System.out.println("Driver Online Queue Started");
	        while(!stop){
	        		try {
	                Thread.sleep(1000);
	                for (ConcurrentHashMap.Entry<String, DriverOnline> entry : DriverOnlineController.DriverOnline.entrySet()) {
	            			if(entry.getValue().getTime()<System.currentTimeMillis()) {
	            				DriverOnlineController.DriverOnline.remove(entry.getKey());
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

}
