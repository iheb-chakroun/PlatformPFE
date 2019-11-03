package entities.administration;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import entities.users.Admin;

@Entity
public class School implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String logo;
	private String slogon;
	private String email;
	private String tel;
	private String colors;
	@OneToMany(mappedBy = "school")
	private List<Site> sites;

	@OneToOne
	private Admin admin;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

}
