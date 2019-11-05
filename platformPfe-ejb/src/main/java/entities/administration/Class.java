package entities.administration;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import entities.users.Student;

@Entity
public class Class {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	private String scholarYear;
	@ManyToOne
	private Specialite option;
	@OneToMany(mappedBy="classe")
	private List<Student> students;
	
	
	
	
	public Class(int id, String label, String scholarYear, Specialite option, List<Student> students) {
		super();
		this.id = id;
		this.label = label;
		this.scholarYear = scholarYear;
		//this.option = option;
		this.students = students;
	}
	
	
	
	public Class() {
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
	public String getScholarYear() {
		return scholarYear;
	}
	public void setScholarYear(String scholarYear) {
		this.scholarYear = scholarYear;
	}
	public Specialite getOption() {
		return option;
	}
	public void setOption(Specialite option) {
		this.option = option;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
	
}
