package services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import business.SearchBusiness;



@Path("student")
public class SearchServices  {
	@EJB
	SearchBusiness studentServices;
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@QueryParam(value ="option") String option,@QueryParam(value ="category") String categorie,@QueryParam(value ="year") String year,@QueryParam(value ="country") String pays){
		if((option == null)&& (categorie == null)&&( year == null )&&( pays == null)) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}else
		return Response.ok(studentServices.getStudents(option, categorie, year, pays), MediaType.APPLICATION_JSON).build();
		}
	
	
}
