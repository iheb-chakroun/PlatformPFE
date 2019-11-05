package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import collection.Role;
import collection.Statuspfefile;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.tracking.HeadDepartementNotification;
import entities.tracking.StudentNotification;
import entities.users.Teacher;
import entities.users.TeacherRole;
import interfaces.PfeFichierLocal;

@Stateless
public class PfeFichierBusiness implements PfeFichierLocal{

	
	@PersistenceContext(unitName ="platform")
	EntityManager em;
	//SmsSender sms=new SmsSender();
	@Override
	public List<PfeFile> getAllPfeFileyear(int id,String year,Role r) {
		List<PfeFile> pfefiles=new ArrayList<PfeFile>();;
		Teacher t = em.find(Teacher.class, id);
		for (TeacherRole tr : t.getTeacherRole()) {
			if(tr.getThesis().getPfeFile().getStudent().getClasse().getScholarYear().equals(year)) {
				if(tr.getRole().equals(r) )
				{
					pfefiles.add(tr.getThesis().getPfeFile());
				}
			  }
			}
		return pfefiles;
		
	}

	@Override
	public List<PfeFile> getAllPfeFileyears(int id,String year,Role r) {
		List<PfeFile> pfefiles = new ArrayList<PfeFile>();;
		String[] years=year.split(";");
		Teacher t = em.find(Teacher.class, id);
		for(String y:years) {
			for (TeacherRole tr : t.getTeacherRole()) {
				
				if(tr.getThesis().getPfeFile().getStudent().getClasse().getScholarYear().equals(y)) {
					if(tr.getRole().equals(r) )
					{
						pfefiles.add(tr.getThesis().getPfeFile());
					}
				  }
				}
		}
		
		return pfefiles;
	}

	@Override
	public List<PfeFile> getAllprevalidatedfiles(Statuspfefile s) {
		Query q = em.createQuery("SELECT p from PfeFile p where p.spf=:s ", PfeFile.class);
		q.setParameter("s", s);
		List<PfeFile> pfefiles = q.getResultList();
		return pfefiles;
	}

	@Override
	public boolean prevalidatepfefile(int id,Statuspfefile s,String msg)  {
		Query q = em.createQuery("update PfeFile p set p.spf=:s where p.id=:id");
		q.setParameter("s", s);
		q.setParameter("id", id);
		System.out.println(s);
		//SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Query q1 = em.createQuery("select s.tel from Student s,PfeFile p where p.id=:id  ");
		q1.setParameter("id", id);
		String n=(String) q1.getSingleResult();
		
		if(s==Statuspfefile.REFUSED) {	
			System.out.println(n);
			//sms.send(n, msg);
			StudentNotification sn=new StudentNotification();
			sn.setContent(msg);
			sn.setDate(new Date(System.currentTimeMillis()));
			em.persist(sn);
			
		}
		int modified= q.executeUpdate();
		if(modified==1) {
			return true;
		}else {
			return false;
		}

	}

	@Override
	public boolean gradesupervisorpfefile(int id, float g) {
		Query q = em.createQuery("update PfeFile p set p.gradeSupervisor=:g where p.id=:id");
		q.setParameter("g", g);
		q.setParameter("id", id);
		Query q1=em.createQuery("SELECT p.gradeReporter from PfeFile p where p.id=:id");
		q1.setParameter("id", id);
		float gr=(float)q1.getSingleResult();
		if(gr-g<0) {
			System.out.println("GREAT");
		}
		else
		if(gr-g>5) {
			HeadDepartementNotification hdn=new HeadDepartementNotification();
			hdn.setContent("ATTENTION PLEASE REVIEW GRADES");
			hdn.setDate(new Date(System.currentTimeMillis()));
			em.persist(hdn);
			
		}
		int modified= q.executeUpdate();
		if(modified==1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean graderapporterpfefile(int id, float g) {
		Query q = em.createQuery("update PfeFile p set p.gradeReporter=:g where p.id=:id");
		q.setParameter("g", g);
		q.setParameter("id", id);
		Query q1=em.createQuery("SELECT p.gradeSupervisor from PfeFile p where p.id=:id");
		q1.setParameter("id", id);
		float gs=(float)q1.getSingleResult();
		if(gs-g<0) {
			System.out.println("GREAT");
		}
		if(gs-g>5) {
			HeadDepartementNotification hdn=new HeadDepartementNotification();
			hdn.setContent("ATTENTION PLEASE REVIEW GRADES");
			hdn.setDate(new Date(System.currentTimeMillis()));
			em.persist(hdn);
			
		}
		int modified= q.executeUpdate();
		if(modified==1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<PfeFile> getAllPfeFilencadreur(int id, Role r) {
		List<PfeFile> l
		= new ArrayList<PfeFile>();
		Teacher t = em.find(Teacher.class, id);
		System.out.println(t.getAddress());
		for (TeacherRole tr : t.getTeacherRole()) {
			if(tr.getRole()== r) {
				l.add(tr.getThesis().getPfeFile());
			}
		}
		return l;
	
	

		
		
		
	}
	
	
	
	
	
}
