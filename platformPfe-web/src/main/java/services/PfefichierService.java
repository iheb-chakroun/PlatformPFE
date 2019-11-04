package services;

import java.io.StringReader;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
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

import com.textmagic.sdk.RestException;

import collection.Role;
import collection.Statuspfefile;
import entities.documents.Categorie;
import interfaces.CategorieSerivceLocal;
import interfaces.PfeFichierLocal;

@Path("pfefichier")
public class PfefichierService {
	
	@EJB
	PfeFichierLocal PfeFichierBusiness;
	
	@GET
	@Path("one/{id}/{year}/{r}")
	@Produces("application/json")
	public Response getAllpfefileone(@PathParam("id") int id,@PathParam("year") String year,@PathParam("r") Role r) {

		if (PfeFichierBusiness.getAllPfeFileyear(id,year,r) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (PfeFichierBusiness.getAllPfeFileyear(id,year,r).size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(PfeFichierBusiness.getAllPfeFileyear(id,year,r), MediaType.APPLICATION_JSON).build();

	}
	
	@GET
	@Path("all")
	@Produces("application/json")
	public Response getAllpfefilesall(@QueryParam("id") int id,@QueryParam("year") String year,@QueryParam("r") Role r) {

		
		if (PfeFichierBusiness.getAllPfeFileyears(id,year,r) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (PfeFichierBusiness.getAllPfeFileyears(id,year,r).size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(PfeFichierBusiness.getAllPfeFileyears(id,year,r), MediaType.APPLICATION_JSON).build();

	
	}
	
	@GET
	@Path("prevalidated/{s}")
	@Produces("application/json")
	public Response getAllprevalidatedfiles(@PathParam("s") Statuspfefile s) {

		if (PfeFichierBusiness.getAllprevalidatedfiles(s) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (PfeFichierBusiness.getAllprevalidatedfiles(s).size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(PfeFichierBusiness.getAllprevalidatedfiles(s), MediaType.APPLICATION_JSON).build();

	}
	
	
	@PUT
	@Path("modify/{id}/{s}/{msg}")
	//@Consumes("application/json")
	//@Produces(MediaType.TEXT_PLAIN)
	public Response modifierCategorie(@PathParam("id") int id ,@PathParam("s")Statuspfefile s ,@PathParam("msg")String msg ) {
		
			if(PfeFichierBusiness.prevalidatepfefile(id, s,msg) ) {
			return Response.status(Status.OK).build(); }
		return Response.status(Status.BAD_REQUEST).build();
		}
	
	@PUT
	@Path("gradesupervisor/{id}/{g}")
	//@Consumes("application/json")
	//@Produces(MediaType.TEXT_PLAIN)
	public Response sgradesupervisorpfefile(@PathParam("id") int id ,@PathParam("g") float g) {
		
			if(PfeFichierBusiness.gradesupervisorpfefile(id, g) ) {
			return Response.status(Status.OK).build(); }
		return Response.status(Status.BAD_REQUEST).build();

}
	@PUT
	@Path("gradereporter/{id}/{g}")
	//@Consumes("application/json")
	//@Produces(MediaType.TEXT_PLAIN)
	public Response sgraderapporterpfefile(@PathParam("id") int id ,@PathParam("g") float g) {
		
			if(PfeFichierBusiness.graderapporterpfefile(id, g) ) {
			return Response.status(Status.OK).build(); }
		return Response.status(Status.BAD_REQUEST).build();

}
	
	@GET
	@Path("list")
	@Produces("application/json")
	public Response getAllprevalidatedfiles(@QueryParam("id") int id,@QueryParam("r") Role r) {

		if (PfeFichierBusiness.getAllPfeFilencadreur(id,r) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (PfeFichierBusiness.getAllPfeFilencadreur(id,r).size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(PfeFichierBusiness.getAllPfeFilencadreur(id,r), MediaType.APPLICATION_JSON).build();

	}
	

}
