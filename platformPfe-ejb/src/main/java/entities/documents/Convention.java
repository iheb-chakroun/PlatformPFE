package entities.documents;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import entities.users.Student;

@Entity
public class Convention implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonFormat(pattern="yyyy-MM-dd")

	private Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd")

	private Date endDate;
	@OneToOne
	private Student student;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Convention(int id, Date startDate, Date endDate, Student student) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.student = student;
	}
	public Convention() {
		super();
	}

}
