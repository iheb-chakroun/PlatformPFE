package business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.tracking.HeadDepartementNotification;

@Stateless
@LocalBean
public class HeadDepartementNotificationBusiness implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	EntityManager em;
	
	public List<HeadDepartementNotification> getNotifByHeadId(int id) {
		TypedQuery<HeadDepartementNotification> q = em.createQuery("select t from HeadDepartementNotification t where t.departementHead.id =:id", HeadDepartementNotification.class);
		q.setParameter("id", id);
		return q.getResultList();
	}

}
