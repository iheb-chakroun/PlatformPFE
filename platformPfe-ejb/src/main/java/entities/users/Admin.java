package entities.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.OneToOne;

import entities.administration.School;

@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends Employe {

	@OneToOne(mappedBy = "admin")
	private School school;
	

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
