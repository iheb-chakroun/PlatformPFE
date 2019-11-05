package entities.administration;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.users.DepartementHead;
import entities.users.Teacher;

@Entity
public class Departement implements Serializable {
	
	//Attributes
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	@ManyToOne
	private Site site;
	@JsonIgnoreProperties({"departement","classes"})
	@OneToMany(mappedBy="departement")
	private List<Option> options;
	@OneToOne
	private DepartementHead departementHead;
	@OneToMany(mappedBy="departement")
	private List<Teacher> teachers;
	
	//Default Constructor
	public Departement() {
		super();
	}
	
	//Getters & Setters
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DepartementHead getDepartementHead() {
		return departementHead;
	}

	public void setDepartementHead(DepartementHead departementHead) {
		this.departementHead = departementHead;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
}
