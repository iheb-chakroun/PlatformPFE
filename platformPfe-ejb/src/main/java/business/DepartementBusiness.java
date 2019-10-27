package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.administration.Departement;
import interfaces.DepartementLocal;
import interfaces.DepartementRemote;

/**
 * Session Bean implementation class DepartementBusiness
 */
@Stateless
@LocalBean
public class DepartementBusiness implements DepartementRemote, DepartementLocal {

	@PersistenceContext
	EntityManager em;

	@Override
	public int addDepartement(Departement departement) {
		em.persist(departement);
		return departement.getId();
	}

	@Override
	public Departement getDepartementById(int id) {
		return em.find(Departement.class, id);
	}

	@Override
	public List<Departement> getAllDepartement() {
		return em.createQuery("select s from Departement s", Departement.class).getResultList();
	}

	@Override
	public void updateDepartement(Departement departement) {
		em.merge(departement);

	}

	@Override
	public boolean deleteDepartement(int id) {
		try {
			em.remove(em.find(Departement.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
