package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.administration.Departement;
import entities.administration.School;
import entities.administration.Site;
import entities.administration.Template;
import entities.users.InternshipDirector;
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
	public boolean affecterSchool(int idSite, int idSchool) {
		try {
			School school = em.find(School.class, idSchool);
			Site site = em.find(Site.class, idSite);
			site.setSchool(school);
			em.merge(site);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean affecterInternshipDirector(int idSite, int idInternshipDirector) {
		try {
			InternshipDirector internshipDirector = em.find(InternshipDirector.class, idInternshipDirector);
			Site site = em.find(Site.class, idSite);
			site.setInternshipDirector(internshipDirector);
			em.merge(site);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Departement> getSiteDepartements(int idSite) {
		System.out.println(idSite);
		TypedQuery<Departement> q = em.createQuery("select s from Departement s where s.site.id =:id",
				Departement.class);
		q.setParameter("id", idSite);
		return q.getResultList();
	}

	@Override
	public List<Template> getSiteTemplates(int idSite) {
		TypedQuery<Template> q = em.createQuery("select s from Template s where s.site.id =:id", Template.class);
		q.setParameter("id", idSite);
		return q.getResultList();
	}

	@Override
	public void updateSite(Site site) {
		//site.setSchool(em.find(School.class, site.getSchool().getId()));
		//site.setInternshipDirector(em.find(InternshipDirector.class, site.getInternshipDirector().getId()));
		System.out.println("here ----------------------------------- ");
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
