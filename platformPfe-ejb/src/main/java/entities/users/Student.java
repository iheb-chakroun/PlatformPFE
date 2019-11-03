package entities.users;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.administration.Class;
import entities.documents.Convention;
import entities.documents.PfeFile;
import entities.tracking.StudentNotification;

@Entity
public class Student implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	//Attributes
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
	
	//Associations
	@JsonIgnoreProperties({"students","id"})
	@ManyToOne
	private Class classe;

	@OneToOne(mappedBy="student")
	private Convention convention;
	
	@JsonIgnore
	@OneToMany(mappedBy="student",fetch = FetchType.EAGER)
	private Set<StudentNotification> notifications;
	
	@JsonIgnoreProperties({"student"})
	@OneToOne(mappedBy="student")
	private PfeFile pfeFile;
	
	//Default Constructor
	
	public Student() {
		super();
	}

	//Getters & Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Class getClasse() {
		return classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}

	public Convention getConvention() {
		return convention;
	}

	public void setConvention(Convention convention) {
		this.convention = convention;
	}


	public Set<StudentNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<StudentNotification> notifications) {
		this.notifications = notifications;
	}

	public PfeFile getPfeFile() {
		return pfeFile;
	}

	public void setPfeFile(PfeFile pfeFile) {
		this.pfeFile = pfeFile;
	}
	
}
