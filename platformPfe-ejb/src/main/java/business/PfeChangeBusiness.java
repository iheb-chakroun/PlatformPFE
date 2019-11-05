package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import collection.PfeChangePosition;
import collection.PfeChangeStatus;
import entities.documents.Entreprise;
import entities.documents.PfeFile;
import entities.documents.PfeFileChange;
import interfaces.PfeFileChangeLocal;

@Stateless
public class PfeChangeBusiness implements PfeFileChangeLocal {
	@PersistenceContext(unitName = "platform")
	EntityManager em;
	
	@Override
	public void ajouterPfeFileChange(PfeFileChange p) {
		// TODO Auto-generated method stub
		if(p.getField()== PfeChangePosition.O)
	p.setStatus(PfeChangeStatus.VALID);
		else
p.setStatus(PfeChangeStatus.PROCESSING);
	

em.persist(p);

	
	}
	
	@Override
	public PfeFileChange findPfeChangeById(int id) {

		PfeFileChange a = em.find(PfeFileChange.class, id);

		return a;
	}
	public boolean deletePfeFileChangeById(int id) {
		// TODO Auto-generated method stub
		PfeFileChange emp = em.find(PfeFileChange.class, id);

		try {
			em.remove(emp);
			return true;

		} catch (Exception e) {
			return false;
		}

	}
	@Override
	public List<PfeFileChange> getAllChanges() {
		// TODO Auto-generated method stub
		List<PfeFileChange> p = em.createQuery("select e from PfeFileChange e", PfeFileChange.class).getResultList();
		return p;
	}
	
	
	@Override
	public boolean ValidatePfeFileChange(int id) {
		// TODO Auto-generated method stub
PfeFileChange p = findPfeChangeById(id);
	p.setStatus(PfeChangeStatus.VALID);
	
	

try {
em.merge(p);
if(p.getField()== PfeChangePosition.F)
p.getPfeFile().setFunctionnalities(p.getContent());
if(p.getField()== PfeChangePosition.P)
p.getPfeFile().setProblematic(p.getContent());


SendMail s = new SendMail() ;
s.envoi(p.getPfeFile().getEmailProfessionel(), "Pfe File Modifications", "your modifications on PFE File has been approved ");
s.envoi(p.getPfeFile().getEmailPersonel(), "Pfe File Modification", "your modifications on PFE File has been approved");

return true;
}catch(Exception e) {
	
	return false;
	
}
}
	@Override
	public boolean RefusePfeFileChange(int id) {
		// TODO Auto-generated method stub
		PfeFileChange p = findPfeChangeById(id);

	p.setStatus(PfeChangeStatus.REFUSED);
	
	
try {

em.merge(p);

SendMail s = new SendMail() ;
s.envoi(p.getPfeFile().getEmailProfessionel(), "Pfe File Modifications", "your modifications on PFE File has been refused ");
s.envoi(p.getPfeFile().getEmailPersonel(), "Pfe File Modification", "your modifications on PFE File has been refused");
return true;
}catch(Exception e) {
	
	return false;
	
}
}

	
	
	
}
