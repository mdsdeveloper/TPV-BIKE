package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Venta;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class VentaDaoImpl implements VentaDao{

	private static final long serialVersionUID = 3935007251591607861L;
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
	public Long createVenta(Venta venta) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(venta);
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
	public Venta readVenta(Long idVenta) {
		Venta dev = null;
		try{
			iniciaSession();
			dev = session.get(Venta.class, idVenta);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateVenta(Venta venta) {
		try{
			iniciaSession();
			session.update(venta);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteVenta(Venta venta) {
		try{
			iniciaSession();
			session.delete(venta);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Venta> obtenerVentas() {
		List<Venta> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from Venta").list();
		}finally {
			session.close();
		}
		return dev;
	}
	@Override
	public List<Venta> cerrarCaja(Date ultimoCierre, Date hoy) {
		List<Venta> dev = null;
		try{
			iniciaSession();
			String hql = "from Venta where fecha between :ultimoCierre and :hoy";
			Query query = session.createQuery(hql);
			query.setParameter("hoy", hoy);
			query.setParameter("ultimoCierre", ultimoCierre);
			dev = query.list();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally{
			session.close();
		}
		
		return dev;
	}
	@Override
	public Integer obtenerNumeroVenta() {
		Integer dev = null;
		List<Integer> lista;
		try{
			iniciaSession();
			String hql = "select MAX(numero) from Venta";
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
}
