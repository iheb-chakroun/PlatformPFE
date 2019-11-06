package business;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors.*;
import java.util.Map.Entry.*;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import collection.Role;
import entities.administration.Site;
import entities.documents.Categorie;
import entities.documents.PfeFile;
import entities.documents.Thesis;
import entities.tracking.ArchivePfeFile;
import entities.tracking.StudentNotification;
import entities.users.Teacher;
import entities.users.TeacherRole;
import interfaces.PfeFileOperationsRemote;

@Stateless
public class PfeFilOperationsBusiness implements PfeFileOperationsRemote {

	@PersistenceContext(unitName="platform")
	EntityManager em;
	
	@Override
	public List<ArchivePfeFile> searchPfeFileArchive(int pfeFileId,String date,String event,String emeteur,Boolean status){
		
		

		try {
			if (!date.equals(null)) {
				TypedQuery<ArchivePfeFile> query = em.createQuery("select p from ArchivePfeFile p where pfeFile_id =:pfeFileId AND emmeteur LIKE :emmeteur AND event like :event AND status = :status AND date = :date",ArchivePfeFile.class);
				query.setParameter("pfeFileId", pfeFileId);
				query.setParameter("emmeteur", "%"+emeteur+"%");
				query.setParameter("event", "%"+event+"%");
				query.setParameter("status", status);
				java.util.Date d1;
				java.sql.Date d2;
				d1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				d2 = new java.sql.Date(d1.getTime());
				query.setParameter("date", d2);
				return query.getResultList();
			}
			
		} catch (ParseException e) {
			System.out.println(e);

		}  
		TypedQuery<ArchivePfeFile> query = em.createQuery("select p from ArchivePfeFile p where pfeFile_id =:pfeFileId AND emmeteur LIKE :emmeteur AND event like :event AND status = :status",ArchivePfeFile.class);
		query.setParameter("pfeFileId", pfeFileId);
		query.setParameter("emmeteur", "%"+emeteur+"%");
		query.setParameter("event", "%"+event+"%");
		query.setParameter("status", status);
		return query.getResultList();
	}
	
	@Override
	public List<ArchivePfeFile> showPfeFileArchive(int pfeFileId) {
		PfeFile pfeFile = em.find(PfeFile.class, pfeFileId);
		return pfeFile.getArchivePfeFile();
	}
	
	
	@Override
	public List<PfeFile> getAcceptedPfeFiles() {
		List<PfeFile> acceptedPfeFiles = em.createQuery("select p from PfeFile p where status = 1",PfeFile.class).getResultList();
		return acceptedPfeFiles;
	}
	
	@Override
	public List<PfeFile> WaitingForSupervisorAffect(){
		List<PfeFile> pfeFiles = em.createQuery("select p from PfeFile p",PfeFile.class).getResultList();
		List<PfeFile> pfs = new ArrayList<>();
		Role r = Role.SUPERVISOR;
		for (PfeFile pf : pfeFiles ) {
			for (TeacherRole tr : pf.getThesis().getTeacherRole()) {
				if (tr.getRole() != r) {
					pfs.add(tr.getThesis().getPfeFile());
				}
			}
		}
		return pfs;
	}
	
	@Override
	public List<PfeFile> WaitingForRaporteurAffect(){
		List<PfeFile> pfeFiles = em.createQuery("select p from PfeFile p",PfeFile.class).getResultList();
		List<PfeFile> pfs = new ArrayList<>();
		Role r = Role.RAPPORTEUR;
		for (PfeFile pf : pfeFiles ) {
			for (TeacherRole tr : pf.getThesis().getTeacherRole()) {
				if (tr.getRole() != r) {
					pfs.add(tr.getThesis().getPfeFile());
				}
			}
		}
		return pfs;
	}
	
