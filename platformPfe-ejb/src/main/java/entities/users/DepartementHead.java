package entities.users;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.administration.Departement;
import entities.tracking.HeadDepartementNotification;

@Entity
@DiscriminatorValue(value="dep-head")
public class DepartementHead extends Employe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Attributes
	@JsonIgnoreProperties("departementHead")
	@OneToOne(mappedBy="departementHead")
	private Departement departement;
	
	@JsonIgnoreProperties("departementHead")
	@OneToMany(mappedBy = "departementHead")
	private List<HeadDepartementNotification> headDepartementNotification;
	
	//Getters & Setters
	
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	public List<HeadDepartementNotification> getHeadDepartementNotification() {
		return headDepartementNotification;
	}
	public void setHeadDepartementNotification(List<HeadDepartementNotification> headDepartementNotification) {
		this.headDepartementNotification = headDepartementNotification;
	}
	
	
}
