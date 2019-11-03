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

@Stateless
public class StudentBusiness implements StudentRemote{

	@PersistenceContext(unitName="platform")
	EntityManager em;
	
	EmailBusiness eb = new EmailBusiness();
	
	@Override
	public int addStudent(Student student) {
		// TODO Auto-generated method stub
		System.out.println("Starting input:");
		em.persist(student);
		System.out.println("Finished");
		return student.getId();
	}

	@Override
	public void removeStudent(int id) {
		// TODO Auto-generated method stub
		//Find managed Entity reference
        Student st = em.find(Student.class, id );
        //Call remove method to remove the entity
        if(st != null){
            em.remove(st);
        }
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("update Student u set u.email=:email, u.firstName=:fname, u.lastName=:lname, u.password=:password, u.sexe=:sexe, u.status=:status, u.tel=:tel where u.id=:id");
		query.setParameter("id", student.getId());
		query.setParameter("email", student.getEmail());
		query.setParameter("fname", student.getFirstName());
		query.setParameter("lname", student.getLastName());
		query.setParameter("password", student.getPassword());
		query.setParameter("sexe", student.getSexe());
		query.setParameter("status", student.isStatus());
		query.setParameter("tel", student.getTel());
		query.executeUpdate();
		
	}

	@Override
	public Student findStudentById(int id) {
		System.out.println("In findStudentById : "); 
		Student st = em.find(Student.class, id); 
		System.out.println("Out of findStudentById : "); 
		return st; 
	}

	@Override
	public List<Student> findAllStudents() {
		// TODO Auto-generated method stub
		System.out.println("In findAllStudents : "); 
		List<Student> students =  em.createQuery("from Student", Student.class).getResultList();
		System.out.println("Out of findAlltudents : "); 
		return students; 
	}

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
		em.persist(archive);
		return pfe.getAnnulation();
	}
	
	//Requirement 6
	//@Author: khaled
	@Override
	public void declineAnnulation(Reason reason) {
		// TODO Auto-generated method stub
		PfeFile pfe = em.find(PfeFile.class, reason.getId());
		pfe.setAnnulation(Status.Declined);
		TemplateMessage template = new TemplateMessage(reason.getMessage());
		eb.sendEmail(pfe.getStudent().getEmail(), "PFE annulation denied.", template.getTemplate());
	}	 

}
