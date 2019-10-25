package entities.users;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import collection.Role;
import entities.documents.Thesis;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class TeacherRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Role role;
	
	@ManyToOne
	@JsonIgnoreProperties({"teacherRole"})
    @JoinColumn(name="idThesis", referencedColumnName="id", insertable=false, updatable=false)
	
	private Thesis thesis;
	@ManyToOne
	@JoinColumn(name="idTeacher", referencedColumnName="id", insertable=false, updatable=false)
	private Teacher teacher;
	public TeacherRole(int id, Role role, Thesis thesis, Teacher teacher) {
		super();
		this.id = id;
		this.role = role;
		this.thesis = thesis;
		this.teacher = teacher;
	}
	public TeacherRole() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Thesis getThesis() {
		return thesis;
	}
	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
