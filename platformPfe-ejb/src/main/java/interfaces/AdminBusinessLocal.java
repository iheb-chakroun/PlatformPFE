package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Departement;
import entities.administration.School;
import entities.administration.Site;
import entities.users.Admin;

@Local
public interface AdminBusinessLocal {
	public int addAdmin(Admin admin);
	public void removeAdmin(int id);
	public void updateAdmin(Admin admin);
	public Admin findAdminById(int id);
	public List<Admin> findAllAdmins();
	public List<Admin> getAvailableAdmin();
	public School getAdminSchool(String email);
	public List<Site> getAdminSite(String email);
	public List<Departement> getAdminDepartment(String email);

}
