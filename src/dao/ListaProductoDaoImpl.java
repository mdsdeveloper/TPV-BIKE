package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.ListaProducto;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class ListaProductoDaoImpl implements ListaProductoDao {

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
	public Long createListaProducto(ListaProducto listaProducto) {
		Long dev = null;
		try{
			iniciaSession();
			dev = (Long) session.save(listaProducto);
			tx.commit();
			
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;		
		}finally{
			session.close();
		}
		return dev;
	}

	@Override
	public ListaProducto readListaProducto(Long idListaProducto) {
		ListaProducto dev = null;
		try{
			iniciaSession();
			dev = session.get(ListaProducto.class, idListaProducto);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateListaProducto(ListaProducto listaProducto) {
		try{
			iniciaSession();
			session.update(listaProducto);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteListaProducto(ListaProducto listaProducto) {
		try{
			iniciaSession();
			session.delete(listaProducto);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
	@Override
	public List<ListaProducto> obtenerListaProducto() {
		List<ListaProducto> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from ListaProducto").list();
		}finally {
			session.close();
		}
		return dev;
	}

}
