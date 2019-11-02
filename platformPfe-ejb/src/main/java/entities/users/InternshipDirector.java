package entities.users;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.administration.Site;

@Entity
@DiscriminatorValue(value="int-dir")
public class InternshipDirector extends Employe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Attributes
	@JsonIgnoreProperties("internshipDirector")
	@OneToOne(mappedBy = "internshipDirector")
	private Site site;
	
	//Getters & Setters
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}

}
