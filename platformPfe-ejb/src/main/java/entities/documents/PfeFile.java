package entities.documents;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import entities.tracking.ArchivePfeFile;
import entities.users.Student;
import entities.users.Teacher;

@Entity
public class PfeFile {
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
	@OneToOne
	private Student student;
	@OneToMany(mappedBy="pfeFile")
	private List<ArchivePfeFile> archivePfeFile;
	@OneToOne
	private Entreprise entreprise;
	@OneToOne(mappedBy="pfeFile")
	private Thesis thesis;
	@ManyToOne
	private Teacher pre_validator;
	@ManyToMany
	private List<Categorie> categories;
}
