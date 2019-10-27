package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Class;

@Local
public interface ClassBusinessLocal {
	public int addClass(Class classe);
	public Class getClassById(int id);
	public List<Class> getAllClass();
	public void update(Class classe);
	public boolean deleteClass(int id);
}
