package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.documents.PfeFile;
import entities.users.DepartementHead;
import entities.users.Teacher;
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
import utilities.EmailBusiness;
import utilities.EmailTemplate;

@Stateless
public class PfeFileBuisness implements PfeFileLocal {

	
	@PersistenceContext(unitName = "platform")
	EntityManager em;
	
	EmailBusiness eb = new EmailBusiness();
	
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
	
	//Requirement 2
	//@Author: khaled
	@Override
	public List<PfeFile> filterPfe(String... options) {
		// TODO Auto-generated method stub
		List<String> filters = new ArrayList<String>(); ;
		for(String item : options) {
			if(item.equals("status")) {filters.add("e.status");}
			if(item.equals("year")) {filters.add("e.student.classe.scholarYear");}
			if(item.equals("country")) {filters.add("e.entreprise.country");}
			if(item.equals("category")) {filters.add("e.categorie.label");}
		}
		String values = filters.stream().collect(Collectors.joining(", "));
		TypedQuery<PfeFile> q = em.createQuery("select e from PfeFile e order by "+values, PfeFile.class);
	    return q.getResultList();
	}
	//Requirement 3
	//@Author: khaled
	@Override
	public List<PfeFile> filterPfeNonTreated() {
		// TODO Auto-generated method stub
		int value = Calendar.getInstance().get(Calendar.YEAR);
		String year = Integer.toString(value);
		TypedQuery<PfeFile> q = em.createQuery("select e from PfeFile e where e.student.classe.scholarYear=:year and e.status = 0 order by e.createdAt ASC", PfeFile.class);
		q.setParameter("year", year);
		//System.out.println(q.getResultList());
	    return q.getResultList();
	}

	//Requirement 4
	//@Author: khaled
	@Override
	public DepartementHead accepterPfe(int id) {
		// TODO Auto-generated method stub
		PfeFile pfe = em.find(PfeFile.class, id);
		DepartementHead dep = em.find(DepartementHead.class,pfe.getStudent().getClasse().getOption().getDepartement().getDepartementHead().getId());
		pfe.setStatus(true);
		pfe.setModerator(dep);
		return dep;
	}
	//Requirement 5
	//@Author: khaled
	@Override
	public void refusePfe(int id, String reason) {
		// TODO Auto-generated method stub
		PfeFile pfe = em.find(PfeFile.class, id);
		pfe.setStatus(false);
		eb.sendEmail(pfe.getStudent().getEmail(), reason, EmailTemplate.template);
	}
	
	//Requirement 10
	//@Author: khaled
	@Override
	public List<PfeFile> getPedingFilesPlanification() {
		// TODO Auto-generated method stub
		TypedQuery<PfeFile> q = em.createQuery("select e from PfeFile e where e.gradeReporter > 0 and e.gradeSupervisor > 0 order by e.createdAt ASC", PfeFile.class);
		return q.getResultList();
	}

	//Requirement 9
	//@Author: khaled
	@Override
	public void approveReport(int id) {
		// TODO Auto-generated method stub
		PfeFile pfe = em.find(PfeFile.class, id);
		pfe.setReportDeposite(!pfe.isReportDeposite());	
	}
	//Requirement 9
	//@Author: khaled// need changes pfefile class
	@Override
	public boolean asignReporter(int id_pfe, int id_rep) {
		// TODO Auto-generated method stub
		PfeFile pfe = em.find(PfeFile.class, id_pfe);
		Teacher teacher = em.find(Teacher.class, id_rep);
		if(pfe.isReportDeposite()) {
			pfe.setPre_validator(teacher);
			return true;
		}
		return false;
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
