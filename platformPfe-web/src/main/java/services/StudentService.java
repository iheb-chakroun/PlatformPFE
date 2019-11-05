package services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import business.StudentBusiness;
import interfaces.PfeFileLocal;
import interfaces.StudentLocal;

@Path("student")
public class StudentService {
	@EJB
	StudentLocal studentService;

	@PUT
	@Path("/activate/{id}")
	@Produces("application/json")
	public Response ActivateAccount(@PathParam (value="id") int id ) {
System.out.println("Class Student service llllllllllllllllll");

		if(studentService.activateAccount(id) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.NOT_ACCEPTABLE).build();

	}
	@PUT
	@Path("/authorize/{id}")
	@Produces("application/json")
	public Response authorizeStudent(@PathParam (value="id") int id ) {


		if(studentService.authorizeStudent(id) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.NOT_ACCEPTABLE).build();

	}
}
