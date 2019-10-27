package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Site;

@Local
public interface SiteBusinessLocal {
	public int addSite(Site site);
	public Site getSiteById(int id);
	public List<Site> getAllSite();
	public void updateSite(Site site);
	public boolean deleteSite(int id);
}
