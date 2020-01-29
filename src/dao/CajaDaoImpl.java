package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Caja;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class CajaDaoImpl implements CajaDao{

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
	public Long createCaja(Caja caja) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(caja);
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
	public Caja readCaja(Long idCaja) {
		Caja dev = null;
		try{
			iniciaSession();
			dev = session.get(Caja.class, idCaja);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateCaja(Caja caja) {
		try{
			iniciaSession();
			session.update(caja);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteCaja(Caja caja) {
		try{
			iniciaSession();
			session.delete(caja);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
	}
	@Override
	public Date getUltimaFechaCierre() {
		Date dev = null;
		List<Date> lista = null;
		try{
			iniciaSession();
			String hql = "select MAX(fechaCierre) from Caja";
			Query query = session.createQuery(hql);
			lista = query.list();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		if(lista.iterator().next() != null){
			dev = lista.iterator().next();
		}
		
		return dev;
	}
	@Override
	public List<Caja> obtenerCajas() {
		List<Caja> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from Caja").list();
		}finally {
			session.close();
		}
		return dev;
	}

}
