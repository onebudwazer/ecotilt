package fr.ecotilt.appui.hibernate.conf;

import java.util.List;

import org.hibernate.Session;

import fr.ecotilt.appui.model.Category;
import fr.ecotilt.appui.model.Pompe;

public class HibernateCommander {

	public static void deleteCategory(Session session, String valueCat) {
		@SuppressWarnings("unchecked")
		List<Pompe> listPompe = (List<Pompe>) session.createCriteria(Pompe.class).list();
		for (int i = 0; i < listPompe.size(); i++) {
			if (listPompe.get(i).getCategory().getCategoryName().contains(valueCat)) {
				System.out.println("suppression");
				Pompe instancePompe = listPompe.get(i);
				HibernateUtil.deleteToHibernate(session, instancePompe);
				
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Category> listCategory = (List<Category>) session.createCriteria(Category.class).list();
		for (int i = 0; i < listCategory.size(); i++) {
			Category instanceCategory = listCategory.get(i);
			HibernateUtil.deleteToHibernate(session, instanceCategory);
		}
	}
	
	
	
	
	
	
}
