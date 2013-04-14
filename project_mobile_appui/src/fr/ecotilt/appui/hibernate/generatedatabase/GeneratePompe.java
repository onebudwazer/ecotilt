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
		Category cat = new Category("Pompe");
		HibernateUtil.saveToHibernate(session, cat);

		for (int i = 0; i < 50; i++) {
			ImageUtil instance = new ImageUtil("/fr/ecotilt/appui/rsc/2013.jpg");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil
					.saveToHibernate(session, new Pompe("Borne " + i, "Toulon",
							RandomNumber.getRandomInt(99999), new GeoCoord(
									RandomNumber.getRandomDouble(180.0),
									RandomNumber.getRandomDouble(180.0)), pe,
							new Category("Pompe")));
		}
		
		for (int i = 0; i < 30; i++) {
			ImageUtil instance = new ImageUtil("/fr/ecotilt/appui/rsc/2013.jpg");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil
					.saveToHibernate(session, new BorneElectrique("Borne " + i, "Toulon",
							RandomNumber.getRandomInt(99999), new GeoCoord(
									RandomNumber.getRandomDouble(180.0),
									RandomNumber.getRandomDouble(180.0)), pe,
							new Category("Pompe")));
		}
		
		for (int i = 0; i < 30; i++) {
			ImageUtil instance = new ImageUtil("/fr/ecotilt/appui/rsc/2013.jpg");
			byte[] bInstanceImg = instance.getImgToByteScalr();
			PictureEntity pe = new PictureEntity();
			pe.setImage(bInstanceImg);
			HibernateUtil
					.saveToHibernate(session, new Velib("Borne " + i, "Toulon",
							RandomNumber.getRandomInt(99999), new GeoCoord(
									RandomNumber.getRandomDouble(180.0),
									RandomNumber.getRandomDouble(180.0)), pe,
							new Category("Pompe")));
		}
		
		session.close();
	}

}

// Category categ = (Category)
// session.createCriteria(Category.class).add(Restrictions.like("categoryName",
// "Pompe")).uniqueResult();
// for (int i = 0; i < list.size(); i++) {
// addOcurance(session, list.get(i));
// }
//
// for (int i = 0; i < listStudent.size(); i++) {
// addOcurance(session, listStudent.get(i));
// }
// Transaction tx = session.beginTransaction();
// init vars
// List<BorneElectrique> list = new ArrayList<BorneElectrique>();
// List<Student> listStudent = new ArrayList<Student>();
// list.add(new BorneElectrique("Borne 1", 45, "Toulon", 83000, new
// Point2D.Double(45.01, -3.546)));
// list.add(new BorneElectrique("Borne 2", 45, "la seyne sur mer", 83000, new
// Point2D.Double(45.01, -3.546)));
// list.add(new BorneElectrique("Borne 3", 45, "six four", 83000, new
// Point2D.Double(45.01, -3.546)));
// list.add(new BorneElectrique("Borne 4", 45, "lol", 55000, new
// Point2D.Double(45.01, -3.546)));
// list.add(new BorneElectrique("Borne 5", 45, "Toulon", 21413, new
// Point2D.Double(45.01, -3.546)));
// list.add(new BorneElectrique("Borne 6", 45, "Toulon", 45545, new
// Point2D.Double(45.01, -3.546)));
// list.add(new BorneElectrique("Borne 7", 45, "Toulon", 56449, new
// Point2D.Double(45.01, -3.546)));
// list.add(new BorneElectrique("Borne 8", 45, "Toulon", 45644, new
// Point2D.Double(45.01, -3.546)));
// Address address1 = new Address("OMR Road", "Chennai", "TN", "600097");
// Address address2 = new Address("Ring Road", "Banglore", "Karnataka",
// "560000");
// Student student1 = new Student("Eswar", address1);
// Student student2 = new Student("Joe", address2);
// listStudent.add(student1);
// listStudent.add(student2);
// File file = new File("C:\\eclipse\\test.jpg");
// BufferedImage tif;
// try {
// tif = ImageIO.read(new File("test.tif"));
// ImageIO.write(tif, "png", new File("C:\\eclipse\\test.png"));
//
// } catch (IOException e1) {
// e1.printStackTrace();
// }
//
// byte[] bFile = new byte[(int) file.length()];

// try {
// FileInputStream fileInputStream = new FileInputStream(file);
// //convert file into array of bytes
// fileInputStream.read(bFile);
// fileInputStream.close();
// } catch (Exception e) {
// e.printStackTrace();
// }

// String pathRsc = "fr/projet/webservice/rsc";
// URL objFromRsc =
// DataBaseGenerator.class.getClassLoader().getResource(pathRsc);
//
// File imageFile = null;
// try {
// imageFile = new File(objFromRsc.toURI());
// } catch (URISyntaxException e) {
// e.printStackTrace();
// }

// for (int j = 0; j < bInstanceImg.length; j++) {
// System.out.println(bInstanceImg[j]);
// }