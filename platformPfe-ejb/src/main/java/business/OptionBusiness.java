package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.administration.Option;
import interfaces.OptionBusinessLocal;
import interfaces.OptionBusinessRemote;

/**
 * Session Bean implementation class OptionBusiness
 */
@Stateless
@LocalBean
public class OptionBusiness implements OptionBusinessRemote, OptionBusinessLocal {

	@PersistenceContext
	EntityManager em;

	@Override
	public int addOption(Option option) {
		em.persist(option);
		return option.getId();
	}

	@Override
	public Option getOptionById(int id) {
		return em.find(Option.class, id);
	}

	@Override
	public List<Option> getAllOption() {
		return em.createQuery("select o from Option o", Option.class).getResultList();
	}

	@Override
	public void updateOption(Option option) {
		em.merge(option);
	}

	@Override
	public boolean deleteOption(int id) {
		try {
			em.remove(em.find(Option.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
