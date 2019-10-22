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
	
}
