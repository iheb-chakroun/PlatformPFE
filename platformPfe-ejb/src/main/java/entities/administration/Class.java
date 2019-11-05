package entities.administration;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import entities.users.Student;

@Entity
public class Class implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	private String scholarYear;
	
	@ManyToOne
	private Option option;
	
	@JsonIgnoreProperties({"students"})
	@OneToMany(mappedBy="classe",fetch =FetchType.EAGER)
	private Set<Student> students;
	
	
	
	public Class(String label, String scholarYear) {
		super();
		this.label = label;
		this.scholarYear = scholarYear;
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
	public String getScholarYear() {
		return scholarYear;
	}
	public void setScholarYear(String scholarYear) {
		this.scholarYear = scholarYear;
	}
	public Option getOption() {
		return option;
	}
	public void setOption(Option option) {
		this.option = option;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	
	
}
