package interfaces;

import java.util.List;

import entities.documents.Entreprise;
import entities.documents.PfeFile;

public interface PfeFileLocal {

	boolean updatePfeFile(PfeFile p);

	List<PfeFile> getAllPfeFile();

	void ajouterPfeFile(PfeFile p);

	boolean deletePfeFileById(int PfeFileId);

	PfeFile findPfeById(int id);
	

}
