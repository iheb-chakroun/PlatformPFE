package entities.users;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import entities.administration.School;

@Entity
@DiscriminatorValue(value = "Admin")
public class Admin extends Employe implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToOne(mappedBy = "admin")
	private School school;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
