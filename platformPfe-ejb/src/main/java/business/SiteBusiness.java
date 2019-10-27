package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.administration.Site;
import interfaces.SiteBusinessLocal;
import interfaces.SiteBusinessRemote;

/**
 * Session Bean implementation class SiteBusiness
 */
@Stateless
@LocalBean
public class SiteBusiness implements SiteBusinessRemote, SiteBusinessLocal {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public int addSite(Site site) {
		SchoolBusiness sb = new SchoolBusiness();
		site.setSchool(sb.getSchoolById(site.getSchool().getId()));
		em.persist(site);
		return site.getId();
	}

	@Override
	public Site getSiteById(int id) {
		return em.find(Site.class, id);
	}

	@Override
	public List<Site> getAllSite() {
		return em.createQuery("select s from Site s", Site.class).getResultList();
	}

	@Override
	public void updateSite(Site site) {
		em.merge(site);
		
	}

	@Override
	public boolean deleteSite(int id) {
		try {
			em.remove(em.find(Site.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
