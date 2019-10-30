package business;

import java.util.Date;
import java.util.List;

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

@Stateless
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
	 * @author iheb
	 * Note : this method to plan a thesis
	 */
	@SuppressWarnings("unused")
	@Override
	public void planThesis() {
		PfeFileBuisness pfeFileBuisness = new PfeFileBuisness();
		ClassroomBusiness classroomBusiness = new ClassroomBusiness();
		TeacherBusiness teacherBusiness = new TeacherBusiness();

		List<PfeFile> pfeFilesReady = pfeFileBuisness.getAllReadyPfeFile();
		List<Classroom> availableClassrooms;
		List<Teacher> validTeachers;
		boolean supervisorAvailable = false;
		boolean rappoteurAvailable = false;

		for (PfeFile pfeFile : pfeFilesReady) {

			// TODO date how to generate to test
			Date date = new Date();

			for (TeacherRole teacherRole : pfeFile.getThesis().getTeacherRole()) {
				if ((teacherRole.getRole() == Role.SUPERVISOR) && (teacherRole.getTeacher().isAvailable(date))) {
					if ((teacherRole.getRole() == Role.RAPPORTEUR) && (teacherRole.getTeacher().isAvailable(date))) {
						supervisorAvailable = true;
						rappoteurAvailable = true;
					}
				}
			}

			validTeachers = teacherBusiness.getValidTeachers(pfeFile.getThesis(), date);
			if ((validTeachers.size() > 0) && (supervisorAvailable) && (rappoteurAvailable)) {
				availableClassrooms = classroomBusiness.getAvailableClassrooms(date);
				TeacherRole president = new TeacherRole();
				president.setRole(Role.PRESIDENT);
				president.setTeacher(validTeachers.get(0));
				president.setThesis(pfeFile.getThesis());
				validTeachers.get(0).getTeacherRole().add(president);
				pfeFile.getThesis().getTeacherRole().add(president);
				pfeFile.getThesis().setClassroom(availableClassrooms.get(0));
				pfeFile.getThesis().setDate(date);
				Schedule schedule = new Schedule();
				schedule.setAvailable(false);
				schedule.setClassroom(availableClassrooms.get(0));
				schedule.setDate(date);
				availableClassrooms.get(0).getSchedule().add(schedule);

				ArchivePfeFile archivePfeFile = new ArchivePfeFile();
				archivePfeFile.copyPfeFile(pfeFile);
				archivePfeFile.setEmmeteur("System");
				archivePfeFile.setEvent(
						"Thesis planned for " + date + " in the classroom " + availableClassrooms.get(0).getName());
				archivePfeFile.setDateOfEvent(new Date(System.currentTimeMillis()));
				// TODO test el persist and merge

				// TODO google calendar api
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

			}

		}

	}

}
