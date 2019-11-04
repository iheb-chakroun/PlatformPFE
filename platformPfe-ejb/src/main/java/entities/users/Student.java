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
	
	
	
	
	public Student(int id, String firstName, String lastName, Date birthDate, String email, String sexe, String tel,
			String password, boolean status, Class classe, Convention convention,
			List<StudentNotification> notifications, PfeFile pfeFile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.sexe = sexe;
		this.tel = tel;
		this.password = password;
		this.status = status;
		this.classe = classe;
		this.convention = convention;
		this.notifications = notifications;
		this.pfeFile = pfeFile;
	}
	
	
	
	
	public Student() {
		super();
	}




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
	public List<StudentNotification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<StudentNotification> notifications) {
		this.notifications = notifications;
	}
	public PfeFile getPfeFile() {
		return pfeFile;
	}
	public void setPfeFile(PfeFile pfeFile) {
		this.pfeFile = pfeFile;
	}
	
	
	

}
