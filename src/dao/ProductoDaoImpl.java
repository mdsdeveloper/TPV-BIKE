package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Producto;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class ProductoDaoImpl implements ProductoDao{

	private static final long serialVersionUID = -4035360271265406450L;
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
	public Long createProducto(Producto producto) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(producto);
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
	public Producto readProducto(Long idProducto) {
		Producto dev = null;
		try{
			iniciaSession();
			dev = session.get(Producto.class, idProducto);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateProducto(Producto producto) {
		
		try{
			iniciaSession();
			session.update(producto);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}		
	}

	@Override
	public void deleteProducto(Producto producto) {
		
		try{
			iniciaSession();
			session.delete(producto);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Producto> obtenerProductos() {
		
		List<Producto> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from Producto").list();
		}finally {
			session.close();
		}
		return dev;
	}
	@Override
	public List<Producto> obtenerProductosByCategoria(String id) {
		List<Producto> dev = null;
		try{
			iniciaSession();
			String hql = "from Producto where idCategoria = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			dev = query.list();
		}finally {
			session.close();
		}
		
		return dev;
	}
	@Override
	public Producto obtenerProductoGenerico() {
		List<Producto> lista = null;
		Producto dev = null;
		try{
			iniciaSession();
			String hql = "from Producto where nombre like 'Varios'";
			Query query = session.createQuery(hql);
			lista = query.list();			
			dev = lista.iterator().next();	
		}finally {
			session.close();
		}
		
		return dev;
	}

}
