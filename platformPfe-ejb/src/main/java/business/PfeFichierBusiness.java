package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import collection.Statuspfefile;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import interfaces.PfeFichierLocal;

@Stateless
public class PfeFichierBusiness implements PfeFichierLocal{

	
	@PersistenceContext(unitName ="platform")
	EntityManager em;

	@Override
	public List<PfeFile> getAllPfeFileyear(String year) {
		Query q = em.createQuery("SELECT p from PfeFile p where p.student.classe.scholarYear=:year ", PfeFile.class);
		q.setParameter("year", year);
		List<PfeFile> pfefiles = q.getResultList();
		return pfefiles;
	}

	@Override
	public List<PfeFile> getAllPfeFileyears(String year) {
		List<PfeFile> pfefiles = null;
		String[] years=year.split(";");
		
		for(String y:years) {
			Query q = em.createQuery("SELECT p from PfeFile p where p.student.classe.scholarYear=:y ", PfeFile.class);
			q.setParameter("y", y);
			pfefiles = q.getResultList();		
			System.out.println(y);
			
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
	public boolean prevalidatepfefile(int id,Statuspfefile s) {
		Query q = em.createQuery("update PfeFile p set p.spf=:s where p.id=:id");
		q.setParameter("s", s);
		q.setParameter("id", id);
		System.out.println(s);
		if(s==Statuspfefile.REFUSED) {
			
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
		int modified= q.executeUpdate();
		if(modified==1) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
}
