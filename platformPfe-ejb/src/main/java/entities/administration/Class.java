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
	private Option option;
	@OneToMany(mappedBy="classe")
	private List<Student> students;
}
