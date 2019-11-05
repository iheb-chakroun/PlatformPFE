package entities.documents;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.users.TeacherRole;

@Entity
public class Thesis implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String classroom;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	@JsonFormat(pattern="HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
	@OneToOne
	@JsonIgnoreProperties({ "thesis" })
	private PfeFile pfeFile;

	@OneToMany(mappedBy = "thesis", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "thesis" })
	private Set<TeacherRole> teacherRole;

	public Thesis(int id, String classroom, Date date, Date startTime, PfeFile pfeFile, Set<TeacherRole> teacherRole) {
		super();
		this.id = id;
		this.classroom = classroom;
		this.date = date;
		this.startTime = startTime;
		this.pfeFile = pfeFile;
		this.teacherRole = teacherRole;
	}

	public Thesis() {
		super();
	}

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

	public Set<TeacherRole> getTeacherRole() {
		return teacherRole;
	}

	public void setTeacherRole(Set<TeacherRole> teacherRole) {
		this.teacherRole = teacherRole;
	}

	@Override
	public String toString() {
		return "Thesis [id=" + id + ", classroom=" + classroom + ", date=" + date + ", startTime=" + startTime
				+ ", pfeFile=" + pfeFile + ", teacherRole=" + teacherRole + "]";
	}

}
