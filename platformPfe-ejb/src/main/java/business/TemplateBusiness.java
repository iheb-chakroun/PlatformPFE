package business;

import java.awt.print.Pageable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.w3c.dom.Document;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import entities.administration.Site;
import entities.administration.Template;
import interfaces.TemplateLocal;
import interfaces.TemplateRemote;


@Stateless
@LocalBean
public class TemplateBusiness implements TemplateLocal,TemplateRemote{
	@PersistenceContext(unitName="platform") 
	EntityManager em;
	
	

	@Override
	public void removeTemplate(int id) {
		System.out.println("In remove template: ");
		em.remove(em.find(Template.class, id));
		
	}


	@Override
	public Template findTemplateById(int id) {
		Template template= em.find(Template.class,id);
		System.out.println(template);
		return template;
	
	}

	@Override
	public List<Template> findAllTemplate() {
		List<Template>template=em.createQuery("Select e from  Template e",Template.class).getResultList();
		return template;
	}

	@Override
	public void affectSiteToTemplate(int siteId, int templateId) {
		Site siteManagedEntity = em.find(Site.class, siteId);
		Template templateManagedEntity = em.find(Template.class, templateId);
		templateManagedEntity.setSite(siteManagedEntity);
		
	}

	@Override
	public void addTemplate(Template T) {
		System.out.println("add template");
		em.persist(T);
		
	}


	@Override
	public void updateTemplate(int id, Template temp) {
		Template t=em.find(Template.class, id);
		t.setContent(temp.getContent());
		t.setTemplateType(temp.getTemplateType());
		em.merge(t);
		
	}



	


	@Override
	public void exportApi(String path) {
		
			
			File htmlSource = new File(path);
	        File pdfDest = new File("C:\\Users\\dorsa\\OneDrive\\Bureau\\output.pdf");
	         // pdfHTML specific code
	        ConverterProperties converterProperties = new ConverterProperties();
	        try {
				HtmlConverter.convertToPdf(new FileInputStream(htmlSource),new FileOutputStream(pdfDest), converterProperties);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}





	
}
