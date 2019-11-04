  
package interfaces;

import java.util.List;

import entities.documents.PfeFile;

public interface PfeFileLocal {

	boolean updatePfeFile(PfeFile p);

	List<PfeFile> getAllPfeFile();

	void ajouterPfeFile(PfeFile p);

	boolean deletePfeFileById(int PfeFileId);

	PfeFile findPfeById(int id);
	
	public List<PfeFile> getListPfeFileByManyIds(String ids);
				
	
	

}