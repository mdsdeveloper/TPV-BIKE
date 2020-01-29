package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Configuracion;
import util.HibernateUtil;

/**
 * 
 * @author Two hands Technology
 *
 */
public class ConfiguracionDaoImpl implements ConfiguracionDao{

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 6877058704114300022L;
	private Session session;
	private Transaction tx;
	
	private void iniciaSession() throws HibernateException{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	private void manejaExcepcion(HibernateException he) throws HibernateException{
	    tx.rollback();
	    throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}
	@Override
	public Long createConfiguracion(Configuracion configuracion) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(configuracion);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		return dev;
	}
	@Override
	public Configuracion readConfiguracion(Long idConfiguracion) {
		Configuracion dev = null;
		try{
			iniciaSession();
			dev = session.get(Configuracion.class, idConfiguracion);
		}finally {
			session.close();
		}
		return dev;
	}
	@Override
	public void upDateConfiguracion(Configuracion configuracion) {
		try{
			iniciaSession();
			session.update(configuracion);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
	@Override
	public void deleteConfiguracion(Configuracion configuracion) {
		try{
			iniciaSession();
			session.delete(configuracion);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Configuracion getConfiguracion() {
		@SuppressWarnings("unused")
		Configuracion dev = null;
		@SuppressWarnings("unused")
		List<Configuracion> lista = new ArrayList<>();
		
		try{
			iniciaSession();
			lista = session.createQuery("from Configuracion").list();
		}finally {
			session.close();
		}
		if(lista != null && !lista.isEmpty()){
			dev = lista.iterator().next();
		}
	
		
		return dev;
	}
}
