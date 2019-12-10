package entities.users;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "super-admin")
public class SuperAdmin extends Employe implements Serializable {

	private static final long serialVersionUID = 1L;

}
