package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.documents.PfeFile;
import interfaces.PfeFileLocal;

@Stateless
public class PfeFileBuisness implements PfeFileLocal {

	@PersistenceContext(unitName = "platform")
	EntityManager em;

	@Override
	public PfeFile findPfeById(int id) {

		PfeFile a = em.find(PfeFile.class, id);

		return a;
	}

	@Override
	public boolean deletePfeFileById(int PfeFileId) {
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
		em.persist(p);
	}

	@Override
	public List<PfeFile> getAllPfeFile() {
		List<PfeFile> pfes = em.createQuery("select e from PfeFile e", PfeFile.class).getResultList();
		return pfes;
	}

	@Override
	public boolean updatePfeFile(PfeFile p) {

		try {
			em.merge(p);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	// Iheb

	@Override
	public List<PfeFile> getAllReadyPfeFile() {
		List<PfeFile> pfes = this.getAllPfeFile();
		List<PfeFile> pfeReady = new ArrayList<PfeFile>();
		for (PfeFile pfeFile : pfes) {
			if (pfeFile.isReady()) {
				pfeReady.add(pfeFile);
			}
		}
		return pfeReady;
	}

}
