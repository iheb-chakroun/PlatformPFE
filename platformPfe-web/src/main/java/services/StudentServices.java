package services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.StudentBusiness;



@Path("student")
public class StudentServices  {
	@EJB
	StudentBusiness studentServices;
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@QueryParam(value ="option") String option,@QueryParam(value ="category") String categorie,@QueryParam(value ="year") String year,@QueryParam(value ="country") String pays){
		System.out.println(option+" "+categorie+" "+year+" "+pays);
		return Response.ok(studentServices.getStudents(option, categorie, year, pays), MediaType.APPLICATION_JSON).build();
		}

}
