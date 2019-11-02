package entities.administration;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String label;

	@JsonIgnoreProperties("departements")
	@ManyToOne
	private Site site;

	@JsonIgnoreProperties("departement")
	@OneToMany(mappedBy = "departement")
	private List<Option> options;

	@JsonIgnoreProperties("departement")
	@OneToOne
	private DepartementHead departementHead;

	@JsonIgnoreProperties("departement")
	@OneToMany(mappedBy = "departement", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Teacher> teachers;

	// Note new attribute
	@JsonIgnoreProperties("departement")
	@OneToMany(mappedBy = "departement", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Classroom> classrooms;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departement other = (Departement) obj;
		if (id != other.id)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		return true;
	}

}
