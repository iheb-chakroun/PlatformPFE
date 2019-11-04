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

import entities.users.Teacher;
import interfaces.TeacherRemote;

@Path("teacher")
public class TeacherResource {
	
	@EJB
	TeacherRemote teacherBusiness;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addTeacher(Teacher teacher) {
		if(teacher == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		teacherBusiness.addTeacher(teacher);
		return Response.status(Response.Status.CREATED).entity(teacher).build();
	}
	@POST
	@Path("/rapporteur")
	@Produces("application/json")
	public Response asignRapporteur(@QueryParam("teacher") int id_teacher, @QueryParam("pfe") int id_pfe) {
		
		int res = teacherBusiness.addTeacherRole(id_teacher, id_pfe);
		if( res == 0) {
			String message = "{\"message\": \"The pfe not approved!\"}";
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(message).build();
		}
		Teacher teacher = teacherBusiness.findTeacherById(res);
		String message = "{\"message\": \"The rapporteur: "+teacher.getFirstName()+" "+teacher.getLastName()+" is assigned to pfe number: "+id_pfe+".\"}";
		return Response.status(Response.Status.OK).entity(message).build();
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateTeacher(Teacher teacher) {
		if(teacher == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		teacherBusiness.updateTeacher(teacher);
		return Response.status(Response.Status.ACCEPTED).entity(teacher).build();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response findTeacher(@PathParam("id")int id) {
		return Response.ok(teacherBusiness.findTeacherById(id), MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public Response deleteTeacher(@PathParam("id") int id) {
		String deleted = "Employe deleted :"+id;
		teacherBusiness.removeTeacher(id);
	    return Response.ok(MediaType.APPLICATION_JSON).entity(deleted).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response findAllTeachers() {
		return Response.ok(teacherBusiness.findAllTeachers(), MediaType.APPLICATION_JSON).build();
	}

}
