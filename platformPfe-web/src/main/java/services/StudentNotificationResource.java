package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.tracking.StudentNotification;
import interfaces.StudentRemote;

@Path("notifications")
public class StudentNotificationResource {
	
	@EJB
	StudentRemote studentBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<StudentNotification> findAllNotifications() {
		return studentBusiness.findAllNotifications();
	}

}
