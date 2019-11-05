package interfaces;

import java.util.List;

import entities.documents.PfeFile;
import entities.documents.PfeFileChange;

public interface PfeFileChangeLocal {

	void ajouterPfeFileChange(PfeFileChange p);

	boolean deletePfeFileChangeById(int id);

	List<PfeFileChange> getAllChanges();



	PfeFileChange findPfeChangeById(int id);

	boolean ValidatePfeFileChange(int id);

	boolean RefusePfeFileChange(int id);


}
