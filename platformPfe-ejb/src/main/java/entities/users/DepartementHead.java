package entities.users;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.administration.Departement;

@Entity
@DiscriminatorValue(value="dep-head")
public class DepartementHead extends Employe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Attributes
	@JsonIgnoreProperties("departementHead")
	@OneToOne(mappedBy="departementHead")
	private Departement departement;
	
	//Getters & Setters
	
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	
}
