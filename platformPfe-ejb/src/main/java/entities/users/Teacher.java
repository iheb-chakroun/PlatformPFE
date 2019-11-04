package entities.users;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.administration.Departement;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.tracking.TeacherNotification;

@Entity
@DiscriminatorValue(value="teacher")
public class Teacher extends Employe implements Serializable{
	
	@JsonIgnore
	@ManyToOne
	private Departement departement;
	@JsonIgnore
	@OneToMany(mappedBy="pre_validator")
	private List<PfeFile> pfeFiles;
	@JsonIgnore
	@ManyToMany
	private List<Categorie> categories;
	@JsonIgnore
	@OneToMany(mappedBy="teacher")
	private List<TeacherNotification> teacherNotification;
	@JsonIgnore
	@OneToMany(mappedBy="teacher")
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
	public List<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(List<Categorie> categories) {
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
	
	
}
