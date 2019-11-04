package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.users.Student;
import interfaces.StudentRemote;
import utilities.EmailBusiness;
import utilities.EmailTemplate;
import utilities.Reason;


@Path("student")
public class StudentResource {
	
	@EJB
	StudentRemote studentBusiness;
	
	EmailBusiness eb = new EmailBusiness();
	
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
	
	@POST
	@Path("/authorization")
	@Consumes("application/json")
	@Produces("application/json")
	public Response authorizeStudent(Student student) {
		if(student == null) {
			String message = "{\"message\": \"No content!\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
		Student st = studentBusiness.findStudentById(student.getId());
		if(!st.isStatus()) {
			studentBusiness.authorizeStudent(student.getId());
			String message = "{\"message\": \"Access has been granted!\"}";
			return Response.status(Response.Status.OK).entity(message).build();
		}else {
			studentBusiness.authorizeStudent(student.getId());
			String message = "{\"message\": \"The student have been banned from access!\"}";
			return Response.status(Response.Status.OK).entity(message).build();
		}
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
	//prerequisite
	@PUT
	@Path("/annulation")
	@Consumes("application/json")
	@Produces("application/json")
	public Response demandeAnnulation(Student student) {
		if(student == null) {
			String message = "{\"message\": \"No Content!\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
		Student st = studentBusiness.findStudentById(student.getId());
		studentBusiness.demandeAnnulation(st.getPfeFile().getId());
		String message = "{\"message\": \"Your annulation demand for the pfe number:"+st.getPfeFile().getId()+" has been submitted successfully and waiting for approval.\"}";
		return Response.status(Response.Status.ACCEPTED).entity(message).build();
	}
	
	@PUT
	@Path("/annulation/approval")
	@Consumes("application/json")
	@Produces("application/json")
	public Response acceptAnnulation(Student student) {
		if(student == null) {
			String message = "{\"message\": \"No Content!\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
		Student st = studentBusiness.findStudentById(student.getId());
		studentBusiness.approveAnnulation(st.getPfeFile().getId());
		String message = "{\"message\": \"The annulation demand for the pfe number: "+st.getPfeFile().getId()+" has been approved.\","
				+ "\"Archived\": \""+st.getPfeFile().getTitle()+" has been achrived.\"}";
		return Response.status(Response.Status.ACCEPTED).entity(message).build();
	}
	@PUT
	@Path("/annulation/denial")
	@Consumes("application/json")
	@Produces("application/json")
	public Response declineAnnulation(Reason reason) {
		if(reason == null) {
			String message = "{\"message\": \"No Content!\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
		Student st = studentBusiness.findStudentById(reason.getId());
		studentBusiness.declineAnnulation(reason);
		String message = "{\"message\": \"The annulation demand for the pfe number: "+st.getPfeFile().getId()+" has been denied.\"}";
		return Response.status(Response.Status.OK).entity(message).build();
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
	public Response findYearStudents(String... years ) {
		return Response.ok(studentBusiness.findStudentsByYears(years), MediaType.APPLICATION_JSON).build();
	}
	
	@GET()
	@Path("/year/notifications")
	@Consumes("application/json")
	@Produces("application/json")
	public Response notfiyStudents(String... years ) {
		List<Student> st = new ArrayList<>();
		st = studentBusiness.findStudentsByYears(years);
		st.forEach(s -> eb.sendEmail(s.getEmail(), "Notification", EmailTemplate.template));
		return Response.ok(st, MediaType.APPLICATION_JSON).build();
	}
	
	

}
