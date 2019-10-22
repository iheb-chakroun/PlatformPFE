package services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import interfaces.EmployeLocal;

@Path("employe")
public class EmployeService {
	
	@EJB
	EmployeLocal employeBusiness;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("1")
	public String sayHello1() {
		String s = "hello";
		return s;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("2")
	public String sayHello2() {
		String s = employeBusiness.sayHello();
		return s;
	}

}