	@Override
	public List<PfeFile> getUngradedPfeFiles() {
		List<PfeFile> ungradedPfeFiles = em.createQuery("select p from PfeFile p where gradeReporter = 0 or gradeSupervisor = 0 ",PfeFile.class).getResultList();
		return ungradedPfeFiles;
	}
	
	@Override
	public boolean validatePfeCategorie(int categorieId){
		Query query = em.createQuery("update Categorie c set status=1 where c.id=:categorieId");
		query.setParameter("categorieId", categorieId);
				try {
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean affectPrevalidator(int teacherId, int pfeFileId) {
		Teacher  teacherManagedEntity = em.find(Teacher.class, teacherId);
		PfeFile pfeFileManagedEntity = em.find(PfeFile.class, pfeFileId);
		Site site = em.find(Site.class, 1);
		List<PfeFile> pfs = em.createQuery("select p from PfeFile p", PfeFile.class).getResultList();
		int s = 0;
		for (PfeFile pf : pfs) {
			if (pf.getPre_validator() != null) {
				if (pf.getPre_validator().getId() == teacherId) {
					s++;
				}
				
				
			}
		}
		try {
			if (s==site.getNbrMaxAction()) {
				pfeFileManagedEntity.setPre_validator(teacherManagedEntity);
				em.merge(pfeFileManagedEntity);
				return true;
			}
			else
				return false;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@Override
	public boolean affectTeacherRole(int teacherId, int pfeFileId, int role) {
		
		Role r;
		
		if (role == 1 ) {
			r = Role.RAPPORTEUR;
		}
		
		else if (role == 2 ) {
			r = Role.PRESIDENT;
		}
		else {
			r = Role.SUPERVISOR;
		}
		
		
		try {
			
			
			Teacher  teacherManagedEntity = em.find(Teacher.class, teacherId);
			PfeFile pfeFileManagedEntity = em.find(PfeFile.class, pfeFileId);
			TeacherRole teacherRoleManagedEntity = new TeacherRole();
			Thesis thesis = pfeFileManagedEntity.getThesis();
			List<TeacherRole> teacherRoles = em.createQuery("select p from TeacherRole p",TeacherRole.class).getResultList();
			Site site = em.find(Site.class, 1);
			int s = 0;
			for (TeacherRole tr : teacherRoles ) {
				if (tr.getTeacher().getId() == teacherId && tr.getRole() == r) {
					s++;
					if (s==site.getNbrMaxAction()) {
						teacherRoleManagedEntity.setRole(r);
						teacherRoleManagedEntity.setTeacher(teacherManagedEntity);
						teacherRoleManagedEntity.setThesis(thesis);
						em.persist(teacherRoleManagedEntity);
						return true;
					}
				}
			}
			return true;
				
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@Override
	public Set<Teacher> teachersByCategory(int cid){
		Categorie c = em.find(Categorie.class, cid);
		return  c.getTeachers();
	}
        
	@Override
	public HashMap<Integer, Integer> SortedTeacher(){
		List<Teacher> teachers = em.createQuery("select t from Teacher t ",Teacher.class).getResultList();
		Map<Integer, Integer> tmap=new HashMap();
		
		for (Teacher t : teachers) {
			tmap.put(t.getId(), 0);
			for (TeacherRole tr : t.getTeacherRole()) {
				if (tr.getRole() == Role.SUPERVISOR) {
					//tmap.put(t.getId(), (int) tmap.get(t) + 1);
					
					tmap.replace(t.getId(), tmap.get(t.getId()) + 1);
				}
			}
		}
		
		List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(tmap.entrySet());
		
				Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
		            public int compare(Map.Entry<Integer, Integer> o1,
		                               Map.Entry<Integer, Integer> o2) {
		                return (o1.getValue()).compareTo(o2.getValue());
		            }
		        });

				Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
		        for (Map.Entry<Integer, Integer> entry : list) {
		            sortedMap.put(entry.getKey(), entry.getValue());
		        }
		return  (HashMap<Integer, Integer>) tmap;
	}
	
	
	
}
