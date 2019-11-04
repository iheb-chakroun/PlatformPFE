package services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.HeadDepartementNotificationBusiness;

@Path("headDepNotification")
public class HeadDepartementNotificationService {

	@EJB
	HeadDepartementNotificationBusiness headDepartementNotificationBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNotif(@QueryParam("id") int id) {
		return Response.ok(headDepartementNotificationBusiness.getNotifByHeadId(id), MediaType.APPLICATION_JSON).build();
	}

}
