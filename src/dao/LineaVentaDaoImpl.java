package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.LineaVenta;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class LineaVentaDaoImpl implements LineaVentaDao {

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
	public Long createLinea(LineaVenta linea) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(linea);
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
	public LineaVenta readLineaVenta(Long idLineaVenta) {
		LineaVenta dev = null;
		try{
			iniciaSession();
			dev = session.get(LineaVenta.class, idLineaVenta);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateLineaVenta(LineaVenta linea) {
		try{
			iniciaSession();
			session.update(linea);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteLineaVenta(LineaVenta linea) {
		try{
			iniciaSession();
			session.delete(linea);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<LineaVenta> obtenerLineaVentas() {
		List<LineaVenta> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from LineaVenta").list();
		}finally {
			session.close();
		}
		return dev;
	}
	@Override
	public void deleteAll() {
		try{
//			iniciaSession();
			List<LineaVenta> lista = obtenerLineaVentas();
			for (LineaVenta lineaVenta : lista) {
				deleteLineaVenta(lineaVenta);
			}
//			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
//			session.close();
		}
		
	}

}
