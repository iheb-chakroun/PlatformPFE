package entities.tracking;

import java.io.Serializable;

public class Location implements Serializable{
	
	private String fname;
	private String lname;
	private String entrepriseName;
	private String address;
	private String longitude;
	private String latitude;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEntrepriseName() {
		return entrepriseName;
	}
	public void setEntrepriseName(String entrepriseName) {
		this.entrepriseName = entrepriseName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
