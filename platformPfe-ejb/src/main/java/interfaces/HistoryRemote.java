package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.tracking.StudentNotification;
import entities.tracking.TeacherNotification;

@Remote
public interface HistoryRemote {

	public List<StudentNotification> getAllStudentNotifications();
	
	public List<TeacherNotification> getAllTeacherNotifications();
	
	public List<StudentNotification> findAllNotificationsByStudent(int studentId);
	
	public List<TeacherNotification> findAllNotificationsByTeacher(int teacherId);
}
