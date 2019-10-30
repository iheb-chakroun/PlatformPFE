package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.users.Admin;

@Local
public interface AdminBusinessLocal {
	public int addAdmin(Admin admin);
	public void removeAdmin(int id);
	public void updateAdmin(Admin admin);
	public Admin findAdminById(int id);
	public List<Admin> findAllAdmins();

}
