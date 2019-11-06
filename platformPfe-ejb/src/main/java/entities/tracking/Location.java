package entities.tracking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Location implements Serializable{
	
	private String first_name;
	private String last_name;
	private String website;
	private String address;
	private ArrayList<String> point;
	
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<String> getPoint() {
		return point;
	}
	public void setPoint(ArrayList<String> point) {
		this.point = point;
	}
	

}
