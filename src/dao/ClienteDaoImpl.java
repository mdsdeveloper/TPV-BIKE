package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Cliente;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class ClienteDaoImpl implements ClienteDao{

	private static final long serialVersionUID = 6877058704114300022L;
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
	public Long createCliente(Cliente cliente) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(cliente);
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
	public Cliente readCliente(Long idCliente) {
		Cliente dev = null;
		try{
			iniciaSession();
			dev = session.get(Cliente.class, idCliente);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateCliente(Cliente cliente) {
		try{
			iniciaSession();
			session.update(cliente);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteCliente(Cliente cliente) {
		try{
			iniciaSession();
			session.delete(cliente);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> obtenerClientes() {
		List<Cliente> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from Cliente").list();
		}finally {
			session.close();
		}
		return dev;	
	}

}
