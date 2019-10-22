package entities.administration;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Option {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String label;
	private String responsibleName;
	private String responsibleTel;
	private String responsibleEmail;
	@ManyToOne
	private Departement departement;
	@OneToMany(mappedBy="option")
	private List<Class> classes;
	
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	
}
