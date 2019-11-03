package business;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import collection.Role;
import entities.administration.Classroom;
import entities.administration.Schedule;
import entities.documents.PfeFile;
import entities.documents.Thesis;
import entities.tracking.ArchivePfeFile;
import entities.users.Teacher;
import entities.users.TeacherRole;
import interfaces.ThesisLocal;
import utilities.GoogleCalendar;

@Stateless
@LocalBean
public class ThesisBuisness implements ThesisLocal {
	@PersistenceContext(unitName = "platform")
	EntityManager em;

	@Override
	public Thesis findThesisById(int id) {

		Thesis a = em.find(Thesis.class, id);

		return a;
	}

	@Override
	public boolean deleteThesisById(int ThesisId) {
		Thesis emp = em.find(Thesis.class, ThesisId);

		try {
			em.remove(emp);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void ajouterThesis(Thesis p) {
		em.persist(p);
	}

	@Override
	public List<Thesis> getAllThesis() {
		List<Thesis> thesis = em.createQuery("select e from Thesis e", Thesis.class).getResultList();
		return thesis;
	}

	@Override
	public boolean updateThesis(Thesis p) {

		try {
			em.merge(p);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @author iheb Note : this method to plan a thesis
	 * @throws ParseException
	 */
	public boolean doPlan(PfeFile pfeFile, Date date) throws ParseException {
		boolean supervisorAvailable = false;
		boolean rappoteurAvailable = false;
		boolean done = false;
		// to check if the supervisor and reporter of the pfefile are available in the
		// given date
		for (TeacherRole teacherRole : pfeFile.getThesis().getTeacherRole()) {
			if ((teacherRole.getRole().equals(Role.SUPERVISOR)) && (teacherRole.getTeacher().isAvailable(date))) {
				System.out.println("Supervisor is available");
				supervisorAvailable = true;
			}
			if ((teacherRole.getRole().equals(Role.RAPPORTEUR)) && (teacherRole.getTeacher().isAvailable(date))) {
				System.out.println("Reporter is available");
				rappoteurAvailable = true;
			}

		}
		if (supervisorAvailable == false) {
			System.out.println("supervisor is not available");
		}
		if (rappoteurAvailable == false) {
			System.out.println("reporter is not available");
		}

		/**
		 * to get all the available teachers in a date who are not either a supervisor
		 * or reporter in the thesis
		 */
		List<Teacher> teachers = em.createQuery("select t from Teacher t", Teacher.class).getResultList();
		List<Teacher> validTeachers = new ArrayList<Teacher>();
		if (teachers.size() > 0) {
			for (Teacher teacher : teachers) {
				if (teacher.isValid(pfeFile.getThesis(), date))
					validTeachers.add(teacher);
			}
		}

		if (validTeachers.size() == 0) {
			System.out.println("no available teachers");
		} else {
			System.out.println("there are some available teachers");
		}

		List<Classroom> classrooms = em.createQuery("select c from Classroom c", Classroom.class).getResultList();
		List<Classroom> availableClassrooms = new ArrayList<Classroom>();
		for (Classroom classroom : classrooms) {
			if (classroom.isAvailable(date, pfeFile.getStudent().getClasse().getOption().getDepartement())) {
				availableClassrooms.add(classroom);
			}
		}

		if (availableClassrooms.size() == 0) {
			System.out.println("no available classroom");
		} else {
			System.out.println("there are some available classroom");
		}

		/**
		 * if there is a teacher who can be a president in that date if the supervisor
		 * is available in that date if the reporter is available in that date if there
		 * is an available classroom in that date president must be from the same site
		 * and the same dep and school
		 */
		if ((validTeachers.size() > 0) && (supervisorAvailable) && (rappoteurAvailable)
				&& (availableClassrooms.size() > 0)) {
			System.out.println("starting the shit");
			// start archivage
			ArchivePfeFile archivePfeFile = new ArchivePfeFile();
			archivePfeFile.copyPfeFile(pfeFile);
			archivePfeFile.setEmmeteur("System");
			archivePfeFile.setEvent(
					"Thesis planned for " + date + " in the classroom " + availableClassrooms.get(0).getName());
			archivePfeFile.setDateOfEvent(new Date(System.currentTimeMillis()));
			System.out.println("persisting archive start");
			em.persist(archivePfeFile);
			System.out.println("persisting archive end");
			// end archivage

			// start adding role president
			System.out.println("persisting teacher role president start");
			TeacherRole president = new TeacherRole();
			president.setRole(Role.PRESIDENT);
			president.setTeacher(validTeachers.get(0));
			president.setThesis(pfeFile.getThesis());
			pfeFile.getThesis().getTeacherRole().add(president);
			em.persist(president);
			System.out.println("persisting teacher role president end");
			// end adding role president

			// start schedule for the classroom
			System.out.println("persisting schedule start");
			Schedule schedule = new Schedule();
			schedule.setAvailable(false);
			schedule.setClassroom(availableClassrooms.get(0));
			schedule.setDate(date);
			em.persist(schedule);
			System.out.println("persisting schedule end");
			// end schedule for the classroom

			System.out.println("merging pfeFile start");
			pfeFile.getThesis().setClassroom(availableClassrooms.get(0));
			pfeFile.getThesis().setDate(date);
			em.merge(pfeFile);
			System.out.println("merging pfeFile end");

			done = true;

			String emailStudent = pfeFile.getStudent().getEmail();
			String emailEntrepriseSupervisor = pfeFile.getEntreprise().getEmailEncadrent();
			String emailSchoolSupervisor = "";
			String emailReporter = "";
			String emailPresident = "";
			for (TeacherRole teacherRole : pfeFile.getThesis().getTeacherRole()) {
				if (teacherRole.getRole() == Role.PRESIDENT) {
					emailPresident = teacherRole.getTeacher().getEmail();
				}
				if (teacherRole.getRole() == Role.RAPPORTEUR) {
					emailReporter = teacherRole.getTeacher().getEmail();
				}
				if (teacherRole.getRole() == Role.SUPERVISOR) {
					emailSchoolSupervisor = teacherRole.getTeacher().getEmail();
				}
			}

			try {
				GoogleCalendar.getInstance().sendEvent(pfeFile, date, this.addHours(date, 1), emailStudent,
						emailEntrepriseSupervisor, emailSchoolSupervisor, emailReporter, emailPresident);
			} catch (Exception e) {
				System.err.println("google calender exception");
				System.out.println(e.getMessage());
			}

		}

		return done;

	}

	@SuppressWarnings("deprecation")
	@Override
	public void plan(PfeFile pfeFile) {

		boolean hasPresident = false;

		for (TeacherRole tr : pfeFile.getThesis().getTeacherRole()) {
			if (tr.getRole().compareTo(Role.PRESIDENT) == 0) {
				hasPresident = true;
				System.out.println("has president");
			}
		}

		if (!hasPresident) {

			Date startDate = pfeFile.getStudent().getClasse().getOption().getDepartement().getSite()
					.getDateOfSessionStarts();
			Date endDate = pfeFile.getStudent().getClasse().getOption().getDepartement().getSite()
					.getDateOfSessionEnds();
			boolean ended = false;
			List<Integer> workHours = new ArrayList<Integer>();
			workHours.add(8);
			workHours.add(9);
			workHours.add(10);
			workHours.add(11);
			workHours.add(14);
			workHours.add(15);
			workHours.add(16);
			workHours.add(17);
			while ((startDate.before(endDate)) && (ended == false)) {
				if ((this.getDayNumber(startDate) != 1) && (this.getDayNumber(startDate) != 7)) {
					if (workHours.indexOf(startDate.getHours()) != -1) {
						try {

							ended = this.doPlan(pfeFile, startDate);

						} catch (ParseException e) {
							System.out.println("error while planning .....");

						}
					}
				}

				startDate = this.addHours(startDate, 1);

			}
		}
	}

	public Date addHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}

	public int getDayNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
}
