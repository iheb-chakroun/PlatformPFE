package services;




import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import interfaces.HistoryRemote;

@Path("history")
public class HistoryResource {
	
	@EJB
	HistoryRemote historyBusiness;
	
	@Path("students")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllStudentNotifications() {
		if (historyBusiness.getAllStudentNotifications().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(historyBusiness.getAllStudentNotifications(), MediaType.APPLICATION_JSON).build(); 
	}
	
	
	
	@Path("teachers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTeacherNotifications() {
		if (historyBusiness.getAllTeacherNotifications().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(historyBusiness.getAllTeacherNotifications(), MediaType.APPLICATION_JSON).build(); 
	}
	
	@Path("student/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllNotificationsByStudent(@PathParam("id") int id) {
		try {
			return Response.ok(historyBusiness.findAllNotificationsByStudent(id), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	@Path("teacher/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllNotificationsByTeacher(@PathParam("id") int id) {
		try {
			return Response.ok(historyBusiness.findAllNotificationsByTeacher(id), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).build();
		}	
	}
}
