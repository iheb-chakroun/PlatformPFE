package interfaces;

import javax.ejb.Local;

import collection.Role;

@Local
public interface enseignantServiceLocal {
	

	public int getnbroleyear(int id,Role r,String year);
	public boolean pickcategory(int id,String ids);
	
	
}
