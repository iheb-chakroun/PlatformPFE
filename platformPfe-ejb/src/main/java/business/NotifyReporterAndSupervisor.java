package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import collection.Role;
import entities.documents.PfeFile;
import entities.tracking.HeadDepartementNotification;
import entities.tracking.TeacherNotification;
import entities.users.DepartementHead;
import entities.users.Teacher;
import entities.users.TeacherRole;
import interfaces.PfeFileLocal;

@Singleton
public class NotifyReporterAndSupervisor {

	@PersistenceContext
	EntityManager em;

	@EJB
	PfeFileLocal pfeFileBusiness;

	@Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
	public void atSchedule() throws InterruptedException {
		List<PfeFile> pfeFiles = pfeFileBusiness.getAllPfeFile();
		for (PfeFile pfeFile : pfeFiles) {
			if (pfeFile.isReportDeposite()) {
				if (pfeFile.getGradeReporter() == -1) {
					TeacherNotification tn = new TeacherNotification();
					tn.setContent("The report of " + pfeFile.getTitle()
							+ " has been deposit and its waiting for your grade.");
					tn.setDate(new Date(System.currentTimeMillis()));
					tn.setLink(
							"link to the page so he can consult the pfefile, this link we be generated once we have the angular routes");
					Teacher t = new Teacher();
					for (TeacherRole tr : pfeFile.getThesis().getTeacherRole()) {
						if (tr.getRole().compareTo(Role.RAPPORTEUR) == 0)
							t = tr.getTeacher();
					}
					tn.setTeacher(t);
					em.persist(tn);
				}
				if (pfeFile.getGradeSupervisor() == -1) {
					TeacherNotification tn = new TeacherNotification();
					tn.setContent("The report of " + pfeFile.getTitle()
							+ " has been deposit and its waiting for your grade.");
					tn.setDate(new Date(System.currentTimeMillis()));
					tn.setLink(
							"link to the page so he can consult the pfefile, this link we be generated once we have the angular routes");
					Teacher t = new Teacher();
					for (TeacherRole tr : pfeFile.getThesis().getTeacherRole()) {
						if (tr.getRole().compareTo(Role.SUPERVISOR) == 0)
							t = tr.getTeacher();
					}
					tn.setTeacher(t);
					em.persist(tn);
				}
			}

		}
		List<DepartementHead> heads = em.createQuery("select h from DepartementHead h", DepartementHead.class)
				.getResultList();
		List<PfeFile> pfeFilesWithNoPSR = new ArrayList<PfeFile>();
		String ids = "";
		for (DepartementHead head : heads) {
			for (PfeFile pfeFile : pfeFiles) {
				if (pfeFile.getStudent().getClasse().getOption().getDepartement() == head.getDepartement()) {
					if (!pfeFile.hasReporter() || !pfeFile.hasSupervisor() || !pfeFile.hasValidator()) {
						pfeFilesWithNoPSR.add(pfeFile);
						ids = ids + pfeFile.getId() + ";";
					}

				}
			}
			if (pfeFilesWithNoPSR.size() != 0) {
				HeadDepartementNotification tn = new HeadDepartementNotification();
				tn.setContent("List of pfe file waiting .......");
				tn.setDate(new Date(System.currentTimeMillis()));
				tn.setLink(
						"link to the page so he can consult the pfefile, this link we be generated once we have the angular routes "
								+ "?ids=" + ids);
				tn.setDepartementHead(head);
				em.persist(tn);
			}
		}
	}

}
