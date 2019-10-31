package services;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.users.Student;
import interfaces.StudentRemote;

@Path("student")
public class StudentResource {
	
	@EJB
	StudentRemote studentBusiness;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addStudent(Student student) {
		if(student == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		studentBusiness.addStudent(student);
		return Response.status(Response.Status.CREATED).entity(student).build();
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateStudent(Student student) {
		if(student == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		studentBusiness.updateStudent(student);
		return Response.status(Response.Status.ACCEPTED).entity(student).build();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response findStudent(@PathParam("id")int id) {
		return Response.ok(studentBusiness.findStudentById(id), MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public Response deleteStudent(@PathParam("id") int id) {
		String deleted = "Student deleted :"+id;
		studentBusiness.removeStudent(id);
	    return Response.ok(MediaType.APPLICATION_JSON).entity(deleted).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response findAllStudents() {
		return Response.ok(studentBusiness.findAllStudents(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET()
	@Path("/year")
	@Consumes("application/json")
	@Produces("application/json")
	public Response findYearStudents(@QueryParam("year1") String year1, @QueryParam("year2") String year2, @QueryParam("year3") String year3 ) {
		return Response.ok(studentBusiness.findStudentsByYears(year1, year2, year3), MediaType.APPLICATION_JSON).build();
	}
	
	

}
