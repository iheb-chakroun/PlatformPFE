package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import collection.Role;
import entities.administration.ChangeSupervisorRequest;
import entities.documents.Thesis;
import entities.users.Student;
import entities.users.Teacher;
import entities.users.TeacherRole;
import interfaces.ChangeSupervisorRequestRemote;

@Stateless
public class ChangeSupervisorRequestBusiness implements ChangeSupervisorRequestRemote {

	@PersistenceContext(unitName="platform")
	EntityManager em;
	
	@Override
	public List<Teacher> getAllActiveForSupervisingTeachers(){
		List<Teacher> teachers = em.createQuery("select t from Teacher t", Teacher.class).getResultList();
		return teachers;
	}
	
	@Override
	public boolean requestSupervisorchange(int id, ChangeSupervisorRequest c){
		ChangeSupervisorRequest changeSupervisorRequest = new ChangeSupervisorRequest();
		
		
		
		
		try {
			changeSupervisorRequest.setDescription(c.getDescription());
			Student student = em.find(Student.class, id);
			changeSupervisorRequest.setStudent(student);
			em.persist(changeSupervisorRequest);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean acceptSupervisorchange(int id){
		ChangeSupervisorRequest c = em.find(ChangeSupervisorRequest.class, id);
		c.setStatus(true);
				try {
					em.merge(c);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean refuseSupervisorchange(int id){
		ChangeSupervisorRequest c = em.find(ChangeSupervisorRequest.class, id);
		c.setStatus(false);
				try {
					em.merge(c);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean assignSupervisorchange(int studentId,int techerId) {
		
		try {
			Student s = em.find(Student.class, studentId);
			Teacher t = em.find(Teacher.class, techerId);
			Thesis thesis = s.getPfeFile().getThesis();
			
			Role r = Role.SUPERVISOR;
			TypedQuery<TeacherRole> query = em.createQuery("select t from TeacherRole t where thesis=:thesis and role=:role", TeacherRole.class);
			query.setParameter("thesis", thesis);
			query.setParameter("role", r);
			TeacherRole tr = query.getSingleResult();
			tr.setTeacher(t);
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
