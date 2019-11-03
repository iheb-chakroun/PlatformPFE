package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.administration.Classroom;
import entities.administration.Departement;
import entities.administration.Option;
import entities.administration.Site;
import entities.users.DepartementHead;
import entities.users.Teacher;
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

	@Override
	public List<Option> getOptionList(int idDep) {
		TypedQuery<Option> q = em.createQuery("select s from Option s where s.departement.id =:id", Option.class);
		q.setParameter("id", idDep);
		return q.getResultList();
	}

	@Override
	public List<Teacher> getTeacherList(int idDep) {
		TypedQuery<Teacher> q = em.createQuery("select s from Teacher s where s.departement.id =:id", Teacher.class);
		q.setParameter("id", idDep);
		return q.getResultList();
	}

	@Override
	public List<Classroom> getClassroomList(int idDep) {
		TypedQuery<Classroom> q = em.createQuery("select s from Classroom s where s.departement.id =:id", Classroom.class);
		q.setParameter("id", idDep);
		return q.getResultList();
	}

	@Override
	public boolean affecterSite(int idSite, int idDep) {
		try {
			Site site = em.find(Site.class, idSite);
			Departement dep = em.find(Departement.class, idDep);
			dep.setSite(site);
			em.merge(dep);
			return true;
		} catch  (Exception e) {
			return false;
		}
	}

	@Override
	public boolean affecterDepartementHead(int idDepartementHead, int idDep) {
		try {
			DepartementHead departementHead = em.find(DepartementHead.class, idDepartementHead);
			Departement dep = em.find(Departement.class, idDep);
			dep.setDepartementHead(departementHead);
			em.merge(dep);
			return true;
		} catch  (Exception e) {
			return false;
		}
	}

}
