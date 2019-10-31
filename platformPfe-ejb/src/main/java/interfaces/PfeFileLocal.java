package interfaces;

import java.util.List;

import entities.documents.PfeFile;
import entities.users.DepartementHead;

public interface PfeFileLocal {

	boolean updatePfeFile(PfeFile p);
	List<PfeFile> getAllPfeFile();
	void ajouterPfeFile(PfeFile p);
	boolean deletePfeFileById(int PfeFileId);
	PfeFile findPfeById(int id);
	public List<PfeFile> filterPfe(String year);
	public List<PfeFile> filterPfeNonTreated();
	public DepartementHead accepterPfe(int id);
	

}
