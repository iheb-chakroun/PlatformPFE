package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import collection.Role;
import entities.documents.PfeFile;
import entities.documents.Thesis;
import entities.users.Teacher;
import entities.users.TeacherRole;
import interfaces.ThesisLocal;
@Stateless
public class ThesisBusiness implements ThesisLocal  {
	@PersistenceContext(unitName = "platform")
	EntityManager em;
	
	
	
	@Override
	public Thesis findThesisById(int id) {

		Thesis a = em.find(Thesis.class, id);

		return a;
	}

	@Override
	public boolean deleteThesisById(int ThesisId) {
		// TODO Auto-generated method stub
		Thesis emp = em.find(Thesis.class, ThesisId);

		try {
			em.remove(emp);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void ajouterThesis(Thesis p) {
		// TODO Auto-generated method stub
		em.persist(p);
	}

	@Override
	public List<Thesis> getAllThesis() {
		// TODO Auto-generated method stub
		List<Thesis> thesis = em.createQuery("select e from Thesis e", Thesis.class).getResultList();
		return thesis;
	}

	@Override
	public boolean updateThesis(Thesis p) {
		// TODO Auto-generated method stub


		try {
			em.merge(p);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean AjouterTeacher(int idThesis , int idTeacher , Role r ) {
		// TODO Auto-generated method stub
		Thesis emp = em.find(Thesis.class, idThesis);
		System.out.println(emp);
		Teacher teacher = em.find(Teacher.class, idTeacher);
		System.out.println(teacher);

TeacherRole teacherR = new TeacherRole();
teacherR.setRole(r);
teacherR.setTeacher(teacher);
teacherR.setRole(r);
teacherR.setThesis(emp);


try {
			em.persist(teacherR);

			
			if(r == Role.SUPERVISOR) {
				
				SendMail s = new SendMail() ;
			     s.envoi(emp.getPfeFile().getEmailProfessionel(), "Supervisor allocation", "We have named Mr/Mrs "+teacher.getLastName() +"  "+teacher.getFirstName()+" as your Supervisor,\n good luck" );
			     s.envoi(emp.getPfeFile().getEmailPersonel(), "Supervisor allocation", "We have named Mr/Mrs "+teacher.getLastName() +"  "+teacher.getFirstName()+" as your Supervisor,\n good luck" );

			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}
