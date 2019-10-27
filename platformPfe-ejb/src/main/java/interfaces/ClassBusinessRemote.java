package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.Class;

@Remote
public interface ClassBusinessRemote {
	public int addClass(Class classe);
	public Class getClassById(int id);
	public List<Class> getAllClass();
	public void update(Class classe);
	public boolean deleteClass(int id);

}
