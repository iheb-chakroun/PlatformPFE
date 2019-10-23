package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.documents.Entreprise;
import interfaces.EntrepriseLocal;

@Stateless
public class EntrepriseBuisness implements EntrepriseLocal {
	@PersistenceContext(unitName = "platform")
	EntityManager em;

	public Entreprise findEntrepriseById(int id) {

		Entreprise a = em.find(Entreprise.class, id);

		return a;
	}

	@Override
	public boolean deleteEntrepriseById(int EntrepriseId) {
		// TODO Auto-generated method stub
		Entreprise emp = em.find(Entreprise.class, EntrepriseId);

		try {
			em.remove(emp);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void ajouterEntreprise(Entreprise E) {
		// TODO Auto-generated method stub
		em.persist(E);
	}

	@Override
	public List<Entreprise> getAllEntreprise() {
		// TODO Auto-generated method stub
		List<Entreprise> entreprises = em.createQuery("select e from Entreprise e", Entreprise.class).getResultList();
		return entreprises;
	}

	@Override
	public boolean updateEntreprise(Entreprise E) {
		// TODO Auto-generated method stub
		Entreprise E1 = em.find(Entreprise.class, E.getId());

		E1.setAddress(E.getAddress());
		E1.setCountry(E.getCountry());
		E.setEmailEncadrent(E.getEmailEncadrent());
		E.setEmailResponsable(E.getEmailResponsable());
		E.setNameResponsable(E.getNameResponsable());
		E.setPfeFile(E.getPfeFile());
		E.setWebsite(E.getWebsite());


		try {
			em.merge(E1);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}
