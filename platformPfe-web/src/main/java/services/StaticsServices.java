package services;

import java.io.InputStream;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import business.StaticBusiness;
import business.TemplateBusiness;
import entities.administration.Template;
import utilities.Roles;
import utilities.Secured;

@Path("stat")
public class StaticsServices {
	@EJB
	StaticBusiness statServices;
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response getstat(@QueryParam(value = "country") String country,@QueryParam(value = "year")String year) {
		
		return Response.ok(statServices.percentageEntreprisePfeFilesByYear(country,year), MediaType.APPLICATION_JSON).build();
		}

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("country/{country}")
	public Response getstatEvolution(@PathParam("country") String country) {
		
		return Response.ok(statServices.percentageEvoluation(country), MediaType.APPLICATION_JSON).build();
		}

	

	@GET
	@Path("Categorie")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoriesByPfeOrder() {
		
		return Response.ok(statServices.getCategoriesOrder(), MediaType.APPLICATION_JSON).build();
		}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("evolution/{category}")
	public Response getCategoryEvolutionNumberByYear(@PathParam("category") String cat) {
		
		return Response.ok(statServices.EvoluationNumberCategoryByYear(cat), MediaType.APPLICATION_JSON).build();
		
		}



}
