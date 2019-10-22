package business;

import javax.ejb.Stateless;

import interfaces.EmployeLocal;


@Stateless
public class EmployeBusiness implements EmployeLocal {

	@Override
	public String sayHello() {
		return "hello from ejb";
		
	}

}
