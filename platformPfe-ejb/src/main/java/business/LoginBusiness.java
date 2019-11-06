package business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.users.Admin;
import entities.users.Employe;
import entities.users.Student;

@Stateless
public class LoginBusiness {
	@PersistenceContext(unitName="platform")
	EntityManager em;
	
	public Object login(String username, String password) {
		// TODO Auto-generated method stub
		TypedQuery<Employe> qe = em.createQuery("select e from Admin e where e.email=:username and e.password=:password", Employe.class);
		qe.setParameter("username", username);
		qe.setParameter("password", password);
		System.out.println("admin"+qe.getResultList());
		
		TypedQuery<Student> qs = em.createQuery("select s from Student s where s.email=:username and s.password=:password", Student.class);
		qs.setParameter("username", username);
		qs.setParameter("password", password);
		Object obj1 = new Object();
		Object obj2 = new Object();

		
		try {
			 obj1 = qe.getSingleResult();
		}catch(Exception e) {
			System.out.println("emloye");
		}
		try {
			System.out.println("sjdlkasjdl");
			 obj2 = qs.getSingleResult();
			 System.out.println(qs.getSingleResult().getFirstName());
		}catch(Exception e) {
			System.out.println("student");
		}
		

        try {
        	if(obj2 instanceof Student) {
        		return obj2;
        	}
        	if(obj1 instanceof Admin) {
        		return  obj1;	
        	}	
        	return null;
           
        } catch (Exception e) {
            return null;
        }
	}

}
