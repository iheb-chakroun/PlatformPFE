package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.users.Admin;

@Remote
public interface AdminRemote {
	public int addAdmin(Admin admin);
	public void removeAdmin(int id);
	public void updateAdmin(Admin admin);
	public Admin findAdminById(int id);
	public List<Admin> findAllAdmins();

}
