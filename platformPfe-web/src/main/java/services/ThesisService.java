package services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import business.ThesisBuisness;
import entities.documents.PfeFile;
import interfaces.PfeFileLocal;

@Path("plan")
public class ThesisService {

	@EJB
	ThesisBuisness thesisBusiness;

	@EJB
	PfeFileLocal pfb;

	@GET
	public void plan() {
		System.out.println("start planing ....... ");
		System.out.println("calling method planThesis().......");
		PfeFile pfeFile = pfb.findPfeById(1);
		System.out.println("Found the pfe file : " + pfeFile.getTitle() + "........");
		System.out.println(pfeFile.isReady());
		if(pfeFile.isReady()) {
			System.out.println("file is ready");
			thesisBusiness.plan(pfeFile);
		}
		System.out.println("end planing ....... ");

	}

}
