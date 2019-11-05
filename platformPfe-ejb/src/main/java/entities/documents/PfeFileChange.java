package entities.documents;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import collection.PfeChangePosition;
import collection.PfeChangeStatus;

@Entity
public class PfeFileChange {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	@ManyToOne(fetch= FetchType.EAGER)
	@JsonIgnoreProperties({"changes"})

	private PfeFile pfeFile;
	@Enumerated(EnumType.STRING)
	private PfeChangePosition field ;
	private String Content ;
	@Enumerated(EnumType.STRING)
	private PfeChangeStatus status ;

	public PfeChangePosition getField() {
		return field;
	}

	public void setField(PfeChangePosition field) {
		this.field = field;
	}

	public PfeChangeStatus getStatus() {
		return status;
	}

	public void setStatus(PfeChangeStatus status) {
		this.status = status;
	}

	public PfeFileChange(int id,  PfeFile pfeFile, PfeChangePosition field, String content,
			PfeChangeStatus status) {
		super();
		this.id = id;
		this.pfeFile = pfeFile;
		this.field = field;
		Content = content;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public PfeFile getPfeFile() {
		return pfeFile;
	}

	public void setPfeFile(PfeFile pfeFile) {
		this.pfeFile = pfeFile;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}



	public PfeFileChange(int id, PfeFile pfeFile, PfeChangePosition field, String content) {
		super();
		this.id = id;
		this.pfeFile = pfeFile;
		this.field = field;
		Content = content;
	}

	public PfeFileChange() {
		super();
	}

	@Override
	public String toString() {
		return "PfeFileChange [id=" + id + ",  pfeFile=" + pfeFile + ", Content=" + Content + "]";
	}
	
	
	
	
}
