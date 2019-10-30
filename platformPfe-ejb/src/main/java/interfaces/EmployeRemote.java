  
package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.users.Employe;

@Remote
public interface EmployeRemote {
	
	public int addEmploye(Employe employe);
	public void removeEmploye(int id);
	public void updateEmploye(Employe employe);
	public Employe findEmployeById(int id);
	public List<Employe> findAllEmployes();

}