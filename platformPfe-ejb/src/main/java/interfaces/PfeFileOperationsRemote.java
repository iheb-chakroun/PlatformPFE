package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.documents.PfeFile;

@Remote
public interface PfeFileOperationsRemote {

	public List<PfeFile> getAcceptedPfeFiles();
	
}
