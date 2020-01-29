package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.LineaVentaRealizada;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class LineaVentaRealizadaDaoImpl implements LineaVentaRealizadaDao{
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
	public Long createLineaRealizada(LineaVentaRealizada lineaVentaRealizada) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(lineaVentaRealizada);
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
	public LineaVentaRealizada readLineaVentaRealizada(Long idLineaVentaRealizada) {
		LineaVentaRealizada dev = null;
		try{
			iniciaSession();
			dev = session.get(LineaVentaRealizada.class, idLineaVentaRealizada);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateLineaVentaRealizada(LineaVentaRealizada lineaVentaRealizada) {
		try{
			iniciaSession();
			session.update(lineaVentaRealizada);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteLineaVentaRealizada(LineaVentaRealizada lineaVentaRealizada) {
		try{
			iniciaSession();
			session.delete(lineaVentaRealizada);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

}
