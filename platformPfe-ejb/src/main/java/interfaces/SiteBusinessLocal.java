package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Departement;
import entities.administration.Site;
import entities.administration.Template;

@Local
public interface SiteBusinessLocal {
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
