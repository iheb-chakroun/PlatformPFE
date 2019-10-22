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
}
