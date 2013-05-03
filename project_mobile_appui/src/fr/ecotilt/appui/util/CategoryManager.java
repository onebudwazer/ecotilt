package fr.ecotilt.appui.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.AGenericObject;
import fr.ecotilt.appui.model.BorneElectrique;
import fr.ecotilt.appui.model.Category;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.model.Velib;

public class CategoryManager {

	private CategoryManager() {
	}

	private static class LazySingleton {
		private static CategoryManager	instance	= new CategoryManager();
	}

	public static CategoryManager getInstance() {
		return LazySingleton.instance;
	}

	public Category addCategory(Session session, String nameOfCategory) {
		Category instance = null;

		@SuppressWarnings("unchecked")
		List<Category> list = session.createCriteria(Category.class)
				.add(Restrictions.like("categoryName", nameOfCategory)).list();

		if (list.size() == 0) {
			Category nouvelleCategorie = new Category(nameOfCategory, "");
			HibernateUtil.saveToHibernate(session, nouvelleCategorie);
			instance = nouvelleCategorie;
		} else {
			for (Category category : list) {
				if (category.getCategoryName().equals(nameOfCategory)) {
					instance = category;
				}
			}
		}

		return instance;
	}

	public int removeCategory(Session session, String nameOfCategory) {

		Criteria queryCriteria = session.createCriteria(Category.class);
		queryCriteria.add(Restrictions.like("categoryName", nameOfCategory));
		@SuppressWarnings("unchecked")
		List<Category> list = queryCriteria.list();

		if (list.size() != 0) {
			for (Category category : list) {
				@SuppressWarnings("unchecked")
				List<AGenericObject> allObject = session
						.createCriteria(AGenericObject.class)
						.add(Restrictions.like("category", category)).list();
				for (AGenericObject aGenericObject : allObject) {
					//remove all object associate to cat
					
					if (aGenericObject instanceof BorneElectrique) {
						BorneElectrique instBe = (BorneElectrique) aGenericObject;
						HibernateUtil.deleteToHibernate(session, instBe);
					}
					
					if (aGenericObject instanceof Velib) {
						Velib instVelib = (Velib) aGenericObject;
						HibernateUtil.deleteToHibernate(session, instVelib);
					}
					
					if (aGenericObject instanceof Pompe) {
						Pompe instPompe = (Pompe) aGenericObject;
						HibernateUtil.deleteToHibernate(session, instPompe);
					}
				}
				//remove cat
				HibernateUtil.deleteToHibernate(session, category);
			}
			return 1;
		}
		
		return 0;
	}

}
