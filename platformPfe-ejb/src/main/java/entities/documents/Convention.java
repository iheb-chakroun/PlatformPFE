package entities.documents;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import entities.users.Student;

@Entity
public class Convention {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date startDate;
	private Date endDate;
	@OneToOne
	private Student student;

}
