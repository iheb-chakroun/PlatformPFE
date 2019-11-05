package business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import collection.Role;
import collection.Statuspfefile;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.users.Teacher;
import entities.users.TeacherRole;
import interfaces.enseignantServiceLocal;

@Stateless
public class enseignatBusiness implements enseignantServiceLocal{

	@PersistenceContext(unitName ="platform")
	EntityManager em;

	@Override
	public int getnbroleyear(int id, Role r, String year) {
		int nbrole=0;

		Teacher t = em.find(Teacher.class, id);
	for (TeacherRole tr : t.getTeacherRole()) {
		if(tr.getThesis().getPfeFile().getStudent().getClasse().getScholarYear().equals(year)) {
			if(tr.getRole().equals(r) )
			
			{
				nbrole++;	
			}
		  }
		}
	return nbrole;
		
	}

	@Override
	public boolean pickcategory(int id, String ids) {
		
		String[] categories=ids.split(";");
		Teacher t =em.find(Teacher.class, id);
		int i;
		for(String g:categories) {
			i=Integer.parseInt(g);
			Categorie c=em.find(Categorie.class, i);
			t.getCategories().add(c);
		}
		
		em.merge(t);
		return true;
	}
	
	
	
}
