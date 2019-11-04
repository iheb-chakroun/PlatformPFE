package entities.documents;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Entreprise {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String website;
	private String address;
	private String country;
	private String nameResponsable;
	private String emailResponsable;
	private String emailEncadrent;
	@JsonIgnoreProperties("entreprise")
	@OneToOne(mappedBy="entreprise")
	private PfeFile pfeFile;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNameResponsable() {
		return nameResponsable;
	}
	public void setNameResponsable(String nameResponsable) {
		this.nameResponsable = nameResponsable;
	}
	public String getEmailResponsable() {
		return emailResponsable;
	}
	public void setEmailResponsable(String emailResponsable) {
		this.emailResponsable = emailResponsable;
	}
	public String getEmailEncadrent() {
		return emailEncadrent;
	}
	public void setEmailEncadrent(String emailEncadrent) {
		this.emailEncadrent = emailEncadrent;
	}
	public PfeFile getPfeFile() {
		return pfeFile;
	}
	public void setPfeFile(PfeFile pfeFile) {
		this.pfeFile = pfeFile;
	}
	
	

}
