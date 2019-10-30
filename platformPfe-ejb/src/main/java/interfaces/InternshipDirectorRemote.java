  
package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.users.InternshipDirector;

@Remote
public interface InternshipDirectorRemote {
	
	public int addDirector(InternshipDirector director);
	public void removeDirector(int id);
	public void updateDirector(InternshipDirector director);
	public InternshipDirector findDirectorById(int id);
	public List<InternshipDirector> findAllDirectors();

}