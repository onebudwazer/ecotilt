package fr.ecotilt.appui.hibernate.generatedatabase;

import java.net.URISyntaxException;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.Pompe;

public class DeleteDataBase {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws URISyntaxException {
		// call hibernate
		// Session session = HibernateUtil.getSessionFactory().openSession();
		// Pompe pompe = (Pompe)
		// session.createCriteria(Pompe.class).list().get(0);
		// HibernateUtil.deleteToHibernate(session, pompe);
		// session.close();
		//

		Session session = HibernateUtil.getSessionFactory().openSession();
		// Pompe cat = (Pompe)
		// session.createCriteria(Pompe.class).list().get(0);
		// HibernateUtil.deleteToHibernate(session, cat);

		// Pompe pompe = (Pompe)
		// session.createCriteria(Pompe.class).list().get(0);
		// pompe.getGeoCoord().setLatitude(10.10);
		// pompe.getGeoCoord().setLongitude(10.18);
		// HibernateUtil.updateToHibernate(session, pompe);

		// HibernateCommander.deleteCategory(session, "Pompe");
		// Pompe pompe = (Pompe)
		// session.createCriteria(Pompe.class).list().get(0);
		// List sql =
		// session.createQuery("from Pompe inner join Category  on CATEGORY_ID = CATEGORY_ID").list();

		List<Pompe> sql = session.createCriteria(Pompe.class)
				.setFetchMode("Category", FetchMode.JOIN).list();

		for (int i = 0; i < sql.size(); i++) {
			Pompe instance = sql.get(i);
			System.out.println("test"
					+ instance.getCategory().getCategoryName());
		}

		// Criteria criteria = session.createCriteria(Pompe.class);
		// criteria.add(Restrictions.eq("fo.folderType", folderType));
		// criteria.list();
		// List<Category> claz =
		// session.createCriteria(Category.class).setProjection(Projections.distinct(Projections.property("categoryName"))).list();
		// for (int i = 0; i < claz.size(); i++) {
		// System.out.println(claz.get(i));
		// }

		session.close();
	}

}
