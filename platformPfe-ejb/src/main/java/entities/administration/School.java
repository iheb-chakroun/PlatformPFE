package entities.administration;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import entities.users.Admin;

@Entity
public class School {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((colors == null) ? 0 : colors.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sites == null) ? 0 : sites.hashCode());
		result = prime * result + ((slogon == null) ? 0 : slogon.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (colors == null) {
			if (other.colors != null)
				return false;
		} else if (!colors.equals(other.colors))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sites == null) {
			if (other.sites != null)
				return false;
		} else if (!sites.equals(other.sites))
			return false;
		if (slogon == null) {
			if (other.slogon != null)
				return false;
		} else if (!slogon.equals(other.slogon))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSlogon() {
		return slogon;
	}

	public void setSlogon(String slogon) {
		this.slogon = slogon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public School(int id, String name, String address, String logo, String slogon, String email, String tel,
			String colors, List<Site> sites, Admin admin) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.logo = logo;
		this.slogon = slogon;
		this.email = email;
		this.tel = tel;
		this.colors = colors;
		this.sites = sites;
		this.admin = admin;
	}

	public School() {
		super();
		// TODO Auto-generated constructor stub
	}

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
