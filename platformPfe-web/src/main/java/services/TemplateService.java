package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import business.TemplateBusiness;
import entities.administration.Template;
@Path("template")
public class TemplateService {
	@EJB
	TemplateBusiness templateService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTemplate(Template T) {
		try {
		 templateService.addTemplate(T);
		 return Response.status(Status.CREATED).build();
		}catch(Exception e) {
			 return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTemplate() {
	return Response.ok(templateService.findAllTemplate(), MediaType.APPLICATION_JSON).build();
		
	
	}
}
