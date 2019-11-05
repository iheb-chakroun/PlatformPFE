package interfaces;

import javax.ejb.Local;

@Local
public interface StudentLocal {

	boolean activateAccount(int id);

	boolean authorizeStudent(int id);

}
