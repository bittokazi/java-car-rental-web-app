package util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;


public class FCMNotification {
	public static FirebaseApp initFirebase(ServletContext context) throws IOException {
			InputStream serviceAccount = new ByteArrayInputStream(System.getenv("OPENSHIFT_FCM_CONFIG_CR").getBytes());
			FirebaseOptions options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			    .setDatabaseUrl("https://car-rental-141815.firebaseio.com/")
		    .build();

			return FirebaseApp.initializeApp(options);
	}
	public static void sendTnotification(ServletContext context, Map<String, String> data) throws FirebaseMessagingException, IOException {
		System.out.println(new Gson().toJson(data));
		try {
			FCMNotification.initFirebase(context);
		} catch (Exception e) {
			
		}
		String topic = "all";
		Message message = Message.builder()
				.setNotification(new Notification("Drive Request", "You have a new ride request"))
			    .putAllData(data)
			    .setTopic(topic)
			    .build();

		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Successfully sent message: " + response);
	}
	
	public static void sendRideAcceptnotification(ServletContext context, Map<String, String> data, String username) throws FirebaseMessagingException, IOException {
		System.out.println(new Gson().toJson(data));
		try {
			FCMNotification.initFirebase(context);
		} catch (Exception e) {
			
		}
		String topic = "rider-"+username;
		Message message = Message.builder()
				.setNotification(new Notification("Driver Found", "Driver Accepted your request"))
			    .putAllData(data)
			    .setTopic(topic)
			    .build();

		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Successfully sent message: " + response);
	}
	
	public static void sendOnTheWay(ServletContext context, Map<String, String> data, String username) throws FirebaseMessagingException, IOException {
		System.out.println(new Gson().toJson(data));
		try {
			FCMNotification.initFirebase(context);
		} catch (Exception e) {
			
		}
		String topic = "rider-"+username;
		Message message = Message.builder()
				.setNotification(new Notification("Driver On the way", "Driver is On the way"))
			    .putAllData(data)
			    .setTopic(topic)
			    .build();

		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Successfully sent message: " + response);
	}
	public static void sendOnTheWayToDelivery(ServletContext context, Map<String, String> data, String username) throws FirebaseMessagingException, IOException {
		System.out.println(new Gson().toJson(data));
		try {
			FCMNotification.initFirebase(context);
		} catch (Exception e) {
			
		}
		String topic = "rider-"+username;
		Message message = Message.builder()
				.setNotification(new Notification("On the way to Delivery", "Driver is On the way to Destination"))
			    .putAllData(data)
			    .setTopic(topic)
			    .build();

		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Successfully sent message: " + response);
	}
	public static void tripEnded(ServletContext context, Map<String, String> data, String username) throws FirebaseMessagingException, IOException {
		System.out.println(new Gson().toJson(data));
		try {
			FCMNotification.initFirebase(context);
		} catch (Exception e) {
			
		}
		String topic = "rider-"+username;
		Message message = Message.builder()
				.setNotification(new Notification("Trip Ended", "Trip ended Please Pay"))
			    .putAllData(data)
			    .setTopic(topic)
			    .build();

		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Successfully sent message: " + response);
	}
	public static void sendLocation(ServletContext context, Map<String, String> data, String username) throws FirebaseMessagingException, IOException {
		System.out.println(new Gson().toJson(data));
		try {
			FCMNotification.initFirebase(context);
		} catch (Exception e) {
			
		}
		String topic = "rider-"+username;
		Message message = Message.builder()
			    .putAllData(data)
			    .setTopic(topic)
			    .build();

		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Successfully sent message: " + response);
	}
	public static void sendPayment(ServletContext context, Map<String, String> data, String username) throws FirebaseMessagingException, IOException {
		System.out.println(new Gson().toJson(data));
		try {
			FCMNotification.initFirebase(context);
		} catch (Exception e) {
			
		}
		String topic = "driver-"+username;
		Message message = Message.builder()
				.setNotification(new Notification("Payment Received", "Trip Payment Completed"))
			    .putAllData(data)
			    .setTopic(topic)
			    .build();

		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Successfully sent message: " + response);
	}
}
