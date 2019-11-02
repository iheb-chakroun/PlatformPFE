package entities.documents;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.administration.Classroom;
import entities.users.TeacherRole;

@Entity
public class Thesis implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//Note added the classroom where the thesis will occur
	@JsonIgnoreProperties({ "thesis" })
	@ManyToOne
	private Classroom classroom;
	
	//Note we need to add the annotation @Temporal when we have date
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@JsonIgnoreProperties({ "thesis" })
	@OneToOne
	private PfeFile pfeFile;
	
	@JsonIgnoreProperties({ "thesis" })
	@OneToMany(mappedBy="thesis")
	private List<TeacherRole> teacherRole;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	//Note added hashCode and equals for thesis
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thesis other = (Thesis) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	
}
