package fr.ecotilt.appui.hibernate.generatedatabase;

import java.net.URISyntaxException;

import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.BorneElectrique;
import fr.ecotilt.appui.model.GeoCoord;
import fr.ecotilt.appui.model.PictureEntity;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.model.Velib;
import fr.ecotilt.appui.util.CategoryManager;
import fr.ecotilt.appui.util.ImageUtil;
import fr.ecotilt.appui.util.RandomNumber;

public class GeneratePoint {

	public static void main(String[] args) throws URISyntaxException {
		// call hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();
		// generateBorne(session);
		// generateVelib(session);
		// Category instance =
		// CategoryManager.getInstance().addCategory(session,
		// "BorneElectrique");
		// @SuppressWarnings("unchecked")
		// List<BorneElectrique> list =
		// session.createCriteria(BorneElectrique.class).list();
		// for (BorneElectrique borneElectrique : list) {
		// borneElectrique.setCategory(instance);
		// HibernateUtil.updateToHibernate(session, borneElectrique);
		// }

		System.out.println("STOP");

		// CategoryManager.getInstance().addCategory(session, "3");
		// CategoryManager.getInstance().addCategory(session, "1");
		// Category instance =
		// CategoryManager.getInstance().addCategory(session,
		// "BorneElectrique");
		int value = CategoryManager.getInstance().removeCategory(session, "1");
		// generateBorneElectrique(session);
		System.out.println(value);
		// CategoryManager.getInstance().removeCategory(session,
		// "BorneElectrique");
		// System.out.println(instance.getCategoryName());
		session.close();
	}

	public static void generateBorne(Session session) {
		for (int i = 0; i < 100; i++) {
			ImageUtil instance = new ImageUtil("300x300.png");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil.saveToHibernate(session, new Pompe("Borne " + i,
					"Toulon", RandomNumber.getRandomInt(99999), new GeoCoord(
							RandomNumber.randomAreaMarseilleLatitude(),
							RandomNumber.randomAreaMarseilleLongitude()), pe));
		}

	}

	public static void generateBorneElectrique(Session session) {
		for (int i = 0; i < 10; i++) {
			ImageUtil instance = new ImageUtil("2013.jpg");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil.saveToHibernate(session, new BorneElectrique("Pompe "
					+ i, "Toulon", RandomNumber.getRandomInt(99999),
					new GeoCoord(RandomNumber.randomAreaMarseilleLatitude(),
							RandomNumber.randomAreaMarseilleLongitude()), pe));
		}
	}

	public static void generateVelib(Session session) {
		for (int i = 0; i < 10; i++) {
			ImageUtil instance = new ImageUtil("2013.jpg");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil.saveToHibernate(session, new Velib("Velib " + i,
					"Toulon", RandomNumber.getRandomInt(99999), new GeoCoord(
							RandomNumber.randomAreaMarseilleLatitude(),
							RandomNumber.randomAreaMarseilleLongitude()), pe));
		}
	}

}

// session.createCriteria(Category.class).add(Restrictions.like("categoryName",
// "Pompe")).uniqueResult();
// for (int i = 0; i < list.size(); i++) {
// Transaction tx = session.beginTransaction();
// List<BorneElectrique> list = new ArrayList<BorneElectrique>();
// List<Student> listStudent = new ArrayList<Student>();

