package entities.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import collection.StudentStatus;
import entities.administration.Class;
import entities.documents.Convention;
import entities.documents.PfeFile;
import entities.tracking.StudentNotification;

@Entity
public class Student implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern="yyyy-MM-dd")

	private Date birthDate;
	private String email;
	private String sexe;
	private String tel;
	//note dedeecefcefc
	private String password;
	@Enumerated(EnumType.STRING)
	private StudentStatus status;
	@ManyToOne
	private Class classe;
	@OneToOne(mappedBy="student")
	private Convention convention;
	@OneToMany(mappedBy="student")
	//private Set<StudentNotification> notifications;
	private List<StudentNotification> notifications= new ArrayList<StudentNotification>();
	@OneToOne(mappedBy="student")
	private PfeFile pfeFile;
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
	public StudentStatus getStatus() {
		return status;
	}
	public void setStatus(StudentStatus status) {
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
//	public Set<StudentNotification> getNotifications() {
//		return notifications;
//	}
//	public void setNotifications(Set<StudentNotification> notifications) {
//		this.notifications = notifications;
//	}
	
	public PfeFile getPfeFile() {
		return pfeFile;
	}
	public List<StudentNotification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<StudentNotification> notifications) {
		this.notifications = notifications;
	}
	public void setPfeFile(PfeFile pfeFile) {
		this.pfeFile = pfeFile;
	}
	public Student(int id, String firstName, String lastName, Date birthDate, String email, String sexe, String tel,
			String password, Class classe) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.sexe = sexe;
		this.tel = tel;
		this.password = password;
		this.status = StudentStatus.NONAUTHORIZED;
		this.classe = classe;
	}
	public Student() {
		super();
	}

}
