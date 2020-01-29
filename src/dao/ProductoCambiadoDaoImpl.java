package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.ProductoCambiado;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class ProductoCambiadoDaoImpl implements ProductoCambiadoDao {
	
	private static final long serialVersionUID = 5665498182425403274L;
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
	public Long createProductoCambiado(ProductoCambiado productoCambiado) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(productoCambiado);
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
	public ProductoCambiado readProductoCambiado(Long idProductoCambiado) {
		ProductoCambiado dev = null;
		try{
			iniciaSession();
			dev = session.get(ProductoCambiado.class, idProductoCambiado);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateProductoCambiado(ProductoCambiado productoCambiado) {
		try{
			iniciaSession();
			session.update(productoCambiado);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteProductoCambiado(ProductoCambiado productoCambiado) {
		try{
			iniciaSession();
			session.delete(productoCambiado);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
	@Override
	public List<ProductoCambiado> obtenerProductos() {
		List<ProductoCambiado> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from ProductoCambiado").list();
		}finally {
			session.close();
		}
		return dev;
	}

}
