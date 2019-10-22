package entities.administration;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import entities.users.InternshipDirector;

@Entity
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String address;
	private String nom;
	private int nbrMaxVal;
	private int nbrMaxAction;
	@ManyToOne
	private School school;
	@OneToMany(mappedBy = "site")
	private List<Template> templates;
	@OneToMany(mappedBy = "site")
	private List<Departement> departements;
	@OneToOne
	private InternshipDirector internshipDirector;

	public List<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

}
