package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Categoria;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class CategoriaDaoImpl implements CategoriaDao {

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
	public Long createCategoria(Categoria categoria) {
		Long dev = null;
		try{
			iniciaSession();
			dev = (Long) session.save(categoria);
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
	public Categoria readCategoria(Long idCategoria) {
		Categoria dev = null;
		try{
			iniciaSession();
			dev = session.get(Categoria.class, idCategoria);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateCategoria(Categoria categoria) {
		
		try{
			iniciaSession();
			session.update(categoria);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteCategoria(Categoria categoria) {
		
		try{
			iniciaSession();
			session.delete(categoria);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Categoria> obtenerCategorias() {
		List<Categoria> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from Categoria").list();
		}finally {
			session.close();
		}
		return dev;
	}
	@Override
	public Categoria obtenerCategoriaByName(String nombre) {
		List<Categoria> lista = null;
		Categoria dev = null;
		
		try{
			iniciaSession();
			String hql = "from Categoria where nombre like :nombre";
			Query query = session.createQuery(hql);
			query.setParameter("nombre", nombre);
			lista = query.list();			
			dev = lista.iterator().next();
			
		}finally {
			session.close();
		}
		return dev;		
	}
	@Override
	public Categoria obtenerCategoriaGeneral() {
		List<Categoria> lista = null;
		Categoria dev = null;		
		try{
			iniciaSession();
			String hql = "from Categoria where nombre like 'Varios'";
			Query query = session.createQuery(hql);
			lista = query.list();			
			dev = lista.iterator().next();				
		}finally{
			session.close();
		}
		return dev;	
	}

}
