package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.documents.PfeFile;

@Remote
public interface PfeFileOperationsRemote {

	public List<PfeFile> getAcceptedPfeFiles();
	
	public List<PfeFile> getUngradedPfeFiles();
	
	public boolean validatePfeCategorie(int categorieId);
	
	public boolean affectPrevalidator(int teacherId, int pfeFileId);
	
	public boolean affectTeacherRole(int teacherId, int pfeFileId, int role);
}
