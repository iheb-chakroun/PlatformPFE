package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.administration.School;
import entities.administration.Site;
import entities.users.Admin;
import interfaces.SchoolBusinessLocal;
import interfaces.SchoolBusinessRemote;


@Stateless
@LocalBean
public class SchoolBusiness implements SchoolBusinessRemote, SchoolBusinessLocal {

	@PersistenceContext
	EntityManager em;

	@Override
	public int addSchool(School school) {
		em.persist(school);
		return school.getId();
	}

	@Override
	public School getSchoolById(int id) {
		return em.find(School.class, id);
	}

	@Override
	public List<School> getAllSchool() {
		return em.createQuery("select s from School s", School.class).getResultList();
	}
	
	@Override
	public Admin getSchoolAdmin(int idSchool) {
		TypedQuery<Admin> q = em.createQuery("select a from Admin a where a.school.id =:id", Admin.class);
		q.setParameter("id", idSchool);
		return q.getSingleResult();
	}
	
	@Override
	public List<Site> getSchoolSites(int idSchool) {
		TypedQuery<Site> q = em.createQuery("select s from Site s where s.school.id =:id", Site.class);
		q.setParameter("id", idSchool);
		return q.getResultList();
	}
	

	@Override
	public void updateSchool(School school) {
		em.merge(school);
		
	}

	@Override
	public boolean deleteSchool(int id) {
		try {
			em.remove(em.find(School.class, id));	
			return true;
		} catch  (Exception e) {
			return false;
		}
			
	}

	@Override
	public boolean assignAdminToSchool(int isSchool, int idAdmin) {
		try {
			School school = em.find(School.class, isSchool);
			Admin admin = em.find(Admin.class, idAdmin);
			school.setAdmin(admin);
			em.merge(school);
			return true;
		} catch  (Exception e) {
			return false;
		}
	}
	

}
