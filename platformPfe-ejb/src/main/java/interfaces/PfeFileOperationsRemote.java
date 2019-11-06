package interfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.tracking.ArchivePfeFile;
import entities.users.Teacher;

@Remote
public interface PfeFileOperationsRemote {
	
	public List<ArchivePfeFile> showPfeFileArchive(int pfeFileId);

	public List<ArchivePfeFile> searchPfeFileArchive(int pfeFileId,String date,String event,String emeteur,Boolean status);
	
	public List<PfeFile> getAcceptedPfeFiles();
	
	public List<PfeFile> WaitingForSupervisorAffect();
	
	public List<PfeFile> WaitingForRaporteurAffect();
	
	public Set<Teacher> teachersByCategory(int cid);
	
	public List<PfeFile> getUngradedPfeFiles();
	
	public boolean validatePfeCategorie(int categorieId);
	
	public boolean affectPrevalidator(int teacherId, int pfeFileId);
	
	public boolean affectTeacherRole(int teacherId, int pfeFileId, int role);
	
	public HashMap<Integer, Integer>  SortedTeacher();
	
	
}
