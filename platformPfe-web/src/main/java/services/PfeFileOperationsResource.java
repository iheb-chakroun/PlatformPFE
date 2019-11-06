package services;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import collection.CSV;
import interfaces.PfeFileOperationsRemote;

@Path("PfeFileOperations")
public class PfeFileOperationsResource {
	
	@EJB
	PfeFileOperationsRemote pfeFileOperationsBusiness;
	
	
	@Path("archive/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response showPfeArchive(@PathParam("id") int pfeFileId) {
		if (pfeFileOperationsBusiness.showPfeFileArchive(pfeFileId).size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.showPfeFileArchive(pfeFileId), MediaType.APPLICATION_JSON).build();
	}
	
	@Path("archive/search/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response SearchPfeArchive(@PathParam("id") int pfeFileId,@QueryParam("date") String date,@QueryParam("event") String event,@QueryParam("emeteur") String emeteur,@QueryParam("status") Boolean status) {
		if (pfeFileOperationsBusiness.searchPfeFileArchive(pfeFileId,date,event,emeteur,status).size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.searchPfeFileArchive(pfeFileId,date,event,emeteur,status), MediaType.APPLICATION_JSON).build();
	}
	
	@Path("accepted")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAcceptedPfeFiles() {
		if (pfeFileOperationsBusiness.getAcceptedPfeFiles().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.getAcceptedPfeFiles(), MediaType.APPLICATION_JSON).build();
	}
	
	@Path("unsupervised")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response WaitingForSupervisorAffect() {
		if (pfeFileOperationsBusiness.WaitingForSupervisorAffect().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.WaitingForSupervisorAffect(), MediaType.APPLICATION_JSON).build();
	}
	
	@Path("noraporteur")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response WaitingForRaporteurAffect() {
		if (pfeFileOperationsBusiness.WaitingForRaporteurAffect().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.WaitingForRaporteurAffect(), MediaType.APPLICATION_JSON).build();
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
	
	
	@Path("categoryteacher/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response teachersByCategory(@PathParam("id") int cid) {
		if (pfeFileOperationsBusiness.teachersByCategory(cid).size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.teachersByCategory(cid), MediaType.APPLICATION_JSON).build();
	}
	
	@Path("Sortedteacher")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response SortedTeacher() {
		if (pfeFileOperationsBusiness.SortedTeacher().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).build();
		else
			return Response.ok(pfeFileOperationsBusiness.SortedTeacher(), MediaType.APPLICATION_JSON).build();
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
	@GET
    @Path("/stream")
	@Produces(MediaType.APPLICATION_JSON)
    public Response stream(@QueryParam("file") String csvFile) throws CsvValidationException, FileNotFoundException, IOException {
		
		
		try (InputStream in = new FileInputStream(csvFile);) {
		    CSV csv = new CSV(true, ',', in );
		    List< String > fieldNames = null;
		    if (csv.hasNext()) fieldNames = new ArrayList < > (csv.next());
		    List < Map < String, String >> list = new ArrayList < > ();
		    while (csv.hasNext()) {
		        List < String > x = csv.next();
		        Map < String, String > obj = new LinkedHashMap < > ();
		        for (int i = 0; i < fieldNames.size(); i++) {
		            obj.put(fieldNames.get(i), x.get(i));
		        }
		        list.add(obj);
		    }
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    mapper.writeValue(System.out, list);
		    return Response.ok(list, MediaType.APPLICATION_JSON).build();
		}
		
		
		
		/*Map<String, String> values = new CSVReaderHeaderAware(new FileReader(csvFile)).readMap();
		System.out.println(values.toString());
		return Response.ok(values, MediaType.APPLICATION_JSON).build();*/
    }
	
}
