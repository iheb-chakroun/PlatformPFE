package entities.users;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import entities.administration.Site;

@Entity
@DiscriminatorValue(value="int-dir")
public class InternshipDirector extends Employe implements Serializable {
	@OneToOne(mappedBy = "internshipDirector")
	private Site site;

}
