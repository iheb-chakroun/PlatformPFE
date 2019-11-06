package services;

import java.util.List;
import java.util.function.Consumer;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.Producer;
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
import javax.ws.rs.core.Response.Status;

import business.TemplateBusiness;
import entities.administration.Template;
import utilities.Roles;
import utilities.Secured;
@Path("template")
public class TemplateService {
	@EJB
	TemplateBusiness templateService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTemplate(Template T) {
		try {
			System.out.println(T.toString());
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
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("affect")
	public Response AffectSiteToTemplate(@QueryParam(value="idTemp") int idtemp,@QueryParam(value="idSite") int idSite) {
		try {
		
		templateService.affectSiteToTemplate(idtemp, idSite);
			
		 return Response.status(Status.CREATED).build();
		 
		}catch(Exception e) {
			 return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}
	
	
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getTemplateById(@PathParam("id") int id) {
		return Response.ok(templateService.findTemplateById(id), MediaType.APPLICATION_JSON).build();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateTemplate(@PathParam("id") int id,Template template) {
		
		templateService.updateTemplate(id,template);
		return Response.status(Status.CREATED).build();
	}
		
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response deleteTemplate(@PathParam("id") int id) {
		templateService.removeTemplate(id);
		 	return Response.status(Status.CREATED).build();
	}
	

	@GET
	@Path("Api")
    @Produces(MediaType.TEXT_PLAIN)
	public Response apiPdf(@QueryParam(value ="content") String content) {
	try {
		
		templateService.exportApi(content);

	
	 return Response.ok(templateService.exportApi(content), MediaType.TEXT_PLAIN).build();
	 
	}catch(Exception e) {
		 return Response.status(Status.NOT_ACCEPTABLE).build();
	}
		}

	
	
	
}
