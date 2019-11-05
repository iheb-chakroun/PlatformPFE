package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.users.Student;
import interfaces.StudentLocal;
import interfaces.StudentRemote;
@Stateless
@LocalBean
public class SearchBusiness implements StudentLocal,StudentRemote{
	@PersistenceContext(unitName="platform") 
	EntityManager em;

	@Override
	public List<Student> getStudents(String option,String categorie,String year,String pays) {
		String cat,op,pa,an;
		
		if(categorie != null) {
		  cat="c.label='"+categorie+"'and ";
		}else {
			cat="";
			
		}
		if(option != null) {
			  op="op.label='"+option+"' and";
			}else {
				op="";
				
			}
		if(pays != null) {
			  pa="e.country='"+pays+"' and ";
			}else {
				pa="";
				
			}
		
		if(year!= null) {
			  an=" cl.scholarYear='"+year+"' and ";
			}else {
				an="";
				
			}
		
		
		Query query=em.createQuery("Select s.id, s.firstName ,s.lastName,c.label,e.country,op.label,cl.scholarYear"
				+ " from Student s join s.pfeFile p join p.categories c join p.entreprise e join s.classe cl join cl.option op"
				+ " where "+cat+pa+op+an+" 1=1");
				System.out.println(query.getResultList());
		       return query.getResultList();
		
		
		
		
		
		
	}
	
	
	


}
