package fr.ecotilt.appui.hibernate.generatedatabase;

import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.User;
import fr.ecotilt.appui.util.UserUtil;

/**
 * Class qui permet de generer les utilisateurs
 * @author Philippe
 *
 */
public class GenerateUser {

	public static void main(String[] args) {
		
		//on cree des instances beans Users
		User instanceTest = new User("test", UserUtil.getInstance().hashPassword("test").toString());
		User instanceMichele = new User("michel", UserUtil.getInstance().hashPassword("michel").toString());
		
		
		//on hiberne open -> hiberne -> close
		Session session = HibernateUtil.getSessionFactory().openSession();
		HibernateUtil.saveToHibernate(session, instanceTest);
		HibernateUtil.saveToHibernate(session, instanceMichele);
		session.close();
	}
}
