package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.Departement;
import entities.administration.Site;
import entities.administration.Template;

@Remote
public interface SiteBusinessRemote {
	public int addSite(Site site);

	public Site getSiteById(int id);

	public List<Site> getAllSite();

	public void updateSite(Site site);

	public boolean deleteSite(int id);

	boolean affecterSchool(int idSite, int idSchool);

	boolean affecterInternshipDirector(int idSite, int idInternshipDirector);
	
	List<Departement> getSiteDepartements(int idSite);
	
	List<Template> getSiteTemplates(int idSite);
}
