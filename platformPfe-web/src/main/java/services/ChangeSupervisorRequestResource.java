package services;




import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.administration.ChangeSupervisorRequest;
import interfaces.ChangeSupervisorRequestRemote;

@Path("supervisor")
public class ChangeSupervisorRequestResource {
	
	@EJB
	ChangeSupervisorRequestRemote changeSupervisorRequestRemote;
	
	@Path("active")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActiveForSupervisingTeachers() {
		if (changeSupervisorRequestRemote.getAllActiveForSupervisingTeachers().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(changeSupervisorRequestRemote.getAllActiveForSupervisingTeachers(), MediaType.APPLICATION_JSON).build(); 
	}
	
	
	@Path("change/request/{id}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response requestSupervisorchange(@PathParam("id") int id, ChangeSupervisorRequest c) {
		if (changeSupervisorRequestRemote.requestSupervisorchange(id, c))
			return Response.status(Response.Status.CREATED).build();
		else
			return Response.ok(changeSupervisorRequestRemote.getAllActiveForSupervisingTeachers(), MediaType.APPLICATION_JSON).build(); 
	}
	
	@Path("change/accept/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response acceptSupervisorchange(@PathParam("id") int id) {
	
		if (changeSupervisorRequestRemote.acceptSupervisorchange(id))
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.ok(changeSupervisorRequestRemote.acceptSupervisorchange(id), MediaType.APPLICATION_JSON).build(); 

	}
	
	@Path("change/refuse/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response refuseSupervisorchange(@PathParam("id") int id) {
	
		if (changeSupervisorRequestRemote.refuseSupervisorchange(id))
			return Response.status(Response.Status.OK).build();
		else
			return Response.ok(changeSupervisorRequestRemote.refuseSupervisorchange(id), MediaType.APPLICATION_JSON).build(); 

	}
	
	@Path("assign/{studentId}/{teacherId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignSupervisorchange(@PathParam("studentId") int studentId,@PathParam("teacherId") int teacherId) {
	
		if (changeSupervisorRequestRemote.assignSupervisorchange(studentId,teacherId))
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.ok(changeSupervisorRequestRemote.assignSupervisorchange(studentId, teacherId), MediaType.APPLICATION_JSON).build(); 

	}
	
}
