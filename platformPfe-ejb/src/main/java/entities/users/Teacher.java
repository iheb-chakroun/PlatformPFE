package entities.users;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import entities.administration.Departement;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.tracking.TeacherNotification;

@Entity
@DiscriminatorValue(value="teacher")
public class Teacher extends Employe implements Serializable{
	
	
	
	@ManyToOne
	private Departement departement;
	@OneToMany(mappedBy="pre_validator")
	private List<PfeFile> pfeFiles;
	@ManyToMany
	private List<Categorie> categories;
	@OneToMany(mappedBy="teacher")
	private List<TeacherNotification> teacherNotification;
	@OneToMany(mappedBy="teacher")
	private List<TeacherRole> teacherRole;
	
	
	
	
	public Teacher(Departement departement, List<PfeFile> pfeFiles, List<Categorie> categories,
			List<TeacherNotification> teacherNotification, List<TeacherRole> teacherRole) {
		super();
		this.departement = departement;
		this.pfeFiles = pfeFiles;
		this.categories = categories;
		this.teacherNotification = teacherNotification;
		this.teacherRole = teacherRole;
	}
	
	
	
	
	public Teacher() {
		super();
	}




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
