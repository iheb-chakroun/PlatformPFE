package entities.documents;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import entities.users.TeacherRole;

@Entity
public class Thesis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String classroom;
	private Date date;
	private Date startTime;
	@OneToOne
	private PfeFile pfeFile;
	
	@OneToMany(mappedBy="thesis")
	private List<TeacherRole> teacherRole;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public PfeFile getPfeFile() {
		return pfeFile;
	}

	public void setPfeFile(PfeFile pfeFile) {
		this.pfeFile = pfeFile;
	}

	public List<TeacherRole> getTeacherRole() {
		return teacherRole;
	}

	public void setTeacherRole(List<TeacherRole> teacherRole) {
		this.teacherRole = teacherRole;
	}
	
	
	
}
