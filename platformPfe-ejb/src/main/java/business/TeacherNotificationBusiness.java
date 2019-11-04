package business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.tracking.TeacherNotification;

@Stateless
@LocalBean
public class TeacherNotificationBusiness implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	EntityManager em;
	
	public List<TeacherNotification> getNotifByTeacherId(int id) {
		TypedQuery<TeacherNotification> q = em.createQuery("select t from TeacherNotification t where t.teacher.id =:id", TeacherNotification.class);
		q.setParameter("id", id);
		return q.getResultList();
	}

}
