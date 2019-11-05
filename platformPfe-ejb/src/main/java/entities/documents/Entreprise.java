package entities.documents;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Entreprise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String website;
	private String address;
	private String country;
	private String nameResponsable;
	private String emailResponsable;
	private String emailEncadrent;
	@OneToOne(mappedBy="entreprise")
	@JsonIgnoreProperties({"entreprise"})
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
	public Entreprise(int id, String website, String address, String country, String nameResponsable,
			String emailResponsable, String emailEncadrent, PfeFile pfeFile) {
		super();
		this.id = id;
		this.website = website;
		this.address = address;
		this.country = country;
		this.nameResponsable = nameResponsable;
		this.emailResponsable = emailResponsable;
		this.emailEncadrent = emailEncadrent;
		this.pfeFile = pfeFile;
	}
	public Entreprise() {
		super();
	}
	@Override
	public String toString() {
		return "Entreprise [id=" + id + ", website=" + website + ", address=" + address + ", country=" + country
				+ ", nameResponsable=" + nameResponsable + ", emailResponsable=" + emailResponsable
				+ ", emailEncadrent=" + emailEncadrent + ", pfeFile=" + pfeFile + "]";
	}
}
