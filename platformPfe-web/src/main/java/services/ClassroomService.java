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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import business.ClassroomBusiness;
import entities.administration.Classroom;

@Path("classroom")
public class ClassroomService {

	@EJB
	ClassroomBusiness cb;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterClassroom(Classroom classroom) {
		try {
			cb.addClassroom(classroom);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerClasssroom() {
		return Response.ok(cb.getClassrooms(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerClassroomParId(@PathParam("id") int id) {
		return Response.ok(cb.getClassroomById(id), MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifierClassroom(Classroom classroom) {
		cb.updateClassrom(classroom);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("{id}")
	public Response supprimerClassroom(@PathParam(value = "id") int id) {
		if (cb.deleteClassroom(id)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();

	}
}
