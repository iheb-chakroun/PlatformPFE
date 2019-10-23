package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Template;

@Local
public interface TemplateLocal {
	public void addTemplate(Template T);
	public void removeTemplate(int id);
	public void updateTemplate(Template e);
	public Template findTemplateById(int id);
	public List<Template> findAllTemplate();
	public void affectSiteToTemplate(int siteId, int templateId);
}
