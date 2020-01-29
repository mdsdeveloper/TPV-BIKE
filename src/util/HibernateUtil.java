package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * 
 * @author Two hands Technology
 *
 */
public class HibernateUtil {	// Con la declaración de static estamos haciendo un singleton ya que solo 
	// nesecitamos tener una conexión.
	/**
	 * Patrón singleton
	 */
	private static SessionFactory sessionFactory;
	private static Configuration configuration = new Configuration();
	

	static{
		configuration.configure("/hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}

