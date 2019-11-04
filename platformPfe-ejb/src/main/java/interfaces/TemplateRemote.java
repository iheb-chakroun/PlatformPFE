package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.Template;


@Remote
public interface TemplateRemote {
	public void addTemplate(Template T);
	public void removeTemplate(int id);
	public void updateTemplate(int id,Template t) ;
	public Template findTemplateById(int id);
	public List<Template> findAllTemplate();
	public void affectSiteToTemplate(int siteId, int templateId);
	public void exportApi(String content);
}
