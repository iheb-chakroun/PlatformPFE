package entities.administration;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.users.InternshipDirector;

@Entity
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String address;
	private String nom;
	private int nbrMaxVal;
	private int nbrMaxEnc;
	private int nbrMaxSup;
	private int nbrMaxPres;
	
	//@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfSessionStarts;
	@JsonFormat(pattern="yyyy-MM-dd")
	//@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfSessionEnds;

	@JsonIgnoreProperties("sites")
	@ManyToOne(cascade=CascadeType.REFRESH)
	private School school;

	@JsonIgnore
	@OneToMany(mappedBy = "site")
	private List<Template> templates;

	@JsonIgnore
	@OneToMany(mappedBy = "site", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Departement> departements;
	
	@JsonIgnoreProperties("site")
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

	public InternshipDirector getInternshipDirector() {
		return internshipDirector;
	}

	public void setInternshipDirector(InternshipDirector internshipDirector) {
		this.internshipDirector = internshipDirector;
	}

	public Date getDateOfSessionStarts() {
		return dateOfSessionStarts;
	}

	public void setDateOfSessionStarts(Date dateOfSessionStarts) {
		this.dateOfSessionStarts = dateOfSessionStarts;
	}

	public Date getDateOfSessionEnds() {
		return dateOfSessionEnds;
	}

	public void setDateOfSessionEnds(Date dateOfSessionEnds) {
		this.dateOfSessionEnds = dateOfSessionEnds;
	}

	public int getNbrMaxEnc() {
		return nbrMaxEnc;
	}

	public void setNbrMaxEnc(int nbrMaxEnc) {
		this.nbrMaxEnc = nbrMaxEnc;
	}

	public int getNbrMaxSup() {
		return nbrMaxSup;
	}

	public void setNbrMaxSup(int nbrMaxSup) {
		this.nbrMaxSup = nbrMaxSup;
	}

	public int getNbrMaxPres() {
		return nbrMaxPres;
	}

	public void setNbrMaxPres(int nbrMaxPres) {
		this.nbrMaxPres = nbrMaxPres;
	}
	
	
}
