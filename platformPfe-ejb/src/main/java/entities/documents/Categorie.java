package entities.documents;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorie implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String label;
	private boolean status;
	
	
	public Categorie(int id, String label, boolean status) {
		super();
		this.id = id;
		this.label = label;
		this.status = status;
	}
	
	
	public Categorie(String label, boolean status) {
		super();
		this.label = label;
		this.status = status;
	}


	public Categorie() {
		super();
	}


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

	public boolean getStatus() {
		return status;
	}
	
}
