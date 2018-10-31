package models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripRequestNotification {
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("tripId")
	@Expose
	private String tripId;
	@SerializedName("customerId")
	@Expose
	private String customerId;
	@SerializedName("driverId")
	@Expose
	private String driverId;
	@SerializedName("pickUpStr")
	@Expose
	private String pickUpStr;
	@SerializedName("destinationStr")
	@Expose
	private String destinationStr;
	@SerializedName("pickUp")
	@Expose
	private String picUp;
	@SerializedName("destination")
	@Expose
	private String destination;
	@SerializedName("total_fare")
	@Expose
	private String total_fare;
	@SerializedName("location")
	@Expose
	private String location;
	@SerializedName("rotation")
	@Expose
	private String rotation;
	@SerializedName("bearing")
	@Expose
	private String bearing;
	@SerializedName("amount")
	@Expose
	private String amount;
	@SerializedName("medium")
	@Expose
	private String medium;
	
	@SerializedName("notificationTitle")
	@Expose
	private String notificationTitle;
	
	@SerializedName("notificationBody")
	@Expose
	private String notificationBody;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPickUpStr() {
		return pickUpStr;
	}
	public void setPickUpStr(String pickUpStr) {
		this.pickUpStr = pickUpStr;
	}
	public String getDestinationStr() {
		return destinationStr;
	}
	public void setDestinationStr(String destinationStr) {
		this.destinationStr = destinationStr;
	}
	public String getPicUp() {
		return picUp;
	}
	public void setPicUp(String picUp) {
		this.picUp = picUp;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getTotal_fare() {
		return total_fare;
	}
	public void setTotal_fare(String total_fare) {
		this.total_fare = total_fare;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRotation() {
		return rotation;
	}
	public void setRotation(String rotation) {
		this.rotation = rotation;
	}
	public String getBearing() {
		return bearing;
	}
	public void setBearing(String bearing) {
		this.bearing = bearing;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getNotificationTitle() {
		return notificationTitle;
	}
	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}
	public String getNotificationBody() {
		return notificationBody;
	}
	public void setNotificationBody(String notificationBody) {
		this.notificationBody = notificationBody;
	}
	
}
