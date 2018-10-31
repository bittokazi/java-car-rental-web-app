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
			System.out.println(System.getenv("OPENSHIFT_FCM_CONFIG_CR"));
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
			    .putAllData(data)
			    .setTopic(topic)
			    .build();

		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Successfully sent message: " + response);
	}
}
