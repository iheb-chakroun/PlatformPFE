package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.documents.PfeFile;
import entities.users.DepartementHead;
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
	public List<PfeFile> getAllPfeFile() {
		// TODO Auto-generated method stub
		List<PfeFile> pfes = em.createQuery("select e from PfeFile e", PfeFile.class).getResultList();
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

	@Override
	public List<PfeFile> filterPfe(String year) {
		// TODO Auto-generated method stub
		TypedQuery<PfeFile> q = em.createQuery("select e from PfeFile e where e.student.classe.scholarYear=:year order by e.status DESC", PfeFile.class);
		q.setParameter("year", year);
		//System.out.println(q.getResultList());
	    return q.getResultList();
	}

	@Override
	public List<PfeFile> filterPfeNonTreated() {
		// TODO Auto-generated method stub
		TypedQuery<PfeFile> q = em.createQuery("select e from PfeFile e where e.student.classe.scholarYear=1920 and e.status = 0 order by e.createdAt ASC", PfeFile.class);
		//System.out.println(q.getResultList());
	    return q.getResultList();
	}

	@Override
	public DepartementHead accepterPfe(int id) {
		// TODO Auto-generated method stub
		PfeFile pfe = em.find(PfeFile.class, id);
		DepartementHead dep = em.find(DepartementHead.class,17);
		pfe.setStatus(true);
		pfe.setModerator(dep);
		return dep;
	}

	
	
	
}
