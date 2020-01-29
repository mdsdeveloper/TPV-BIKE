package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Producto;
import modelo.ProductosVarios;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class ProductosVariosDaoImpl implements ProductosVariosDao {

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
	public Long createProductosVarios(ProductosVarios productosVarios) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(productosVarios);
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
	public ProductosVarios readProductosVarios(Long idProductosVarios) {
		ProductosVarios dev = null;
		try{
			iniciaSession();
			dev = session.get(ProductosVarios.class, idProductosVarios);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateProductosVarios(ProductosVarios producto) {
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
	public void deleteProductosVarios(ProductosVarios producto) {
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
	public ProductosVarios obtenerProductoVarios(Long idProducto, Long idVentaRealizada, Date fecha) {
		List<ProductosVarios> lista = null;
		ProductosVarios dev = null;
		
		try{
			iniciaSession();
			String hql = "from ProductosVarios where idProducto = :idProducto and idLineaVentaRealizada = :idVentaRealizada and fecha = :fecha";
			Query query = session.createQuery(hql);
			query.setParameter("idProducto", idProducto);
			query.setParameter("idVentaRealizada", idVentaRealizada);
			query.setParameter("fecha", fecha);
			lista = query.list();			
			dev = lista.iterator().next();	
		}finally {
			session.close();
		}
		
		return dev;
	}

	
}
