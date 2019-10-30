package entities.administration;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import entities.documents.Thesis;

@Entity
public class Classroom implements Serializable {
	/**
	 * Note add new class Classroom
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToMany(mappedBy="classroom")
	private List<Schedule> schedules;
	@ManyToOne
	private Departement departement;
	@OneToMany(mappedBy="classroom")
	private List<Thesis> thesis;
	public List<Thesis> getThesis() {
		return thesis;
	}
	public void setThesis(List<Thesis> thesis) {
		this.thesis = thesis;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	public List<Schedule> getSchedule() {
		return schedules;
	}
	public void setSchedule(List<Schedule> schedule) {
		this.schedules = schedule;
	}

	public boolean isAvailable(Date date) {
		for (Schedule schedule : schedules) {
			if (schedule.getDate() == date)
				return false;
		}
		return true;
	}
	
	
	
	
}
