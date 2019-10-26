package entities.documents;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import entities.tracking.ArchivePfeFile;
import entities.users.Student;
import entities.users.Teacher;

@Entity
public class PfeFile implements Serializable{
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
	private boolean reportDeposite;
	@JsonIgnoreProperties({"pfeFile"})
	@OneToOne
	private Student student;
	@OneToOne
	@JsonIgnoreProperties({"pfeFile"})
	private Thesis Thesis;
	@OneToMany(mappedBy="pfeFile", fetch=FetchType.EAGER)
	private List<ArchivePfeFile> archivePfeFile;
	@OneToOne
	@JsonIgnoreProperties({"pfeFile"})
	private Entreprise entreprise;
	@OneToOne(mappedBy="pfeFile")
	private Thesis thesis;
	@ManyToOne
	private Teacher pre_validator;
	@ManyToMany
	private List<Categorie> categories;

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
	public List<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
	public PfeFile(int id, String title, String description, String problematic, String functionnalities,
			String keywords, float gradeSupervisor, float gradeReporter, String emailPersonel, String emailProfessionel,
			boolean status, boolean reportDeposite, Student student, List<ArchivePfeFile> archivePfeFile,
			Entreprise entreprise, Thesis thesis, Teacher pre_validator, List<Categorie> categories) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.problematic = problematic;
		this.functionnalities = functionnalities;
		this.keywords = keywords;
		this.gradeSupervisor = gradeSupervisor;
		this.gradeReporter = gradeReporter;
		this.emailPersonel = emailPersonel;
		this.emailProfessionel = emailProfessionel;
		this.status = status;
		this.reportDeposite = reportDeposite;
		this.student = student;
		this.archivePfeFile = archivePfeFile;
		this.entreprise = entreprise;
		this.thesis = thesis;
		this.pre_validator = pre_validator;
		this.categories = categories;
	}
	public PfeFile() {
		super();
	}
	
	
	
	
}
