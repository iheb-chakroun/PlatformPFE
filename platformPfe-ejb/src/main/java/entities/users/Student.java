package entities.users;

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
	@ManyToOne
	private Class classe;
	@OneToOne(mappedBy="student")
	private Convention convention;
	@OneToMany(mappedBy="student")
	private List<StudentNotification> notifications;
	@OneToOne(mappedBy="student")
	private PfeFile pfeFile;
	
	//Default Constructor
	
	public Student() {
		super();
	}

	public Student(String firstName, String lastName, Date birthDate, String email, String sexe, String tel,
			String password, boolean status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.sexe = sexe;
		this.tel = tel;
		this.password = password;
		this.status = status;
	}
	
	
	public Student(String firstName, String lastName, String email, String sexe, String tel, String password,
			boolean status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.sexe = sexe;
		this.tel = tel;
		this.password = password;
		this.status = status;
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
	
	//Override methods like hashcode, toString, etc...
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = prime * result + ((convention == null) ? 0 : convention.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pfeFile == null) ? 0 : pfeFile.hashCode());
		result = prime * result + ((sexe == null) ? 0 : sexe.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		Student other = (Student) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (classe == null) {
			if (other.classe != null)
				return false;
		} else if (!classe.equals(other.classe))
			return false;
		if (convention == null) {
			if (other.convention != null)
				return false;
		} else if (!convention.equals(other.convention))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (notifications == null) {
			if (other.notifications != null)
				return false;
		} else if (!notifications.equals(other.notifications))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pfeFile == null) {
			if (other.pfeFile != null)
				return false;
		} else if (!pfeFile.equals(other.pfeFile))
			return false;
		if (sexe == null) {
			if (other.sexe != null)
				return false;
		} else if (!sexe.equals(other.sexe))
			return false;
		if (status != other.status)
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", email=" + email + ", sexe=" + sexe + ", tel=" + tel + ", password=" + password + ", status="
				+ status + ", classe=" + classe + ", convention=" + convention + ", notifications=" + notifications
				+ ", pfeFile=" + pfeFile + "]";
	}

}
