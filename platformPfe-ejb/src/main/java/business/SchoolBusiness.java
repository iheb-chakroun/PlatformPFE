package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.administration.School;
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
	

}
