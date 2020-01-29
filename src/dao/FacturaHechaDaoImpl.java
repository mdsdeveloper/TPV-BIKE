package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Factura;
import modelo.FacturaHecha;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FacturaHechaDaoImpl implements FacturaHechaDao{

	private static final long serialVersionUID = -6388346983448024721L;
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
	public Long createFacturaHecha(FacturaHecha facturaHecha) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(facturaHecha);
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
	public FacturaHecha readFacturaHecha(Long idFacturaHecha) {
		FacturaHecha dev = null;
		try{
			iniciaSession();
			dev = session.get(FacturaHecha.class, idFacturaHecha);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateFacturaHecha(FacturaHecha facturaHecha) {
		try{
			iniciaSession();
			session.update(facturaHecha);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteFacturaHecha(FacturaHecha facturaHecha) {
		try{
			iniciaSession();
			session.delete(facturaHecha);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
	@Override
	public Integer obtenerNumeroFactura() {
		Integer dev = null;
		List<Integer> lista;
		try{
			iniciaSession();
			String hql = "select MAX(numero) from FacturaHecha";
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
	public List<FacturaHecha> obtenerFacturas() {
		List<FacturaHecha> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from FacturaHecha").list();
		}finally {
			session.close();
		}
		return dev;
	}

}
