package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.users.Student;
import interfaces.StudentRemote;

@Path("student")
public class StudentResource {
	
	@EJB
	StudentRemote studentBusiness;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student ajouterEmploye(Student student) {
		studentBusiness.addStudent(student);
		return student;
	}
	
	@GET
	@Path("/one")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student findStudent(Student student) {
		return studentBusiness.findStudentById(student.getId());
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> findAllStudents() {
		return studentBusiness.findAllStudents();
	}
	

}
