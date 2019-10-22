package entities.documents;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Entreprise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String website;
	private String address;
	private String country;
	private String nameResponsable;
	private String emailResponsable;
	private String emailEncadrent;
	@OneToOne(mappedBy="entreprise")
	private PfeFile pfeFile;

}
