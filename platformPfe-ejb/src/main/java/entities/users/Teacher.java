package entities.users;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import entities.administration.Departement;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.tracking.TeacherNotification;

@Entity
@DiscriminatorValue(value="teacher")
public class Teacher extends Employe implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Departement departement;
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@OneToMany(cascade = CascadeType.ALL, mappedBy="pre_validator",fetch =FetchType.EAGER)
	private Set<PfeFile> pfeFiles;
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@ManyToMany(cascade = CascadeType.ALL,mappedBy="teachers",fetch =FetchType.EAGER)
	private Set<Categorie> categories;
	
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@OneToMany(mappedBy="teacher",fetch =FetchType.EAGER)
	private Set<TeacherRole> teacherRole;
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@OneToMany(cascade = CascadeType.ALL,mappedBy="teacher",fetch =FetchType.EAGER)
	private Set<TeacherNotification> teacherNotification;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	public Set<PfeFile> getPfeFiles() {
		return pfeFiles;
	}
	public void setPfeFiles(Set<PfeFile> pfeFiles) {
		this.pfeFiles = pfeFiles;
	}
	
	public Set<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}
	public Set<TeacherNotification> getTeacherNotification() {
		return teacherNotification;
	}
	public void setTeacherNotification(Set<TeacherNotification> teacherNotification) {
		this.teacherNotification = teacherNotification;
	}
	public Set<TeacherRole> getTeacherRole() {
		return teacherRole;
	}
	public void setTeacherRole(Set<TeacherRole> teacherRole) {
		this.teacherRole = teacherRole;
	}
	
	
	
}
