package entities.documents;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Categorie implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String label;
	private boolean status;
	@JsonIgnoreProperties({"categorie"})
	@OneToOne(mappedBy="categorie")
	private PfeFile pfefile;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Categorie() {
		super();
	}
	public PfeFile getPfefile() {
		return pfefile;
	}
	public void setPfefile(PfeFile pfefile) {
		this.pfefile = pfefile;
	}

}
