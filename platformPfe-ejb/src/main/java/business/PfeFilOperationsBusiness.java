package business;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import collection.Role;
import entities.documents.PfeFile;
import entities.documents.Thesis;
import entities.users.Teacher;
import entities.users.TeacherRole;
import interfaces.PfeFileOperationsRemote;

@Stateless
public class PfeFilOperationsBusiness implements PfeFileOperationsRemote {

	@PersistenceContext(unitName="platform")
	EntityManager em;
	
	
	
	
	@Override
	public List<PfeFile> getAcceptedPfeFiles() {
		List<PfeFile> acceptedPfeFiles = em.createQuery("select p from PfeFile p where status = 1",PfeFile.class).getResultList();
		return acceptedPfeFiles;
	}
	
	@Override
	public List<PfeFile> getUngradedPfeFiles() {
		List<PfeFile> ungradedPfeFiles = em.createQuery("select p from PfeFile p where gradeReporter = 0 or gradeSupervisor = 0 ",PfeFile.class).getResultList();
		return ungradedPfeFiles;
	}
	
	@Override
	public boolean validatePfeCategorie(int categorieId){
		Query query = em.createQuery("update Categorie c set status=1 where c.id=:categorieId");
		query.setParameter("categorieId", categorieId);
				try {
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean affectPrevalidator(int teacherId, int pfeFileId) {
		Teacher  teacherManagedEntity = em.find(Teacher.class, teacherId);
		PfeFile pfeFileManagedEntity = em.find(PfeFile.class, pfeFileId);
		try {
			pfeFileManagedEntity.setPre_validator(teacherManagedEntity);
			em.merge(pfeFileManagedEntity);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean affectTeacherRole(int teacherId, int pfeFileId, int role) {
		
		Role r;
		
		if (role == 1 ) {
			r = Role.RAPPORTEUR;
		}
		
		else if (role == 2 ) {
			r = Role.PRESIDENT;
		}
		else {
			r = Role.SUPERVISOR;
		}
		
		
		try {
			Teacher  teacherManagedEntity = em.find(Teacher.class, teacherId);
			PfeFile pfeFileManagedEntity = em.find(PfeFile.class, pfeFileId);
			TeacherRole teacherRoleManagedEntity = new TeacherRole();
			Thesis thesis = pfeFileManagedEntity.getThesis();
			teacherRoleManagedEntity.setRole(r);
			teacherRoleManagedEntity.setTeacher(teacherManagedEntity);
			teacherRoleManagedEntity.setThesis(thesis);
			em.persist(teacherRoleManagedEntity);
			return true;	
		} catch (Exception e) {
			return false;
		}
		
	}
	
	
	
}
