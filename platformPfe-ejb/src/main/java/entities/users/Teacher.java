package entities.users;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import entities.administration.Departement;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.tracking.TeacherNotification;

@Entity
@DiscriminatorValue(value = "teacher")

public class Teacher extends Employe implements Serializable {
	@ManyToOne
	private Departement departement;
	@OneToMany(mappedBy = "pre_validator", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "pre_validator" })
	private List<PfeFile> pfeFiles;
	@ManyToMany
	@JsonIgnore
	private Set<Categorie> categories;
	@OneToMany(mappedBy = "teacher", fetch= FetchType.EAGER)
	

	private List<TeacherNotification> teacherNotification;

	@OneToMany(mappedBy = "teacher")
	@JsonIgnore
	private List<TeacherRole> teacherRole;

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public List<PfeFile> getPfeFiles() {
		return pfeFiles;
	}

	public void setPfeFiles(List<PfeFile> pfeFiles) {
		this.pfeFiles = pfeFiles;
	}

	public Set<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}

	public List<TeacherNotification> getTeacherNotification() {
		return teacherNotification;
	}

	public void setTeacherNotification(List<TeacherNotification> teacherNotification) {
		this.teacherNotification = teacherNotification;
	}

	public List<TeacherRole> getTeacherRole() {
		return teacherRole;
	}

	public void setTeacherRole(List<TeacherRole> teacherRole) {
		this.teacherRole = teacherRole;
	}

	public Teacher() {
		super();
	}

}
