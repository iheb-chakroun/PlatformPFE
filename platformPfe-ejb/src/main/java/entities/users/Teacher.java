package entities.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import entities.administration.Departement;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.documents.Thesis;
import entities.tracking.TeacherNotification;

@Entity
@DiscriminatorValue(value = "teacher")
public class Teacher extends Employe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Departement departement;
	@OneToMany(mappedBy = "pre_validator")
	private List<PfeFile> pfeFiles;
	@ManyToMany
	private List<Categorie> categories;
	@OneToMany(mappedBy = "teacher")
	private List<TeacherNotification> teacherNotification;
	@OneToMany(mappedBy = "teacher")
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

	//Note isAvailable : to check if the teacher is available in a given date
	public boolean isAvailable(Date date) {
		for (TeacherRole tr : teacherRole) {
			if (tr.getThesis().getDate() == date) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Note isValid : to check if the teacher can take another action 
	 * and to check if has already a role in a given thesis
	 * @param thesis
	 * @param date
	 * @return
	 */
	public boolean isValid(Thesis thesis, Date date) {
		if (this.teacherRole.size() < this.departement.getSite().getNbrMaxAction()) {
			return false;
		}
		
		
		for (TeacherRole tr : this.teacherRole) {
			if (tr.getThesis().equals(thesis)) {
				return false;
			}
			if (tr.getThesis().getDate() == date) {
				return false;
			}
		}

		return true;
	}

}
