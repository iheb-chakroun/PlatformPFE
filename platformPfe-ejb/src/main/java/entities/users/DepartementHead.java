package entities.users;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import entities.administration.Departement;

@Entity
@DiscriminatorValue(value="dep-head")
public class DepartementHead extends Employe implements Serializable {
	@OneToOne(mappedBy="departementHead")
	private Departement departement;
}
