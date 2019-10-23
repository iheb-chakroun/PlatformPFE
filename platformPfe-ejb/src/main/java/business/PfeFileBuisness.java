package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.documents.Entreprise;
import entities.documents.PfeFile;
import interfaces.PfeFileLocal;

@Stateless
public class PfeFileBuisness implements PfeFileLocal {

	
	@PersistenceContext(unitName = "platform")
	EntityManager em;

	public PfeFile findEntrepriseById(int id) {

		PfeFile a = em.find(PfeFile.class, id);

		return a;
	}

	@Override
	public boolean deletePfeFileById(int PfeFileId) {
		// TODO Auto-generated method stub
		PfeFile emp = em.find(PfeFile.class, PfeFileId);

		try {
			em.remove(emp);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void ajouterPfeFile(PfeFile p) {
		// TODO Auto-generated method stub
		em.persist(p);
	}

	@Override
	public List<Entreprise> getAllPfeFile() {
		// TODO Auto-generated method stub
		List<Entreprise> pfes = em.createQuery("select e from PfeFile e", Entreprise.class).getResultList();
		return pfes;
	}

	@Override
	public boolean updatePfeFile(PfeFile p) {
		// TODO Auto-generated method stub


		try {
			em.merge(p);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	
	
	
}
