package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import entities.administration.Template;

@Local
public interface TemplateLocal {
	public void addTemplate(Template T);
	public void removeTemplate(int id);
	public void updateTemplate(int id,Template t) ;
	public Template findTemplateById(int id);
	public List<Template> findAllTemplate();
	public void affectSiteToTemplate(int siteId, int templateId);
	
}
