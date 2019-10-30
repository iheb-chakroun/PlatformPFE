package entities.administration;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import entities.users.InternshipDirector;



@Entity
public class Site implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String address;
	private String nom;
	private int nbrMaxVal;
	private int nbrMaxAction;
	@JsonIgnoreProperties("sites")
	@ManyToOne
	private School school;
	@OneToMany(mappedBy = "site")
	private List<Template> templates;
	@OneToMany(mappedBy = "site", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbrMaxVal() {
		return nbrMaxVal;
	}

	public void setNbrMaxVal(int nbrMaxVal) {
		this.nbrMaxVal = nbrMaxVal;
	}

	public int getNbrMaxAction() {
		return nbrMaxAction;
	}

	public void setNbrMaxAction(int nbrMaxAction) {
		this.nbrMaxAction = nbrMaxAction;
	}

	public InternshipDirector getInternshipDirector() {
		return internshipDirector;
	}

	public void setInternshipDirector(InternshipDirector internshipDirector) {
		this.internshipDirector = internshipDirector;
	}
	

}
