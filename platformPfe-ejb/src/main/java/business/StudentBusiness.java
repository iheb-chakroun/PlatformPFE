package business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import collection.Status;
import entities.documents.PfeFile;
import entities.tracking.ArchivePfeFile;
import entities.tracking.StudentNotification;
import entities.users.Student;
import interfaces.StudentRemote;
import utilities.EmailBusiness;
import utilities.Reason;
import utilities.TemplateMessage;

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
	
	EmailBusiness eb = new EmailBusiness();
	
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

	@Override
	public List<StudentNotification> findAllNotifications() {
		// TODO Auto-generated method stub
		System.out.println("In findAllStudents : "); 
		List<StudentNotification> notifs =  em.createQuery("from StudentNotification", StudentNotification.class).getResultList();
		System.out.println("Out of findAlltudents : "); 
		return notifs;
	}
	//Requirement 1
	//@Author: khaled
	@Override
	public List<Student> findStudentsByYears(String... years) {
		// TODO Auto-generated method stub
		String values = Arrays.asList(years).stream().filter(y -> y != null).collect(Collectors.joining(", "));
		String query = "select e from Student e where e.classe.scholarYear IN ("+values+")";
		TypedQuery<Student> q = em.createQuery(query, Student.class);
			return q.getResultList().stream().filter(line -> line.getPfeFile()==null).collect(Collectors.toList());
	}
	
	//Requirement 7
	//@Author: khaled
	@Override
	public void authorizeStudent(int id) {
		// TODO Auto-generated method stub
		Student st = em.find(Student.class, id);
		st.setStatus(!st.isStatus());
	}

	@Override
	public Status demandeAnnulation(int id) {
		// TODO Auto-generated method stub
		PfeFile pfe = em.find(PfeFile.class, id);
		pfe.setAnnulation(Status.Pending);
		return pfe.getAnnulation();
	}
	
	//Requirement 6
	//@Author: khaled
	@Override
	public Status approveAnnulation(int id) {
		// TODO Auto-generated method stub
		PfeFile pfe = em.find(PfeFile.class, id);
		ArchivePfeFile archive = new ArchivePfeFile();
		pfe.setAnnulation(Status.Approved);
		archive.setTitle(pfe.getTitle());
		archive.setDescription(pfe.getDescription());
		archive.setEmailPersonel(pfe.getEmailPersonel());
		archive.setEmailProfessionel(pfe.getEmailProfessionel());
		archive.setGradeReporter(pfe.getGradeReporter());
		archive.setGradeSupervisor(pfe.getGradeSupervisor());
		archive.setKeywords(pfe.getKeywords());
		archive.setProblematic(pfe.getProblematic());
		archive.setStatus(pfe.isStatus());
		archive.setReportDeposite(pfe.isReportDeposite());
		archive.setFunctionnalities(pfe.getFunctionnalities());
		archive.setPfeFile(pfe);
		archive.setNote("canceled");
		em.persist(archive);
		return pfe.getAnnulation();
	}
	
	//Requirement 6
	//@Author: khaled
	@Override
	public void declineAnnulation(Reason reason) {
		// TODO Auto-generated method stub
		Student st = em.find(Student.class, reason.getId());
		st.getPfeFile().setAnnulation(Status.Declined);
		TemplateMessage template = new TemplateMessage(reason.getMessage());
		eb.sendEmail(st.getEmail(), "PFE annulation denied.", template.getTemplate());
	}	 

			a.setStatus(StudentStatus.AUTHORIZED);
			System.out.print(a.getStatus());

			em.merge(a);
						return true;

		} catch (Exception e) {
			return false;
		}
		
		
	}
}
