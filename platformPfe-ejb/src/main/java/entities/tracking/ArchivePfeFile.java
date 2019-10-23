package entities.tracking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import entities.documents.PfeFile;

@Entity
public class ArchivePfeFile {
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
	private String event;
	private String emmeteur;
	private String note;
	@ManyToOne
	private PfeFile pfeFile;
	public ArchivePfeFile(int id, String title, String description, String problematic, String functionnalities,
			String keywords, float gradeSupervisor, float gradeReporter, String emailPersonel, String emailProfessionel,
			boolean status, boolean reportDeposite, String event, String emmeteur, String note, PfeFile pfeFile) {
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
		this.event = event;
		this.emmeteur = emmeteur;
		this.note = note;
		this.pfeFile = pfeFile;
	}
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
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEmmeteur() {
		return emmeteur;
	}
	public void setEmmeteur(String emmeteur) {
		this.emmeteur = emmeteur;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public PfeFile getPfeFile() {
		return pfeFile;
	}
	public void setPfeFile(PfeFile pfeFile) {
		this.pfeFile = pfeFile;
	}
	public ArchivePfeFile() {
		super();
	}
	
}
