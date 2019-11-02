package entities.users;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import entities.administration.Class;
import entities.documents.Convention;
import entities.documents.PfeFile;
import entities.tracking.StudentNotification;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String email;
	private String sexe;
	private String tel;
	private String password;
	private boolean status;
	@ManyToOne
	private Class classe;
	@OneToOne(mappedBy="student")
	private Convention convention;
	@OneToMany(mappedBy="student")
	private List<StudentNotification> notifications;
	@OneToOne(mappedBy="student",fetch=FetchType.EAGER)
	private PfeFile pfeFile;

}
