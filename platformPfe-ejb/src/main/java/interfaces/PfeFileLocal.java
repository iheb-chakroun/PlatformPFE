package interfaces;

import java.util.List;

import entities.documents.Entreprise;
import entities.documents.PfeFile;
import entities.users.Teacher;
import entities.users.TeacherRole;

public interface PfeFileLocal {

	boolean updatePfeFile(PfeFile p);

	List<PfeFile> getAllPfeFile();

	void ajouterPfeFile(PfeFile p);

	boolean deletePfeFileById(int PfeFileId);

	PfeFile findPfeById(int id);


	boolean ToProcessStatusPfe(int id);

	boolean ToValidStatusPfe(int id);



	boolean ToRefusedStatusPfe(int id, String cause);

	Teacher supervisorName(int id);
	

}
