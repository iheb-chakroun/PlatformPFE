package entities.administration;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Schedule implements Serializable{
	/**
	 * Note added new class schedule
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private boolean available;
	
	@JsonIgnore
	@ManyToOne
	private Classroom classroom;
	
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
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	
	
}
