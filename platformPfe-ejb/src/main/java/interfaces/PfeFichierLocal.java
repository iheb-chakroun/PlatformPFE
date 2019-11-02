package interfaces;

import java.util.List;

import javax.ejb.Local;

import collection.Statuspfefile;
import entities.documents.Categorie;
import entities.documents.PfeFile;

@Local
public interface PfeFichierLocal {

	
	public List<PfeFile> getAllPfeFileyear(String year);
	public List<PfeFile> getAllPfeFileyears(String year);
	public List<PfeFile> getAllprevalidatedfiles(Statuspfefile s);
	public boolean prevalidatepfefile(int id,Statuspfefile s);
	public boolean gradesupervisorpfefile(int id,float g);
	public boolean graderapporterpfefile(int id,float g);


}
