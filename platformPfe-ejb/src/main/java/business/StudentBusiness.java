package business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import collection.StudentStatus;
import entities.documents.PfeFile;
import entities.users.Student;
import interfaces.StudentLocal;

@Stateless
public class StudentBusiness implements StudentLocal {
	@PersistenceContext(unitName = "platform")
	EntityManager em;
	@Override
	public boolean activateAccount (int id) {
		System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+id);

		TypedQuery<Student> q = em.createQuery("Select e from Student e where e.id=:idw", Student.class);
		q.setParameter("idw", id);
		Student a = new Student(); 
			a=	q.getSingleResult();
			System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhkkkkkk"+a.getEmail());
		if(a.getStatus()== StudentStatus.AUTHORIZED) {
		try {
			
				
				 CharacterRule alphabets = new CharacterRule(EnglishCharacterData.Alphabetical);
			     CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);

			     PasswordGenerator passwordGenerator = new PasswordGenerator();
			     String password = passwordGenerator.generatePassword(8, alphabets, digits);
			     System.out.println(password);
			     a.setPassword(password);
			     em.merge(a);
			     SendMail s = new SendMail() ;
			     s.envoi(a.getEmail(), "Activation account", "your PFE account has been activated \n your email : "+ a.getEmail()+
			    		 " \n your password is : " + a.getPassword());
			     
				
			
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}	

		}
		
		return false;

		
	}
	@Override
	public boolean authorizeStudent(int id) {
		try {
			System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+id);
			
			TypedQuery<Student> q = em.createQuery("select e from Student e where e.id=:idw", Student.class);
			q.setParameter("idw", id);
			Student a = new Student(); 
				a=q.getSingleResult();
				System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkhhhhhhhhhhhhhhkkkkkk"+a.getEmail());
			System.out.print(a.getEmail());

			a.setStatus(StudentStatus.AUTHORIZED);
			System.out.print(a.getStatus());

			em.merge(a);
						return true;

		} catch (Exception e) {
			return false;
		}
		
		
	}
}
