package services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import interfaces.StudentRemote;

@Path("student")
public class StudentService {
	
	@EJB
	StudentRemote studentBusiness;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("1")
	public String sayHello1() {
		String s = "hello";
		return s;
	}
	

}
