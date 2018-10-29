package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import adapters.CostAdapter;
import models.Cost;
import models.TripRequestNotification;

public class GooglePlacesServices {
	public TripRequestNotification getFromAndTo(String from_place, String to_place) throws ClientProtocolException, IOException {
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
			JSONObject start_location = obj2.getJSONObject("start_location");
			JSONObject end_location = obj2.getJSONObject("end_location");
			
			TripRequestNotification tripRequestNotification = new TripRequestNotification();
			
			List<Double> puckup = new ArrayList<>();
			puckup.add(start_location.getDouble("lng"));
			puckup.add(start_location.getDouble("lat"));
			tripRequestNotification.setPicUp(new Gson().toJson(puckup));
			
			List<Double> destination = new ArrayList<>();
			destination.add(end_location.getDouble("lng"));
			destination.add(end_location.getDouble("lat"));
			tripRequestNotification.setDestination(new Gson().toJson(destination));
			
			return tripRequestNotification;

		} catch (JSONException e) {
			return null;
		}
	}
}
