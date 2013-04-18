package fr.ecotilt.appui.hibernate.generatedatabase;

import java.net.URISyntaxException;

import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.BorneElectrique;
import fr.ecotilt.appui.model.Category;
import fr.ecotilt.appui.model.GeoCoord;
import fr.ecotilt.appui.model.PictureEntity;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.model.Velib;
import fr.ecotilt.appui.util.ImageUtil;
import fr.ecotilt.appui.util.RandomNumber;

public class GeneratePompe {

	public static void main(String[] args) throws URISyntaxException {
		// call hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();
		generateBorne(session);
		generatePompe(session);
		generateVelib(session);
		session.close();
	}

	private static void generateBorne(Session session) {
		for (int i = 0; i < 10; i++) {
			ImageUtil instance = new ImageUtil("/fr/ecotilt/appui/rsc/2013.jpg");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil
					.saveToHibernate(session, new Pompe("Borne " + i, "Toulon",
							RandomNumber.getRandomInt(99999), new GeoCoord(
									RandomNumber.randomAreaMarseilleLatitude(),
									RandomNumber.randomAreaMarseilleLongitude()), pe,
							new Category("Pompe")));
		}
	}

	private static void generatePompe(Session session) {
		for (int i = 0; i < 10; i++) {
			ImageUtil instance = new ImageUtil("/fr/ecotilt/appui/rsc/2013.jpg");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil.saveToHibernate(session, new BorneElectrique("Pompe "
					+ i, "Toulon", RandomNumber.getRandomInt(99999),
					new GeoCoord(RandomNumber.randomAreaMarseilleLatitude(),
							RandomNumber.randomAreaMarseilleLongitude()), pe,
					new Category("Pompe")));
		}
	}

	private static void generateVelib(Session session) {
		for (int i = 0; i < 10; i++) {
			ImageUtil instance = new ImageUtil("/fr/ecotilt/appui/rsc/2013.jpg");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil.saveToHibernate(session, new Velib("Velib " + i,
					"Toulon", RandomNumber.getRandomInt(99999), new GeoCoord(
							RandomNumber.randomAreaMarseilleLatitude(),
							RandomNumber.randomAreaMarseilleLongitude()), pe,
					new Category("Pompe")));
		}
	}

}

// session.createCriteria(Category.class).add(Restrictions.like("categoryName",
// "Pompe")).uniqueResult();
// for (int i = 0; i < list.size(); i++) {
// Transaction tx = session.beginTransaction();
// List<BorneElectrique> list = new ArrayList<BorneElectrique>();
// List<Student> listStudent = new ArrayList<Student>();

