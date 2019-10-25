package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.users.DepartementHead;

@Remote
public interface DepartmentHeadRemote {
	
	public int addDepartmentHead(DepartementHead dh);
	public void removeDepartmentHead(int id);
	public void updateDepartmentHead(DepartementHead dh);
	public DepartementHead findDepartmentHeadById(int id);
	public List<DepartementHead> findDepartmentHeads();

}
