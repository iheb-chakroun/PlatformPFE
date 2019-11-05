package services;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import entities.administration.Class;
import collection.CSV;
import interfaces.PfeFileOperationsRemote;

@Path("PfeFileOperations")
public class PfeFileOperationsResource {
	
	@EJB
	PfeFileOperationsRemote pfeFileOperationsBusiness;
	
	@Path("accepted")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAcceptedPfeFiles() {
		if (pfeFileOperationsBusiness.getAcceptedPfeFiles().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.getAcceptedPfeFiles(), MediaType.APPLICATION_JSON).build();
	}
	
	@Path("ungraded")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUngradedPfeFiles() {
		if (pfeFileOperationsBusiness.getUngradedPfeFiles().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.getUngradedPfeFiles(), MediaType.APPLICATION_JSON).build();
	}
	
	@Path("validate/categorie/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response validatePfeCategorie(@PathParam("id") int id) {
		if (pfeFileOperationsBusiness.validatePfeCategorie(id))
			return Response.status(Status.OK).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@Path("affect/prevalidator/{teacherId}/{pfeFileId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response affectPrevalidator(@PathParam("teacherId") int teacherId ,@PathParam("pfeFileId") int pfeFileId) {
		if (pfeFileOperationsBusiness.affectPrevalidator(teacherId,pfeFileId))
			return Response.status(Status.OK).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@Path("affect/affectRole/{teacherId}/{pfeFileId}/{role}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response affectTeacherRole(@PathParam("teacherId") int teacherId ,@PathParam("pfeFileId") int pfeFileId,@PathParam("role") int role) {
		if (pfeFileOperationsBusiness.affectTeacherRole(teacherId,pfeFileId,role))
			return Response.status(Status.OK).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	/*@POST
	@Path("/upload")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.)
	public Response uploadFile(String csvFile) throws JsonMappingException, IOException {
		
		Pattern pattern = Pattern.compile(",");
		try (BufferedReader in = new BufferedReader(new FileReader(csvFile));) {
			List<Class> classes = in
			        .lines()
			        .skip(1)
			        .map(line -> {
			                String[] x = pattern.split(line);
			                return new Class(x[0],x[1]);
			            })
			        .collect(Collectors.toList());
			ObjectMapper mapper = new ObjectMapper();
		    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    mapper.writeValue(System.out, classes);
		    return Response.status(Status.OK).build();
		}
		

	}*/
	@POST
    @Path("/stream")
    @Consumes(MediaType.TEXT_PLAIN)
    public void stream(String csvFile) throws CsvValidationException, FileNotFoundException, IOException {
		Map<String, String> values = new CSVReaderHeaderAware(new FileReader(csvFile)).readMap();
		System.out.println(values.toString());
    }
	
}
