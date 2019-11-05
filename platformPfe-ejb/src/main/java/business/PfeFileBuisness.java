package business;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import collection.PfeStatus;
import collection.StudentStatus;
import entities.documents.Entreprise;
import entities.documents.PfeFile;
import entities.users.Student;
import entities.users.Teacher;
import entities.users.TeacherRole;
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
		p.setStatus(PfeStatus.WAITING);

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
	public boolean ToProcessStatusPfe (int id) {
		System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+id);

		TypedQuery<PfeFile> q = em.createQuery("Select e from PfeFile e where e.id=:idw", PfeFile.class);
		q.setParameter("idw", id);
		PfeFile a = new PfeFile(); 
			a=	q.getSingleResult();
			System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhkkkkkk"+a.getEmailPersonel());
			System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhkkkkkk"+a.getEmailProfessionel());

	
		try {
			
				
				a.setStatus(PfeStatus.PROCESSING);
			     em.merge(a);
			     SendMail s = new SendMail() ;
			     s.envoi(a.getEmailPersonel(), "Pfe File revision status ", "Your Pfe File is now under process.");
			     s.envoi(a.getEmailProfessionel(),  "Pfe File revision status ", "Your Pfe File is now under process.");

				
			
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}	

		
		

		
	}
	@Override
	public boolean ToValidStatusPfe (int id) {
		System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+id);

		TypedQuery<PfeFile> q = em.createQuery("Select e from PfeFile e where e.id=:idw", PfeFile.class);
		q.setParameter("idw", id);
		PfeFile a = new PfeFile(); 
			a=	q.getSingleResult();
			System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhkkkkkk"+a.getEmailPersonel());
			System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhkkkkkk"+a.getEmailProfessionel());

		 
		try {
			
				
				a.setStatus(PfeStatus.VALID);
			     em.merge(a);
			     SendMail s = new SendMail() ;
			     s.envoi(a.getEmailPersonel(), "Pfe File revision status", "Your Pfe File is now valid. \n good luck on your project");
			     s.envoi(a.getEmailProfessionel(), "Pfe File revision status", "Your Pfe File is now valid. \n good luck on your project");

				
			
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}	

	

		
	}
	@Override
	public boolean ToRefusedStatusPfe (int id , String cause) {
		System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+id);

		TypedQuery<PfeFile> q = em.createQuery("Select e from PfeFile e where e.id=:idw", PfeFile.class);
		q.setParameter("idw", id);
		PfeFile a = new PfeFile(); 
			a=	q.getSingleResult();
			System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhkkkkkk"+a.getEmailPersonel());
			System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhkkkkkk"+a.getEmailProfessionel());

		 
		try {
			
				
				a.setStatus(PfeStatus.REFUSED);
			     em.merge(a);
			     SendMail s = new SendMail() ;
			     s.envoi(a.getEmailPersonel(), "Pfe File revision status", "Your Pfe File refused. \n " + cause);
			     s.envoi(a.getEmailProfessionel(), "Pfe File revision status", "Your Pfe File  refused. \n "+ cause );

				
			
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}	



		
	}

	@Override
	public Teacher  supervisorName (int id ) {
		// TODO Auto-generated method stub
	PfeFile p = findPfeById(id);
		Query  q = em.createQuery("Select r from PfeFile e "
				+ ",Thesis m, TeacherRole c , Teacher r where e.id=:idw and e.id=m.pfeFile.id "
				+ "and m.id=c.thesis.id and c.role='SUPERVISOR'"
				+ " and r.id=c.teacher.id", Teacher.class);
		q.setParameter("idw", id);
	return (Teacher) q.getSingleResult();
	}
}
