package entities.administration;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import entities.users.DepartementHead;
import entities.users.Teacher;
@Entity
public class Departement implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	@ManyToOne
	private Site site;
	@OneToMany(mappedBy="departement")
	private List<Specialite> options;
	@OneToOne
	private DepartementHead departementHead;
	@OneToMany(mappedBy="departement")
	private List<Teacher> teachers;
	
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public List<Specialite> getOptions() {
		return options;
	}
	public void setOptions(List<Specialite> options) {
		this.options = options;
	}
	
	

}
