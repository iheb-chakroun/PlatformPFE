package entities.users;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import collection.Role;
import entities.documents.Thesis;

@Entity
public class TeacherRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
			private Role role;
	
	@ManyToOne
	@JsonIgnoreProperties({"teacherRole"})
    @JoinColumn(name="idThesis", referencedColumnName="id", insertable=true, updatable=true)
	
	private Thesis thesis;
	@ManyToOne

	@JoinColumn(name="idTeacher", referencedColumnName="id", insertable=true, updatable=true)
	@JsonProperty(value = "teacherRole")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
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
	@Override
	public String toString() {
		return "TeacherRole [id=" + id + ", role=" + role + ", thesis=" + thesis + ", teacher=" + teacher + "]";
	}
	
}
