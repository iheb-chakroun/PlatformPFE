package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.Site;

@Remote
public interface SiteBusinessRemote {
	public int addSite(Site site);
	public Site getSiteById(int id);
	public List<Site> getAllSite();
	public void updateSite(Site site);
	public boolean deleteSite(int id);
}
