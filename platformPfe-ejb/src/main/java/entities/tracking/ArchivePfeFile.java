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
}
