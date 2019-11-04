package entities.documents;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import collection.Status;
import entities.tracking.ArchivePfeFile;
import entities.users.DepartementHead;
import entities.users.Student;
import entities.users.Teacher;

@Entity
public class PfeFile implements Serializable{
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private String problematic;
	private String functionnalities;
	private String keywords;
	private float gradeSupervisor;
	private float gradeReporter;
	private String emailPersonel;
	private String emailProfessionel;
	private boolean status;
	@Enumerated(EnumType.STRING)
	private Status annulation;
	@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss")
	private Date createdAt;
	private boolean reportDeposite;
	@JsonIgnoreProperties({"pfeFile","password","status","convention","email","birthDate","tel"})
	@OneToOne
	private Student student;
	@JsonIgnoreProperties({"pfeFile","title","problematic","description","functionnalities","event","emmeteur","keywords","gradeSupervisor","gradeReporter","emailPersonel","emailProfessionel","status","reportDeposite"})
	@OneToMany(mappedBy="pfeFile", fetch=FetchType.EAGER)
	private List<ArchivePfeFile> archivePfeFile;
	@OneToOne
	@JsonIgnoreProperties({"pfeFile","nameResponsable","emailResponsable","emailEncadrent"})
	private Entreprise entreprise;
	@JsonIgnoreProperties({"pfeFile","teacherRole"})
	@OneToOne(mappedBy="pfeFile")
	private Thesis thesis;
	@JsonIgnoreProperties({"departement","password","username","address","email","teacherRole","pfeFiles","categories","teacherNotification"})
	@ManyToOne
	private Teacher pre_validator;
	@JsonIgnoreProperties({"id","status","pfefile"})
	@OneToOne
	private Categorie categorie;
	@JsonIgnoreProperties({"departement","password","username","address","email"})
	@ManyToOne()
	private DepartementHead moderator;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProblematic() {
		return problematic;
	}
	public void setProblematic(String problematic) {
		this.problematic = problematic;
	}
	public String getFunctionnalities() {
		return functionnalities;
	}
	public void setFunctionnalities(String functionnalities) {
		this.functionnalities = functionnalities;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public float getGradeSupervisor() {
		return gradeSupervisor;
	}
	public void setGradeSupervisor(float gradeSupervisor) {
		this.gradeSupervisor = gradeSupervisor;
	}
	public float getGradeReporter() {
		return gradeReporter;
	}
	public void setGradeReporter(float gradeReporter) {
		this.gradeReporter = gradeReporter;
	}
	public String getEmailPersonel() {
		return emailPersonel;
	}
	public void setEmailPersonel(String emailPersonel) {
		this.emailPersonel = emailPersonel;
	}
	public String getEmailProfessionel() {
		return emailProfessionel;
	}
	public void setEmailProfessionel(String emailProfessionel) {
		this.emailProfessionel = emailProfessionel;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isReportDeposite() {
		return reportDeposite;
	}
	public void setReportDeposite(boolean reportDeposite) {
		this.reportDeposite = reportDeposite;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<ArchivePfeFile> getArchivePfeFile() {
		return archivePfeFile;
	}
	public void setArchivePfeFile(List<ArchivePfeFile> archivePfeFile) {
		this.archivePfeFile = archivePfeFile;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public Thesis getThesis() {
		return thesis;
	}
	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}
	public Teacher getPre_validator() {
		return pre_validator;
	}
	public void setPre_validator(Teacher pre_validator) {
		this.pre_validator = pre_validator;
	}
	public PfeFile() {
		super();
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public DepartementHead getModerator() {
		return moderator;
	}
	public void setModerator(DepartementHead moderator) {
		this.moderator = moderator;
	}
	public Status getAnnulation() {
		return annulation;
	}
	public void setAnnulation(Status annulation) {
		this.annulation = annulation;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	
	
}
