package entities.users;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.administration.Departement;
import entities.documents.PfeFile;

@Entity
@DiscriminatorValue(value="dep-head")
public class DepartementHead extends Employe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Attributes
	
	@JsonIgnoreProperties({"departementHead","site"})
	@OneToOne(mappedBy="departementHead")
	private Departement departement;
	@JsonIgnore
	@OneToMany(mappedBy="moderator")
	private Set<PfeFile> moderatedFiles;
	
	//Getters & Setters
	
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	
}
