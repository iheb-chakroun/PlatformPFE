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
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((departements == null) ? 0 : departements.hashCode());
		result = prime * result + id;
		result = prime * result + ((internshipDirector == null) ? 0 : internshipDirector.hashCode());
		result = prime * result + nbrMaxAction;
		result = prime * result + nbrMaxVal;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((templates == null) ? 0 : templates.hashCode());
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
		Site other = (Site) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (departements == null) {
			if (other.departements != null)
				return false;
		} else if (!departements.equals(other.departements))
			return false;
		if (id != other.id)
			return false;
		if (internshipDirector == null) {
			if (other.internshipDirector != null)
				return false;
		} else if (!internshipDirector.equals(other.internshipDirector))
			return false;
		if (nbrMaxAction != other.nbrMaxAction)
			return false;
		if (nbrMaxVal != other.nbrMaxVal)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (templates == null) {
			if (other.templates != null)
				return false;
		} else if (!templates.equals(other.templates))
			return false;
		return true;
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
