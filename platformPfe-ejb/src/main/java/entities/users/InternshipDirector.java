package entities.users;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import entities.administration.Site;

@Entity
@DiscriminatorValue(value="int-dir")
public class InternshipDirector extends Employe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Attributes
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
