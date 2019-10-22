package entities.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import collection.Role;
import entities.documents.Thesis;

@Entity
public class TeacherRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="idThesis", referencedColumnName="id", insertable=false, updatable=false)
	private Thesis thesis;
	@ManyToOne
	@JoinColumn(name="idTeacher", referencedColumnName="id", insertable=false, updatable=false)
	private Teacher teacher;
	
}
