package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.documents.PfeFile;
import interfaces.PfeFileLocal;

@Stateless
public class PfeFileBuisness implements PfeFileLocal {

	@PersistenceContext(unitName = "platform")
	EntityManager em;

	@EJB
	ThesisBuisness tb;

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
		if (p.isReady()) {
			// XXX
			tb.plan(p);
		}
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
			if (p.isReady()) {
				// XXX
				tb.plan(p);
			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<PfeFile> getListPfeFileByManyIds(String ids) {
		List<PfeFile> pfes = new ArrayList<PfeFile>();
		String[] tabId = ids.split(";");
		for (String sId : tabId) {
			int iId = Integer.parseInt(sId);
			pfes.add(this.findPfeById(iId));
		}
		return pfes;
	}

}
