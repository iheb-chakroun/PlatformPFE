package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.tracking.StudentNotification;
import entities.tracking.TeacherNotification;
import entities.users.Student;
import entities.users.Teacher;
import interfaces.HistoryRemote;

@Stateless
public class HistoryBusiness implements HistoryRemote {

	@PersistenceContext(unitName="platform")
	EntityManager em;
	
	@Override
	public List<StudentNotification> getAllStudentNotifications(){
		List<StudentNotification> studentNotifications = em.createQuery("select s from StudentNotification s", StudentNotification.class).getResultList();
		return studentNotifications;
	}
	
	@Override
	public List<TeacherNotification> getAllTeacherNotifications(){
		List<TeacherNotification> teacherNotifications = em.createQuery("select t from TeacherNotification t", TeacherNotification.class).getResultList();
		return teacherNotifications;
	}
	
	@Override
	public List<StudentNotification> findAllNotificationsByStudent(int studentId){
		Student student = em.find(Student.class, studentId);
		List<StudentNotification> snl = new ArrayList<>();
		for (StudentNotification sn : student.getNotifications() ) {
			snl.add(sn);
		}
		return snl;
	}
	
	
	@Override
	public List<TeacherNotification> findAllNotificationsByTeacher(int teacherId){
		Teacher teacher = em.find(Teacher.class, teacherId);
		List<TeacherNotification> tnl = new ArrayList<>();
		for (TeacherNotification tn : teacher.getTeacherNotification() ) {
			tnl.add(tn);
		}
		return tnl;
	}
	
}
