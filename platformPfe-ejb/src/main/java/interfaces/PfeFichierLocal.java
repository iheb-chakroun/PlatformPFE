package interfaces;

import java.util.List;

import javax.ejb.Local;


import collection.Role;
import collection.Statuspfefile;
import entities.documents.Categorie;
import entities.documents.PfeFile;

@Local
public interface PfeFichierLocal {

	
	public List<PfeFile> getAllPfeFileyear(int id,String year,Role r);
	public List<PfeFile> getAllPfeFileyears(int id,String year,Role r);
	public List<PfeFile> getAllprevalidatedfiles(Statuspfefile s);
	public boolean prevalidatepfefile(int id,Statuspfefile s,String msg) ;
	public boolean gradesupervisorpfefile(int id,float g);
	public boolean graderapporterpfefile(int id,float g);
	public List<PfeFile> getAllPfeFilencadreur(int id,Role r);
	

}
