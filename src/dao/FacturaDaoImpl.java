package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Factura;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FacturaDaoImpl implements FacturaDao {

	private static final long serialVersionUID = 9217655615641074442L;
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
	public Long createFactura(Factura factura) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(factura);
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
	public Factura readFactura(Long idFactura) {
		Factura dev = null;
		try{
			iniciaSession();
			dev = session.get(Factura.class, idFactura);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateFactura(Factura factura) {
		try{
			iniciaSession();
			session.update(factura);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteFactura(Factura factura) {
		try{
			iniciaSession();
			session.delete(factura);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Factura> obtenerFacturas() {
		List<Factura> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from Factura").list();
		}finally {
			session.close();
		}
		return dev;
	}

}
