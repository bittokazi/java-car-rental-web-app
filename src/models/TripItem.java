package models;

import java.util.ArrayList;
import java.util.List;

public class TripItem {
	private String userName;
	private Long time;
	private List<String> informedDrivers = new ArrayList<>();
	private Double latitude;
	private Double longitude;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public List<String> getInformedDrivers() {
		return informedDrivers;
	}
	public void setInformedDrivers(List<String> informedDrivers) {
		this.informedDrivers = informedDrivers;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
}
